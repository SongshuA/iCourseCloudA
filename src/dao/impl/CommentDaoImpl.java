package dao.impl;

import dao.CommentDao;
import dao.UserDao;
import domain.Comment;
import domain.Course;
import util.JDBCTools.SQLExecute;
import util.JDBCTools.SQLQuery;

import java.util.List;

public class CommentDaoImpl implements CommentDao {
    private CommentDaoImpl(){}

    private static class SingletonFactory{
        private static CommentDaoImpl singleton = new CommentDaoImpl();
    }

    public static CommentDaoImpl getInstance(){
        return SingletonFactory.singleton;
    }

    @Override
    public int create(Comment comment) {
        SQLExecute execute = new SQLExecute("INSERT INTO comment (context, userId, courseId) VALUES(?, ?, ?)", statement -> {
            statement.setString(1, comment.getContext());
            statement.setInt(2, comment.getUser().getId());
            statement.setInt(3, comment.getCourse().getId());
        });

        return execute.run();
    }

    @Override
    public List<Comment> getByCourse(Course course) {
        UserDao userDao = UserDao.getInstance();

        SQLQuery<Comment> query = new SQLQuery<>("SELECT * FROM comment WHERE courseId = ?", statement -> statement.setInt(1, course.getId()), (rs, list) -> {
            while(rs.next())
                list.add(new Comment(rs.getInt("id"), rs.getString("context"),
                        userDao.getById(rs.getInt("id")), course));
        });

        return query.run();
    }
}
