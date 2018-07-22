package service;

import domain.User;
import exception.ServiceException;
import service.impl.UserServiceImpl;

public interface UserService {
    static UserService getInstance() { return UserServiceImpl.getInstance(); }

    User queryUser(String username);

    User getUserById(int id);

    int register(String username, String password) throws ServiceException;

    void authorize(String username, String password) throws ServiceException;
}
