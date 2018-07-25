package dao;

import dao.impl.ChapterDaoImpl;
import domain.Chapter;
import domain.Course;

import java.util.List;

public interface ChapterDao {
    static ChapterDao getInstance(){ return ChapterDaoImpl.getInstance(); }

    int create(Chapter chapter);

    Chapter getById(int id);

    List<Chapter> getByCourse(Course course);

    void delete(int id);

    void update(int id, Chapter chapter);
}
