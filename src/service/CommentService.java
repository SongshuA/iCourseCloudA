package service;

import domain.Comment;
import exception.ServiceException;
import service.impl.CommentServiceImpl;

import java.util.List;

public interface CommentService {
    static CommentService getInstance() { return CommentServiceImpl.getInstance(); }

    List<Comment> getComments(int courseId) throws ServiceException;

    int createComment(String context, String username, int courseId) throws ServiceException;
}
