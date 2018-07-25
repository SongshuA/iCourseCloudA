package service;

import domain.Course;
import exception.ServiceException;
import service.impl.CourseServiceImpl;

import java.util.List;

public interface CourseService {

    static CourseService getInstance() { return CourseServiceImpl.getInstance(); }

    List<Course> getAllCourse();

    int createCourse(String creatorUsername, String name, String description) throws ServiceException;

    void selectCourse(String username, int courseId) throws ServiceException;

    void dropCourse(String username, int courseId) throws ServiceException;

    String getResourceFolderURL(int courseId);

    String getResourceFolderLocalPath(int courseId);

    List<String> getListOfResourceFilename(int courseId);

    String getCoverURL(int courseId);

    String getCoverLocalPath(int courseId);

    List<Course> getSelectedCourses(String username) throws ServiceException;

    List<Course> getCreatedCourses(String creatorUsername) throws ServiceException;

    Course getCourseById(int id);

    void deleteCourseById(int id);

    void updateCourse(int id, String name, String description) throws ServiceException;
}
