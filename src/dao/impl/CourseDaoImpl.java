package dao.impl;

import dao.CourseDao;
import dao.UserDao;
import domain.Course;
import domain.User;
import util.SQLExecute;
import util.SQLQuery;

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
    public boolean create(Course course) {
        SQLExecute execute = new SQLExecute("INSERT INTO course (name, description, assertFolderPath, creatorId) VALUES(?, ?, ?, ?)", statement -> {
            statement.setString(1, course.getName());
            statement.setString(2, course.getDescription());
            statement.setString(3, course.getAssertFolderPath());
            statement.setInt(4, course.getCreator().getId());
        });

        boolean result = execute.run();
        execute.free();
        return result;
    }

    @Override
    public Course getById(int id) {
        final UserDao userDao = UserDao.getInstance();
        Course course = null;

        SQLQuery<Course> query = new SQLQuery<>("SELECT * FROM course WHERE id = ?", statement -> statement.setInt(1, id), (rs, list) -> {
            if(rs.next())
                list.add(new Course(id, rs.getString("name"), rs.getString("description"),
                         rs.getString("assertFolderPath"), userDao.getById(rs.getInt("creatorId"))));
        });

        List<Course> list = query.run();
        if(list.size() > 0)
            course = list.get(0);

        query.free();
        return course;
    }

    @Override
    public List<Course> getByCreator(User creator) {

        SQLQuery<Course> query = new SQLQuery<>("SELECT * FROM course WHERE creatorId = ?", statement -> statement.setInt(1, creator.getId()), (rs, list) -> {
            while(rs.next())
                list.add(new Course(rs.getInt("id"), rs.getString("name"), rs.getString("description"),
                        rs.getString("assertFolderPath"), creator));
        });

        List<Course> list = query.run();
        query.free();
        return list;
    }
}
