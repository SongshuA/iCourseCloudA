package service.impl;

import service.BarrageService;

public class BarrageServiceImpl implements BarrageService {
    private BarrageServiceImpl() {}

    private static class SingletonFactory{
        private static BarrageServiceImpl singleton = new BarrageServiceImpl();
    }

    public static BarrageServiceImpl getInstance(){
        return SingletonFactory.singleton;
    }
}
