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
    public boolean create(Point point) {
        SQLExecute execute = new SQLExecute("INSERT INTO point (name, description, videoFolderPath, documentFolderPath, chapterId) VALUES(?, ?, ?, ?, ?)", statement -> {
            statement.setString(1, point.getName());
            statement.setString(2, point.getDescription());
            statement.setString(3, point.getVideoFolderPath());
            statement.setString(4, point.getDocumentFolderPath());
            statement.setInt(5, point.getChapter().getId());
        });

        boolean result = execute.run();
        execute.free();
        return result;
    }

    @Override
    public Point getById(int id) {
        final ChapterDao chapterDao = ChapterDao.getInstance();
        Point user = null;

        SQLQuery<Point> query = new SQLQuery<>("SELECT * FROM point WHERE id = ?", statement -> statement.setInt(1, id), (rs, list) -> {
            if(rs.next())
                list.add(new Point(id, rs.getString("name"),
                        rs.getString("description"),
                        rs.getString("videoFolderPath"),
                        rs.getString("documentFolderPath"),
                        chapterDao.getById(rs.getInt("id"))));
        });

        List<Point> list = query.run();
        if(list.size() > 0)
            user = list.get(0);

        query.free();
        return user;
    }

    @Override
    public List<Point> getByChapter(Chapter chapter) {
        return null;
    }
}
