package dao;

import dao.impl.UserDaoImpl;
import domain.User;

public interface UserDao {
    static UserDao getInstance(){ return UserDaoImpl.getInstance(); }

    User getById(int id);

    User getByUsername(String username);

    int create(User user);
}
