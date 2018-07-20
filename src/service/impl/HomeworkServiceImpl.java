package service.impl;

import service.HomeworkService;

public class HomeworkServiceImpl implements HomeworkService {
    private HomeworkServiceImpl() {}

    private static class SingletonFactory{
        private static HomeworkServiceImpl singleton = new HomeworkServiceImpl();
    }

    public static HomeworkServiceImpl getInstance(){
        return SingletonFactory.singleton;
    }
}
