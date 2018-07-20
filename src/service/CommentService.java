package service;

import service.impl.CommentServiceImpl;

public interface CommentService {
    static CommentService getInstance() { return CommentServiceImpl.getInstance(); }
}
