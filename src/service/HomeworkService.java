package service;

import domain.Homework;
import exception.ServiceException;
import service.impl.HomeworkServiceImpl;

import java.util.List;

/**
 * 作业操作类
 */
public interface HomeworkService {
    static HomeworkService getInstance() { return HomeworkServiceImpl.getInstance(); }

    /**
     * 取某课程的所有作业列表
     * @param courseId 课程ID
     * @return 作业对象列表
     * @throws ServiceException 操作失败时抛出的异常，通过e.getMessage()获得错误原因
     */
    List<Homework> getHomework(int courseId) throws ServiceException;

    /**
     * 根据ID取出作业对象
     * @param id 作业ID
     * @return 作业对象
     */
    Homework getHomeworkById(int id);

    /**
     * 创建作业
     * @param name 作业名称
     * @param context 作业内容
     * @param courseId 作业所属课程ID
     * @return 创建的作业对象ID
     * @throws ServiceException 操作失败时抛出的异常，通过e.getMessage()获得错误原因
     */
    int createHomework(String name, String context, int courseId) throws ServiceException;

    /**
     * 删除作业
     * @param id 要删除的作业ID
     */
    void deleteHomeworkById(int id);

    /**
     * 修改作业内容
     * @param id 要修改的作业ID
     * @param name 作业名称
     * @param context 作业内容
     * @throws ServiceException 操作失败时抛出的异常，通过e.getMessage()获得错误原因
     */
    void updateHomeworkById(int id, String name, String context) throws ServiceException;
}
