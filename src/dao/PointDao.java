package dao;

import dao.impl.PointDaoImpl;
import domain.Chapter;
import domain.Point;

import java.util.List;

public interface PointDao {
    static PointDao getInstance(){ return PointDaoImpl.getInstance(); }

    int create(Point point);

    Point getById(int id);

    List<Point> getByChapter(Chapter chapter);

    void update(int id, Point point);

    void delete(int id);
}
