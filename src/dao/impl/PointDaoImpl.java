package dao.impl;

import dao.ChapterDao;
import dao.PointDao;
import domain.Chapter;
import domain.Point;
import util.JDBCTools.SQLExecute;
import util.JDBCTools.SQLQuery;

import java.util.List;

public class PointDaoImpl implements PointDao {
    private PointDaoImpl(){}

    private static class SingletonFactory{
        private static PointDaoImpl singleton = new PointDaoImpl();
    }

    public static PointDaoImpl getInstance(){
        return SingletonFactory.singleton;
    }

    @Override
    public int create(Point point) {
        SQLExecute execute = new SQLExecute("INSERT INTO point (name, description, chapterId) VALUES(?, ?, ?)", statement -> {
            statement.setString(1, point.getName());
            statement.setString(2, point.getDescription());
            statement.setInt(3, point.getChapter().getId());
        });

        return execute.run();
    }

    @Override
    public Point getById(int id) {
        final ChapterDao chapterDao = ChapterDao.getInstance();
        Point user = null;

        SQLQuery<Point> query = new SQLQuery<>("SELECT * FROM point WHERE id = ?", statement -> statement.setInt(1, id), (rs, list) -> {
            if(rs.next())
                list.add(new Point(id, rs.getString("name"), rs.getString("description"), chapterDao.getById(rs.getInt("chapterId"))));
        });

        List<Point> list = query.run();
        if(list.size() > 0)
            user = list.get(0);

        return user;
    }

    @Override
    public List<Point> getByChapter(Chapter chapter) {
        SQLQuery<Point> query = new SQLQuery<>("SELECT * FROM point WHERE chapterId = ?", statement -> statement.setInt(1, chapter.getId()), (rs, list) -> {
            while(rs.next())
                list.add(new Point(rs.getInt("id"), rs.getString("name"),
                        rs.getString("description"), chapter));
        });

        return query.run();
    }

    @Override
    public void update(int id, Point point) {
        SQLExecute execute = new SQLExecute("UPDATE point SET name=?, description=?, chapterId=? WHERE id=?", statement -> {
            statement.setString(1, point.getName());
            statement.setString(2, point.getDescription());
            statement.setInt(3, point.getChapter().getId());
            statement.setInt(4, id);
        });

        execute.run();
    }

    @Override
    public void delete(int id) {
        SQLExecute execute = new SQLExecute("DELETE FROM point WHERE id=?", statement -> {
            statement.setInt(1, id);
        });
        execute.run();
    }
}
