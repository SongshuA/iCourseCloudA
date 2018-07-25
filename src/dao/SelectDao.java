package dao;

import dao.impl.SelectDaoImpl;
import domain.Course;
import domain.Select;
import domain.User;

import java.util.List;

public interface SelectDao {
    static SelectDao getInstance(){ return SelectDaoImpl.getInstance(); }

    int create(Select select);

    List<Select> getByUser(User user);

    List<Select> getByCourse(Course course);

    Select getByUserAndCourse(User user, Course course);

    void delete(int id);
}
