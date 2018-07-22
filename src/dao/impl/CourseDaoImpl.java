package dao.impl;

import dao.CourseDao;
import dao.UserDao;
import domain.Course;
import domain.User;
import util.JDBCTools.SQLExecute;
import util.JDBCTools.SQLQuery;

import java.util.List;

public class CourseDaoImpl implements CourseDao {
    private CourseDaoImpl(){}

    private static class SingletonFactory{
        private static CourseDaoImpl singleton = new CourseDaoImpl();
    }

    public static CourseDaoImpl getInstance(){
        return SingletonFactory.singleton;
    }

    @Override
    public int create(Course course) {
        SQLExecute execute = new SQLExecute("INSERT INTO course (name, description, creatorId) VALUES(?, ?, ?)", statement -> {
            statement.setString(1, course.getName());
            statement.setString(2, course.getDescription());
            statement.setInt(3, course.getCreator().getId());
        });

        return execute.run();
    }

    @Override
    public Course getById(int id) {
        final UserDao userDao = UserDao.getInstance();
        Course course = null;

        SQLQuery<Course> query = new SQLQuery<>("SELECT * FROM course WHERE id = ?", statement -> statement.setInt(1, id), (rs, list) -> {
            if(rs.next())
                list.add(new Course(id, rs.getString("name"), rs.getString("description"), userDao.getById(rs.getInt("creatorId"))));
        });

        List<Course> list = query.run();
        if(list.size() > 0)
            course = list.get(0);

        return course;
    }

    @Override
    public List<Course> getByCreator(User creator) {

        SQLQuery<Course> query = new SQLQuery<>("SELECT * FROM course WHERE creatorId = ?", statement -> statement.setInt(1, creator.getId()), (rs, list) -> {
            while(rs.next())
                list.add(new Course(rs.getInt("id"), rs.getString("name"), rs.getString("description"), creator));
        });

        return query.run();
    }

    @Override
    public List<Course> getAll() {
        UserDao userDao = UserDao.getInstance();

        SQLQuery<Course> query = new SQLQuery<>("SELECT * FROM course", null, (rs, list) -> {
            while(rs.next())
                list.add(new Course(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        userDao.getById(rs.getInt("creatorId"))));
        });

        return query.run();
    }
}
