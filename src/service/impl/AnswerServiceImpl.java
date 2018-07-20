package service.impl;

import service.AnswerService;

public class AnswerServiceImpl implements AnswerService {
    private AnswerServiceImpl() {}

    private static class SingletonFactory{
        private static AnswerServiceImpl singleton = new AnswerServiceImpl();
    }

    public static AnswerServiceImpl getInstance(){
        return SingletonFactory.singleton;
    }
}
