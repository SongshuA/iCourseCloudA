package service;

import domain.Course;
import exception.ServiceException;
import service.impl.CourseServiceImpl;

import java.util.List;

public interface CourseService {

    static CourseService getInstance() { return CourseServiceImpl.getInstance(); }

    List<Course> getAllCourse();

    void createCourse(String creatorUsername, String name, String description, String assertFolderPath) throws ServiceException;

    void selectCourse(String username, int courseId) throws ServiceException;

    List<Course> getSelectedCourses(String username) throws ServiceException;

    List<Course> getCreatedCourses(String creatorUsername) throws ServiceException;

    Course getCourseById(int id);
}
