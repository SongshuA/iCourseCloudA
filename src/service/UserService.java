package service;

import domain.User;
import exception.ServiceException;
import service.impl.UserServiceImpl;

/**
 * 用户操作类
 */
public interface UserService {
    static UserService getInstance() { return UserServiceImpl.getInstance(); }

    /**
     * 以用户名查询用户
     * @param username 用户名
     * @return 用户名对应的用户对象
     */
    User queryUser(String username);

    /**
     * 根据用户ID取出对应的用户对象
     * @param id 用户ID
     * @return 用户对象
     */
    User getUserById(int id);

    /**
     * 注册用户
     * @param username 用户名
     * @param password 密码
     * @return 产生的用户ID
     * @throws ServiceException 操作失败时抛出的异常，通过e.getMessage()获得错误原因
     */
    int register(String username, String password) throws ServiceException;

    /**
     * 检验用户名与密码是否匹配（用于登录）
     * @param username 用户名
     * @param password 密码
     * @throws ServiceException 操作失败时抛出的异常，通过e.getMessage()获得错误原因
     */
    void authorize(String username, String password) throws ServiceException;
}
