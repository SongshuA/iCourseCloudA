package dao.impl;

import dao.CourseDao;
import dao.SelectDao;
import dao.UserDao;
import domain.Course;
import domain.Select;
import domain.User;
import util.JDBCTools.SQLExecute;
import util.JDBCTools.SQLQuery;

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
    public boolean create(Select select) {
        SQLExecute execute = new SQLExecute("INSERT INTO `select` (userId, courseId) VALUES(?, ?)", statement -> {
            statement.setInt(1, select.getUser().getId());
            statement.setInt(2, select.getCourse().getId());
        });

        boolean result = execute.run();
        execute.free();
        return result;
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
        List<Select> list = query.run();
        query.free();
        return list;
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
        List<Select> list = query.run();
        query.free();
        return list;
    }
}
