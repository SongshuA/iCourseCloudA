package service.impl;

import config.GlobalConfig;
import dao.CourseDao;
import dao.SelectDao;
import domain.Course;
import domain.Select;
import domain.User;
import exception.ServiceException;
import service.CourseService;
import service.UserService;
import util.FileUtil;

import java.util.ArrayList;
import java.util.List;

public class CourseServiceImpl implements CourseService {
    private CourseDao courseDao;
    private SelectDao selectDao;

    private CourseServiceImpl() {
        courseDao = CourseDao.getInstance();
        selectDao = SelectDao.getInstance();
    }

    private static class SingletonFactory{
        private static CourseServiceImpl singleton = new CourseServiceImpl();
    }

    public static CourseServiceImpl getInstance(){
        return SingletonFactory.singleton;
    }

    @Override
    public List<Course> getAllCourse() {
        return courseDao.getAll();
    }


    @Override
    public int createCourse(String creatorUsername, String name, String description) throws ServiceException {
        User creator = UserService.getInstance().queryUser(creatorUsername);
        if(creator == null)
            throw new ServiceException("课程对应的创建者用户不存在");

        Course course = new Course(0,name, description, creator);

        return courseDao.create(course);

    }


    @Override
    public void selectCourse(String username, int courseId) throws ServiceException {
        User user = UserService.getInstance().queryUser(username);
        Course course = getCourseById(courseId);

        if(user == null)
            throw new ServiceException("课程对应的选课用户不存在");

        if(course == null)
            throw new ServiceException("课程不存在");

        if(course.getCreator().equals(user))
            throw new ServiceException("不能选择自己开设的课程");

        if(getSelectedCourses(username).contains(course))
            throw new ServiceException("该用户已经选择此课程");

        selectDao.create(new Select(0, user, course));
    }

    @Override
    public String getResourceFolderURL(int courseId) {
        return String.format("/asserts/course/%d/resource/", courseId);
    }

    @Override
    public List<String> getListOfResourceFilename(int courseId) {
        return FileUtil.walkThroughFolder(String.format("%s/course/%d/resource", GlobalConfig.assertPath, courseId));
    }

    @Override
    public String getCoverURL(int courseId) {
        List<String> list = FileUtil.walkThroughFolder(String.format("%s/course/%d/cover", GlobalConfig.assertPath, courseId));

        String filename = null;

        if(list != null){

            for(String item : list){
                String ext = item.substring(item.lastIndexOf(".") + 1).toLowerCase();
                if(ext.equals("jpg") || ext.equals("png"))
                    filename = String.format("/asserts/course/%d/cover/%s", courseId, item);
            }

        }

        if(filename == null)
            return GlobalConfig.defaultCourseIcon;
        else
            return filename;
    }


    @Override
    public List<Course> getSelectedCourses(String username) throws ServiceException {
        User user = UserService.getInstance().queryUser(username);
        if(user == null)
            throw new ServiceException("用户不存在");

        List<Select> records = selectDao.getByUser(user);
        List<Course> courses = new ArrayList<>();

        for(Select select : records){
            courses.add(select.getCourse());
        }

        return courses;
    }

    @Override
    public List<Course> getCreatedCourses(String creatorUsername) throws ServiceException {
        User creator = UserService.getInstance().queryUser(creatorUsername);

        if(creator == null)
            throw new ServiceException("用户不存在");

        return courseDao.getByCreator(creator);
    }

    @Override
    public Course getCourseById(int id) {
        return courseDao.getById(id);
    }
}
