package dao.impl;

import dao.CourseDao;
import dao.HomeworkDao;
import domain.Course;
import domain.Homework;
import util.JDBCTools.SQLExecute;
import util.JDBCTools.SQLQuery;

import java.util.List;

public class HomeworkDaoImpl implements HomeworkDao {
    private HomeworkDaoImpl(){}

    private static class SingletonFactory{
        private static HomeworkDaoImpl singleton = new HomeworkDaoImpl();
    }

    public static HomeworkDaoImpl getInstance(){
        return SingletonFactory.singleton;
    }

    @Override
    public int create(Homework homework) {
        SQLExecute execute = new SQLExecute("INSERT INTO homework (name, context, courseId) VALUES(?, ?, ?)", statement -> {
            statement.setString(1, homework.getName());
            statement.setString(2, homework.getContext());
            statement.setInt(3, homework.getCourse().getId());
        });

        return execute.run();
    }

    @Override
    public Homework getById(int id) {
        final CourseDao courseDao = CourseDao.getInstance();
        Homework homework = null;

        SQLQuery<Homework> query = new SQLQuery<>("SELECT * FROM homework WHERE id = ?", statement -> statement.setInt(1, id), (rs, list) -> {
            if(rs.next())
                list.add(new Homework(id, rs.getString("name"), rs.getString("context"),
                        courseDao.getById(rs.getInt("courseId"))));
        });

        List<Homework> list = query.run();
        if(list.size() > 0)
            homework = list.get(0);

        return homework;
    }

    @Override
    public List<Homework> getByCourse(Course course) {
        SQLQuery<Homework> query = new SQLQuery<>("SELECT * FROM homework WHERE courseId = ?", statement -> statement.setInt(1, course.getId()), (rs, list) -> {
            while(rs.next())
                list.add(new Homework(rs.getInt("id"), rs.getString("name"), rs.getString("context"), course));
        });

        return query.run();
    }

    @Override
    public void delete(int id) {
        SQLExecute execute = new SQLExecute("DELETE FROM homework WHERE id=?", statement -> {
            statement.setInt(1, id);
        });
        execute.run();
    }

    @Override
    public void update(int id, Homework homework) {
        SQLExecute execute = new SQLExecute("UPDATE homework SET name=?, context=?, courseId=? WHERE id=?", statement -> {
            statement.setString(1, homework.getName());
            statement.setString(2, homework.getContext());
            statement.setInt(3, homework.getCourse().getId());
            statement.setInt(4, id);
        });

        execute.run();
    }
}
