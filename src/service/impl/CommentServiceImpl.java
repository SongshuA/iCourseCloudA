package service.impl;

import service.CommentService;

public class CommentServiceImpl implements CommentService {
    private CommentServiceImpl() {}

    private static class SingletonFactory{
        private static CommentServiceImpl singleton = new CommentServiceImpl();
    }

    public static CommentServiceImpl getInstance(){
        return SingletonFactory.singleton;
    }
}
