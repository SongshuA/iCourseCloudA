package dao;

import dao.impl.CommentDaoImpl;
import domain.Comment;
import domain.Course;

import java.util.List;

public interface CommentDao {
    static CommentDao getInstance(){ return CommentDaoImpl.getInstance(); }

    int create(Comment comment);

    List<Comment> getByCourse(Course course);
}
