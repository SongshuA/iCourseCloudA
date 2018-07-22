package service.impl;

import dao.CommentDao;
import domain.Comment;
import domain.Course;
import domain.User;
import exception.ServiceException;
import service.CommentService;
import service.CourseService;
import service.UserService;

import java.util.List;

public class CommentServiceImpl implements CommentService {

    private CommentDao commentDao;

    private CommentServiceImpl() {
        commentDao = CommentDao.getInstance();
    }

    private static class SingletonFactory{
        private static CommentServiceImpl singleton = new CommentServiceImpl();
    }

    public static CommentServiceImpl getInstance(){
        return SingletonFactory.singleton;
    }


    @Override
    public List<Comment> getComments(int courseId) throws ServiceException {
        Course course = CourseService.getInstance().getCourseById(courseId);

        if(course == null)
            throw new ServiceException("评论的对应的课程不存在");

        return commentDao.getByCourse(course);
    }


    @Override
    public int createComment(String context, String username, int courseId) throws ServiceException {
        Course course = CourseService.getInstance().getCourseById(courseId);
        User user = UserService.getInstance().queryUser(username);

        if(course == null)
            throw new ServiceException("评论的对应的课程不存在");

        if(user == null)
            throw new ServiceException("评论的对应的用户不存在");

        return commentDao.create(new Comment(0,context,user,course));
    }
}
