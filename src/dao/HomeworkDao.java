package dao;

import dao.impl.HomeworkDaoImpl;
import domain.Course;
import domain.Homework;

import java.util.List;

public interface HomeworkDao {
    static HomeworkDao getInstance(){ return HomeworkDaoImpl.getInstance(); }

    int create(Homework homework);

    Homework getById(int id);

    List<Homework> getByCourse(Course course);
}
