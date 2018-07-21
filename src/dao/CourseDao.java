package dao;

import dao.impl.CourseDaoImpl;
import domain.Course;
import domain.User;

import java.util.List;

public interface CourseDao {
    static CourseDao getInstance(){ return CourseDaoImpl.getInstance(); }

    boolean create(Course course);

    Course getById(int id);

    List<Course> getByCreator(User creator);

    List<Course> getAll();
}
