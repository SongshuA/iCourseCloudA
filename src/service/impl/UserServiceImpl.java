package service.impl;

import dao.UserDao;
import domain.User;
import exception.ServiceException;
import service.UserService;

public class UserServiceImpl implements UserService {
    private UserDao userDao;

    private UserServiceImpl() {
        userDao = UserDao.getInstance();
    }

    private static class SingletonFactory{
        private static UserServiceImpl singleton = new UserServiceImpl();
    }

    public static UserServiceImpl getInstance(){
        return SingletonFactory.singleton;
    }


    @Override
    public User queryUser(String username) {
        return userDao.getByUsername(username);
    }


    @Override
    public int register(String username, String password) throws ServiceException {
        if(queryUser(username) != null)
            throw new ServiceException("用户已经存在");

        User user = new User(0, username, password);
        userDao.create(user);

        return (userDao.getByUsername(username).getId());
    }


    @Override
    public void authorize(String username, String password) throws ServiceException {
        User user = queryUser(username);

        if(user == null)
            throw new ServiceException("用户名不存在");


        if(!user.getPasswordHash().equals(password))
            throw new ServiceException("密码错误");

    }
}
