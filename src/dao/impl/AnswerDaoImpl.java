package dao.impl;

import dao.AnswerDao;
import dao.UserDao;
import domain.Answer;
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
    public boolean create(Answer answer) {

        SQLExecute execute = new SQLExecute("INSERT INTO answer (context, userId, homeworkId) VALUES(?, ?, ?)", statement -> {
            statement.setString(1, answer.getContext());
            statement.setInt(2, answer.getUser().getId());
            statement.setInt(3, answer.getHomework().getId());
        });

        boolean result = execute.run();
        execute.free();
        return result;
    }

    @Override
    public List<Answer> getByHomework(Homework homework) {
        UserDao userDao = UserDao.getInstance();

        SQLQuery<Answer> query = new SQLQuery<>("SELECT * FROM answer WHERE homeworkId = ?", statement -> statement.setInt(1, homework.getId()), (rs, list) -> {
            while(rs.next())
                list.add(new Answer(rs.getInt("id"), rs.getString("context"), rs.getInt("score"),
                        userDao.getById(rs.getInt("userId")), homework));
        });

        List<Answer> list = query.run();
        query.free();
        return list;
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

        query.free();
        return answer;
    }
}
