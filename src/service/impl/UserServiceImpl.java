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
        if(username == null)
            return null;

        return userDao.getByUsername(username);
    }


    @Override
    public User getUserById(int id) {
        return userDao.getById(id);
    }


    @Override
    public int register(String username, String password) throws ServiceException {
        if(queryUser(username) != null)
            throw new ServiceException("用户已经存在");

        User user = new User(0, username, password);

        return userDao.create(user);
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
