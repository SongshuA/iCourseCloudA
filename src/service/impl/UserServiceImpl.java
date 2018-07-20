package service.impl;

import service.UserService;

public class UserServiceImpl implements UserService {
    private UserServiceImpl() {}

    private static class SingletonFactory{
        private static UserServiceImpl singleton = new UserServiceImpl();
    }

    public static UserServiceImpl getInstance(){
        return SingletonFactory.singleton;
    }
}
