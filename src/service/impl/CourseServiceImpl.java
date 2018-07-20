package service.impl;

import service.CourseService;

public class CourseServiceImpl implements CourseService {
    private CourseServiceImpl() {}

    private static class SingletonFactory{
        private static CourseServiceImpl singleton = new CourseServiceImpl();
    }

    public static CourseServiceImpl getInstance(){
        return SingletonFactory.singleton;
    }
}
