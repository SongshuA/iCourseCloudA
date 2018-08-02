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

    @Override
    public void delete(int id) {
        SQLExecute execute = new SQLExecute("DELETE FROM course WHERE id=?", statement -> {
            statement.setInt(1, id);
        });
        execute.run();
    }

    @Override
    public void update(int id, Course course) {
        SQLExecute execute = new SQLExecute("UPDATE course SET name=?, description=?, creatorId=? WHERE id=?", statement -> {
            statement.setString(1, course.getName());
            statement.setString(2, course.getDescription());
            statement.setInt(3, course.getCreator().getId());
            statement.setInt(4, id);
        });

        execute.run();
    }

    @Override
    public List<Course> getCourseOrderByEngagement(int limit) {
        UserDao userDao = UserDao.getInstance();
        SQLQuery<Course> query = new SQLQuery<>("SELECT course.id, course.`name`, course.description, course.creatorId FROM course LEFT JOIN `select` ON `select`.courseId = course.id GROUP BY course.id ORDER BY COUNT(`select`.userId) DESC LIMIT ?", statement -> {
            statement.setInt(1, limit);
        }, (rs, list) -> {
            while(rs.next())
                list.add(new Course(rs.getInt("id"), rs.getString("name"), rs.getString("description"), userDao.getById(rs.getInt("creatorId"))));
        });

        return query.run();
    }

    @Override
    public List<Course> getCourseOrderByTime(int limit) {
        UserDao userDao = UserDao.getInstance();
        SQLQuery<Course> query = new SQLQuery<>("SELECT * FROM course ORDER BY course.id DESC LIMIT ?", statement -> {
            statement.setInt(1, limit);
        }, (rs, list) -> {
            while(rs.next())
                list.add(new Course(rs.getInt("id"), rs.getString("name"), rs.getString("description"), userDao.getById(rs.getInt("creatorId"))));
        });

        return query.run();
    }

    @Override
    public List<Course> getCoursesByOrderByEngagementByNameLike(String keyword, int skip, int limit) {
        UserDao userDao = UserDao.getInstance();

        SQLQuery<Course> query = new SQLQuery<>("SELECT course.* FROM course LEFT JOIN `select` ON course.id = `select`.courseId WHERE `course`.`name` LIKE ? GROUP BY `course`.id ORDER BY COUNT(`select`.id) DESC LIMIT ?,?",
        statement -> {
            statement.setString(1, '%' + keyword + '%');
            statement.setInt(2, skip);
            statement.setInt(3, limit);

        }, (rs, list) -> {
            while(rs.next()){
                list.add(new Course(rs.getInt(1), rs.getString(2),
                        rs.getString(3), userDao.getById(rs.getInt(4))));
            }
        });

        return query.run();
    }


    @Override
    public List<Course> getCoursesByOrderByEngagementByCreatorNameLike(String keyword, int skip, int limit) {
        UserDao userDao = UserDao.getInstance();

        SQLQuery<Course> query = new SQLQuery<>("SELECT course.* FROM course LEFT JOIN `select` ON course.id = `select`.courseId  INNER JOIN `user` ON course.creatorId = `user`.id WHERE `user`.`username` LIKE ? GROUP BY `course`.id ORDER BY COUNT(`select`.id) DESC LIMIT ?,?",
            statement -> {
                statement.setString(1, '%' + keyword + '%');
                statement.setInt(2, skip);
                statement.setInt(3, limit);

            }, (rs, list) -> {
                while(rs.next()){
                    list.add(new Course(rs.getInt(1), rs.getString(2),
                            rs.getString(3), userDao.getById(rs.getInt(4))));
                }
        });

        return query.run();
    }

    @Override
    public List<Course> getCoursesByOrderByCreateTimeByNameLike(String keyword, int skip, int limit) {
        UserDao userDao = UserDao.getInstance();

        SQLQuery<Course> query = new SQLQuery<>("SELECT course.* FROM course WHERE `course`.`name` LIKE ? ORDER BY course.id DESC LIMIT ?,?",
                statement -> {
                    statement.setString(1, '%' + keyword + '%');
                    statement.setInt(2, skip);
                    statement.setInt(3, limit);

                }, (rs, list) -> {
            while(rs.next()){
                list.add(new Course(rs.getInt(1), rs.getString(2),
                        rs.getString(3), userDao.getById(rs.getInt(4))));
            }
        });

        return query.run();
    }

    @Override
    public List<Course> getCoursesByOrderByCreateTimeByCreatorNameLike(String keyword, int skip, int limit) {
        UserDao userDao = UserDao.getInstance();

        SQLQuery<Course> query = new SQLQuery<>("SELECT course.* FROM course INNER JOIN `user` ON course.creatorId = `user`.id WHERE `user`.`username` LIKE ? ORDER BY course.id DESC LIMIT ?,?",
                    statement -> {
                        statement.setString(1, '%' + keyword + '%');
                    statement.setInt(2, skip);
                    statement.setInt(3, limit);

                }, (rs, list) -> {
            while(rs.next()){
                list.add(new Course(rs.getInt(1), rs.getString(2),
                        rs.getString(3), userDao.getById(rs.getInt(4))));
            }
        });

        return query.run();
    }

    @Override
    public int countCoursesByNameLike(String keyword) {
        Integer count = 0;

        SQLQuery<Integer> query = new SQLQuery<>("SELECT COUNT(id) FROM course WHERE `name` LIKE ?", statement -> {
            statement.setString(1, '%' + keyword + '%');
        }, (rs, list) -> {
            if(rs.next())
                list.add(rs.getInt(1));
        });

        List<Integer> r = query.run();

        if(!r.isEmpty())
            count = r.get(0);

        return count;
    }

    @Override
    public int countCoursesByCreatorNameLike(String keyword) {
        Integer count = 0;

        SQLQuery<Integer> query = new SQLQuery<>("SELECT COUNT(course.id) FROM course INNER JOIN `user` ON `user`.id = course.creatorId WHERE `user`.username LIKE ?", statement -> {
            statement.setString(1, '%' + keyword + '%');
        }, (rs, list) -> {
            if(rs.next())
                list.add(rs.getInt(1));
        });

        List<Integer> r = query.run();
        if(!r.isEmpty())
            count = r.get(0);

        return count;
    }
}
