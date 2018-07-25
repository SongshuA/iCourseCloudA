package service;

import domain.Course;
import exception.ServiceException;
import service.impl.CourseServiceImpl;

import java.util.List;

public interface CourseService {

    static CourseService getInstance() { return CourseServiceImpl.getInstance(); }

    /**
     * 获取当前所有的课程列表
     * @return 所有课程的列表
     */
    List<Course> getAllCourse();

    /**
     * 创建课程
     * @param creatorUsername 开课者用户名
     * @param name 课程名
     * @param description 课程简介
     * @return 创建的课程ID
     * @throws ServiceException 操作失败时抛出的异常，通过e.getMessage()获得错误原因
     */
    int createCourse(String creatorUsername, String name, String description) throws ServiceException;

    /**
     * 选课
     * @param username 用户名
     * @param courseId 课程ID
     * @throws ServiceException 操作失败时抛出的异常，通过e.getMessage()获得错误原因
     */
    void selectCourse(String username, int courseId) throws ServiceException;

    /**
     * 退课
     * @param username 用户名
     * @param courseId 课程ID
     * @throws ServiceException 操作失败时抛出的异常，通过e.getMessage()获得错误原因
     */
    void dropCourse(String username, int courseId) throws ServiceException;

    /**
     * 获取课程资源文件夹URL（既在浏览器中的访问地址）
     * @param courseId 课程ID
     * @return 文件夹URL
     */
    String getResourceFolderURL(int courseId);

    /**
     * 获取课程资源文件夹在本地的路径
     * @param courseId 课程ID
     * @return 文件夹路径
     */
    String getResourceFolderLocalPath(int courseId);

    /**
     * 获取课程中所有资源的文件名
     * （可与getResourceFolderURL 或者 getResourceFolderLocalPath方法结合得到完整地址）
     * @param courseId 课程ID
     * @return 文件名列表
     */
    List<String> getListOfResourceFilename(int courseId);


    /**
     * 获取课程封面图像URL（既在浏览器中的访问地址）
     * @param courseId 课程ID
     * @return URL
     */
    String getCoverURL(int courseId);

    /**
     * 获取课程封面图像在本地的路径
     * @param courseId 课程ID
     * @return 封面图像路径
     */
    String getCoverLocalPath(int courseId);

    /**
     * 得到一个用户选的所有课
     * @param username 用户名
     * @return 课程对象列表
     * @throws ServiceException 操作失败时抛出的异常，通过e.getMessage()获得错误原因
     */
    List<Course> getSelectedCourses(String username) throws ServiceException;

    /**
     * 得到一个用户创建的所有课
     * @param creatorUsername 用户名
     * @return 课程对象列表
     * @throws ServiceException 操作失败时抛出的异常，通过e.getMessage()获得错误原因
     */
    List<Course> getCreatedCourses(String creatorUsername) throws ServiceException;

    /**
     * 通过课程ID获取课程对象
     * @param id 课程ID
     * @return 课程对象列表
     */
    Course getCourseById(int id);

    /**
     * 删除课程
     * @param id 课程ID
     */
    void deleteCourseById(int id);

    /**
     * 更改课程信息
     * @param id 要修改的课程ID
     * @param name 课程名
     * @param description 课程描述
     * @throws ServiceException 操作失败时抛出的异常，通过e.getMessage()获得错误原因
     */
    void updateCourse(int id, String name, String description) throws ServiceException;
}
