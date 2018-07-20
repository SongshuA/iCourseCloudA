package dao;

import dao.impl.SelectDaoImpl;
import domain.Course;
import domain.Select;
import domain.User;

import java.util.List;

public interface SelectDao {
    static SelectDao getInstance(){ return SelectDaoImpl.getInstance(); }

    boolean create(Select select);

    List<Select> getByUser(User user);

    List<Select> getByCourse(Course course);
}
