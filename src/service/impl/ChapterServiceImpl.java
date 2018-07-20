package service.impl;

import service.ChapterService;

public class ChapterServiceImpl implements ChapterService {
    private ChapterServiceImpl() {}

    private static class SingletonFactory{
        private static ChapterServiceImpl singleton = new ChapterServiceImpl();
    }

    public static ChapterServiceImpl getInstance(){
        return SingletonFactory.singleton;
    }
}
