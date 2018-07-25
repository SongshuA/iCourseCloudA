package dao.impl;

import dao.AnswerDao;
import dao.CourseDao;
import dao.HomeworkDao;
import dao.UserDao;
import domain.Answer;
import domain.Chapter;
import domain.Homework;
import domain.User;
import util.JDBCTools.SQLExecute;
import util.JDBCTools.SQLQuery;

import java.util.List;

public class AnswerDaoImpl implements AnswerDao {
    private AnswerDaoImpl(){}

    private static class SingletonFactory{
        private static AnswerDaoImpl singleton = new AnswerDaoImpl();
    }

    public static AnswerDaoImpl getInstance(){
        return SingletonFactory.singleton;
    }

    @Override
    public int create(Answer answer) {

        SQLExecute execute = new SQLExecute("INSERT INTO answer (context, userId, homeworkId) VALUES(?, ?, ?)", statement -> {
            statement.setString(1, answer.getContext());
            statement.setInt(2, answer.getUser().getId());
            statement.setInt(3, answer.getHomework().getId());
        });

        return execute.run();
    }

    @Override
    public List<Answer> getByHomework(Homework homework) {
        UserDao userDao = UserDao.getInstance();

        SQLQuery<Answer> query = new SQLQuery<>("SELECT * FROM answer WHERE homeworkId = ?", statement -> statement.setInt(1, homework.getId()), (rs, list) -> {
            while(rs.next())
                list.add(new Answer(rs.getInt("id"), rs.getString("context"), rs.getInt("score"),
                        userDao.getById(rs.getInt("userId")), homework));
        });

        return query.run();
    }

    @Override
    public Answer getById(int id) {
        Answer answer = null;

        SQLQuery<Answer> query = new SQLQuery<>("SELECT * FROM answer WHERE id = ?", statement -> statement.setInt(1, id), (rs, list) -> {
            if(rs.next())
                list.add(new Answer(id, rs.getString("context"),
                                    rs.getInt("score"), UserDao.getInstance().getById(rs.getInt("userId")),
                                    HomeworkDao.getInstance().getById(rs.getInt("homeworkId"))));
        });

        List<Answer> list = query.run();
        if(list.size() > 0)
            answer = list.get(0);

        return answer;
    }

    @Override
    public Answer getByUserAndCourse(User user, Homework homework) {
        Answer answer = null;

        SQLQuery<Answer> query = new SQLQuery<>("SELECT * FROM answer WHERE userId = ? AND homeworkId = ?", statement -> {
            statement.setInt(1, user.getId());
            statement.setInt(2, homework.getId());

        }, (rs, list) -> {
            if(rs.next())
                list.add(new Answer(rs.getInt("id"), rs.getString("context"), rs.getInt("score"), user, homework));
        });

        List<Answer> list = query.run();

        if(list.size() > 0)
            answer = list.get(0);

        return answer;
    }

    @Override
    public void update(int id, Answer answer) {
        SQLExecute execute = new SQLExecute("UPDATE answer SET context=?, score=?, userId=?, homeworkId=? WHERE id=?", statement -> {
            statement.setString(1, answer.getContext());
            statement.setInt(2,answer.getScore());
            statement.setInt(3, answer.getUser().getId());
            statement.setInt(4, answer.getHomework().getId());
            statement.setInt(5, id);
        });

        execute.run();
    }
}
