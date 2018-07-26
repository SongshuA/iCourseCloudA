package service;

import domain.Comment;
import exception.ServiceException;
import service.impl.CommentServiceImpl;

import java.util.List;

/**
 * 讨论板（评论）操作类
 */
public interface CommentService {
    static CommentService getInstance() { return CommentServiceImpl.getInstance(); }

    /**
     * 获取一个课程讨论版中的所有评论
     * @param courseId 课程ID
     * @return 评论对象列表
     * @throws ServiceException 操作失败时抛出的异常，通过e.getMessage()获得错误原因
     */
    List<Comment> getComments(int courseId) throws ServiceException;

    /**
     * 创建（发表）一个评论
     * @param context 评论内容
     * @param username 发表评论的用户名
     * @param courseId 评论所属的课程ID
     * @return 创建的评论对象ID
     * @throws ServiceException 操作失败时抛出的异常，通过e.getMessage()获得错误原因
     */
    int createComment(String context, String username, int courseId) throws ServiceException;
}
