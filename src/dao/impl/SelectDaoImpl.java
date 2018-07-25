package dao.impl;

import dao.CourseDao;
import dao.SelectDao;
import dao.UserDao;
import domain.Course;
import domain.Select;
import domain.User;
import util.JDBCTools.SQLExecute;
import util.JDBCTools.SQLQuery;

import java.util.ArrayList;
import java.util.List;

public class SelectDaoImpl implements SelectDao {
    private SelectDaoImpl(){}

    private static class SingletonFactory{
        private static SelectDaoImpl singleton = new SelectDaoImpl();
    }

    public static SelectDaoImpl getInstance(){
        return SingletonFactory.singleton;
    }

    @Override
    public int create(Select select) {
        SQLExecute execute = new SQLExecute("INSERT INTO `select` (userId, courseId) VALUES(?, ?)", statement -> {
            statement.setInt(1, select.getUser().getId());
            statement.setInt(2, select.getCourse().getId());
        });

        return execute.run();
    }

    @Override
    public List<Select> getByUser(User user) {
        final CourseDao courseDao = CourseDao.getInstance();
        SQLQuery<Select> query = new SQLQuery<>("SELECT * FROM `select` WHERE userId = ?", statement -> {
            statement.setInt(1, user.getId());
        }, (rs, list) -> {
            while(rs.next()){
                int id = rs.getInt("id");
                Course course = courseDao.getById(rs.getInt("courseId"));
                list.add(new Select(id, user, course));
            }
        });

        return query.run();
    }

    @Override
    public List<Select> getByCourse(Course course) {
        final UserDao userDao = UserDao.getInstance();
        SQLQuery<Select> query = new SQLQuery<>("SELECT * FROM select WHERE courseId = ?", statement -> {
            statement.setInt(1, course.getId());
        }, (rs, list) -> {
            while(rs.next()){
                int id = rs.getInt("id");
                User user = userDao.getById(rs.getInt("userId"));
                list.add(new Select(id, user, course));
            }
        });

        return query.run();
    }

    @Override
    public Select getByUserAndCourse(User user, Course course) {
        Select select = null;

        SQLQuery<Select> query = new SQLQuery<>("SELECT * FROM select WHERE userId=? AND courseId = ?", statement -> {
            statement.setInt(1, user.getId());
            statement.setInt(2, course.getId());
        }, (rs, list) -> {
            if(rs.next())
                list.add(new Select(rs.getInt("id"), user, course));
        });

        List<Select> selects = query.run();
        if(selects.size() > 0)
            select = selects.get(0);

        return select;
    }

    @Override
    public void delete(int id) {
        SQLExecute execute = new SQLExecute("DELETE FROM select WHERE id=?", statement -> {
            statement.setInt(1, id);
        });
        execute.run();
    }
}
