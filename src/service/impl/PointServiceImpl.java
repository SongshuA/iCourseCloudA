package service.impl;

import service.PointService;

public class PointServiceImpl implements PointService {
    private PointServiceImpl() {}

    private static class SingletonFactory{
        private static PointServiceImpl singleton = new PointServiceImpl();
    }

    public static PointServiceImpl getInstance(){
        return SingletonFactory.singleton;
    }
}
