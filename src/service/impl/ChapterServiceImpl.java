package service.impl;

import dao.ChapterDao;
import domain.Chapter;
import domain.Course;
import exception.ServiceException;
import service.ChapterService;
import service.CourseService;

import java.util.List;

public class ChapterServiceImpl implements ChapterService {

    private ChapterDao chapterDao;

    private ChapterServiceImpl() {
        chapterDao = ChapterDao.getInstance();
    }

    private static class SingletonFactory{
        private static ChapterServiceImpl singleton = new ChapterServiceImpl();
    }

    public static ChapterServiceImpl getInstance(){
        return SingletonFactory.singleton;
    }


    @Override
    public List<Chapter> getChapters(int courseId) throws ServiceException {
        Course course = CourseService.getInstance().getCourseById(courseId);

        if(course == null)
            throw new ServiceException("章节对应的课程不存在！");

        return chapterDao.getByCourse(course);
    }


    @Override
    public int createChapter(String name, int courseId) throws ServiceException {
        Course course = CourseService.getInstance().getCourseById(courseId);

        if(course == null)
            throw new ServiceException("章节对应的课程不存在！");

        return chapterDao.create(new Chapter(0, name, course));

    }

    @Override
    public Chapter getChapterById(int id) {
        return chapterDao.getById(id);
    }
}
