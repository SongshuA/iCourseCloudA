package dao.impl;

import dao.ChapterDao;
import dao.CourseDao;
import domain.Chapter;
import domain.Course;
import util.JDBCTools.SQLExecute;
import util.JDBCTools.SQLQuery;

import java.util.List;

public class ChapterDaoImpl implements ChapterDao {
    private ChapterDaoImpl(){}

    private static class SingletonFactory{
        private static ChapterDaoImpl singleton = new ChapterDaoImpl();
    }

    public static ChapterDaoImpl getInstance(){
        return SingletonFactory.singleton;
    }

    @Override
    public int create(Chapter chapter) {
        SQLExecute execute = new SQLExecute("INSERT INTO chapter (name, courseId) VALUES(?, ?)", statement -> {
            statement.setString(1, chapter.getName());
            statement.setInt(2, chapter.getCourse().getId());
        });

        return execute.run();
    }

    @Override
    public Chapter getById(int id) {
        final CourseDao courseDao = CourseDao.getInstance();
        Chapter chapter = null;

        SQLQuery<Chapter> query = new SQLQuery<>("SELECT * FROM chapter WHERE id = ?", statement -> statement.setInt(1, id), (rs, list) -> {
            if(rs.next())
                list.add(new Chapter(id, rs.getString("name"), courseDao.getById(rs.getInt("courseId"))));
        });

        List<Chapter> list = query.run();
        if(list.size() > 0)
            chapter = list.get(0);

        return chapter;
    }


    @Override
    public List<Chapter> getByCourse(Course course) {
        CourseDao courseDao = CourseDao.getInstance();

        SQLQuery<Chapter> query = new SQLQuery<>("SELECT * FROM chapter WHERE courseId = ?", statement -> statement.setInt(1, course.getId()), (rs, list) -> {
            while(rs.next())
                list.add(new Chapter(rs.getInt("id"), rs.getString("name"), courseDao.getById(rs.getInt("id"))));
        });

        return query.run();
    }
}
