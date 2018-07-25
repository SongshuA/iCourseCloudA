package service.impl;

import dao.HomeworkDao;
import domain.Answer;
import domain.Course;
import domain.Homework;
import exception.ServiceException;
import service.CourseService;
import service.HomeworkService;

import java.util.List;

public class HomeworkServiceImpl implements HomeworkService {
    private HomeworkDao homeworkDao;

    private HomeworkServiceImpl() {
        homeworkDao = HomeworkDao.getInstance();
    }

    private static class SingletonFactory{
        private static HomeworkServiceImpl singleton = new HomeworkServiceImpl();
    }

    public static HomeworkServiceImpl getInstance(){
        return SingletonFactory.singleton;
    }

    @Override
    public List<Homework> getHomework(int courseId) throws ServiceException {
        Course course = CourseService.getInstance().getCourseById(courseId);

        if(course == null)
            throw new ServiceException("作业对应的课程不存在");


        return homeworkDao.getByCourse(course);
    }


    @Override
    public Homework getHomeworkById(int id) {
        return homeworkDao.getById(id);
    }


    @Override
    public int createHomework(String name, String context, int courseId) throws ServiceException {
        Course course = CourseService.getInstance().getCourseById(courseId);

        if(course == null)
            throw new ServiceException("作业对应的课程不存在");

        return homeworkDao.create(new Homework(0, name,context, course));
    }

    @Override
    public void deleteHomeworkById(int id) {
        homeworkDao.delete(id);
    }

    @Override
    public void updateHomeworkById(int id, String name, String context) throws ServiceException {
        Homework homework = getHomeworkById(id);
        if(homework == null)
            throw new ServiceException("未找到需要修改的作业对象");

        homework.setName(name);
        homework.setContext(context);
        homeworkDao.update(id, homework);
    }
}
