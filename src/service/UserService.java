package service;

import service.impl.UserServiceImpl;

public interface UserService {
    static UserService getInstance() { return UserServiceImpl.getInstance(); }
}
