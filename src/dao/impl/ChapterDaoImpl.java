package dao.impl;

import dao.ChapterDao;
import dao.CourseDao;
import domain.Chapter;
import domain.Course;
import util.SQLExecute;
import util.SQLQuery;

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
    public boolean create(Chapter chapter) {
        SQLExecute execute = new SQLExecute("INSERT INTO chapter (name, courseId) VALUES(?, ?)", statement -> {
            statement.setString(1, chapter.getName());
            statement.setInt(2, chapter.getCourse().getId());
        });

        boolean result = execute.run();
        execute.free();
        return result;
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

        query.free();
        return chapter;
    }


    @Override
    public List<Chapter> getByCourse(Course course) {
        CourseDao courseDao = CourseDao.getInstance();

        SQLQuery<Chapter> query = new SQLQuery<>("SELECT * FROM chapter WHERE courseId = ?", statement -> statement.setInt(1, course.getId()), (rs, list) -> {
            while(rs.next())
                list.add(new Chapter(rs.getInt("id"), rs.getString("name"), courseDao.getById(rs.getInt("id"))));
        });

        List<Chapter> list = query.run();
        query.free();
        return list;
    }
}
