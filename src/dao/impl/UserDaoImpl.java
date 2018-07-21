package dao.impl;

import dao.UserDao;
import domain.User;
import util.JDBCTools.SQLExecute;
import util.JDBCTools.SQLQuery;

import java.util.List;

public class UserDaoImpl implements UserDao {
    private UserDaoImpl(){}

    private static class SingletonFactory{
        private static UserDaoImpl singleton = new UserDaoImpl();
    }

    public static UserDaoImpl getInstance(){
        return SingletonFactory.singleton;
    }



    @Override
    public User getById(int id) {
        User user = null;

        SQLQuery<User> query = new SQLQuery<>("SELECT * FROM user WHERE id = ?", statement -> statement.setInt(1, id), (rs, list) -> {
            if(rs.next())
                list.add(new User(id, rs.getString("username"), rs.getString("passwordHash")));
        });

        List<User> list = query.run();
        if(list.size() > 0)
            user = list.get(0);

        query.free();
        return user;
    }

    @Override
    public User getByUsername(String username) {
        User user = null;

        SQLQuery<User> query = new SQLQuery<>("SELECT * FROM user WHERE username = ?", statement -> statement.setString(1, username), (rs, list) -> {
            if(rs.next())
                list.add(new User(rs.getInt("id"), username, rs.getString("passwordHash")));
        });

        List<User> list = query.run();
        if(list.size() > 0)
            user = list.get(0);

        query.free();
        return user;
    }

    @Override
    public boolean create(User user) {
        SQLExecute execute = new SQLExecute("INSERT INTO user (username, passwordHash) VALUES(?, ?)", statement -> {
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPasswordHash());
        });
        boolean result = execute.run();
        execute.free();
        return result;
    }
}
