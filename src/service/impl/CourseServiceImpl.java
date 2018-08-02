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
import util.ServletUtil;

import java.io.File;
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
            throw new ServiceException("您还没有登录");

        if(course == null)
            throw new ServiceException("课程不存在");

        if(course.getCreator().equals(user))
            throw new ServiceException("不能选择自己开设的课程");

        if(getSelectedCourses(username).contains(course))
            throw new ServiceException("您已经选择此课程");

        selectDao.create(new Select(0, user, course));
    }

    @Override
    public void dropCourse(String username, int courseId) throws ServiceException {
        User user = UserService.getInstance().queryUser(username);
        Course course = CourseService.getInstance().getCourseById(courseId);

        if(user == null)
            throw new ServiceException("无法找到对应用户");

        if(course == null)
            throw new ServiceException("无法找到对应课程");

        Select select = selectDao.getByUserAndCourse(user, course);

        if(select == null)
            throw new ServiceException("该用户没有选择此课程");

        selectDao.delete(select.getId());
    }

    @Override
    public String getResourceFolderURL(int courseId) {
        return String.format("/assets/course/%d/resource/", courseId);
    }

    @Override
    public String getResourceFolderLocalPath(int courseId) {
        return String.format("%s/course/%d/resource", GlobalConfig.assetPath, courseId);
    }

    @Override
    public List<String> getListOfResourceFilename(int courseId) {
        return FileUtil.walkThroughFolder(getResourceFolderLocalPath(courseId));
    }

    @Override
    public String getCoverURL(int courseId) {
        String localPath = getCoverLocalPath(courseId);
        String url = String.format("/assets/course/%d/cover", courseId);

        File file = new File(localPath);

        if(!file.exists())
            return GlobalConfig.defaultCourseIcon;
        else
            return url;
    }

    @Override
    public String getCoverLocalPath(int courseId) {
        return String.format("%s/course/%d/cover", GlobalConfig.assetPath, courseId);
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

    @Override
    public void deleteCourseById(int id) {
        courseDao.delete(id);
    }

    @Override
    public void updateCourse(int id, String name, String description) throws ServiceException {
        Course course = getCourseById(id);
        if(course == null)
            throw new ServiceException("未找到需要修改的课程对象");

        course.setName(name);
        course.setDescription(description);
        courseDao.update(id, course);
    }

    @Override
    public List<Course> searchCourseByNameOrderByEngagement(String keyword, int skip, int limit) {
        return courseDao.getCoursesByOrderByEngagementByNameLike(keyword, skip, limit);
    }

    @Override
    public List<Course> searchCourseByCreatorNameOrderByEngagement(String keyword, int skip, int limit) {
        return courseDao.getCoursesByOrderByEngagementByCreatorNameLike(keyword, skip, limit);
    }

    @Override
    public List<Course> searchCourseByNameOrderByCreateTime(String keyword, int skip, int limit) {
        return courseDao.getCoursesByOrderByCreateTimeByNameLike(keyword, skip, limit);
    }

    @Override
    public List<Course> searchCourseByCreatorNameOrderByCreateTime(String keyword, int skip, int limit) {
        return courseDao.getCoursesByOrderByCreateTimeByCreatorNameLike(keyword, skip, limit);
    }

    @Override
    public int getSearchCountByName(String keyword) {
        return courseDao.countCoursesByNameLike(keyword);
    }

    @Override
    public int getSearchCountByCreatorName(String keyword) {
        return courseDao.countCoursesByCreatorNameLike(keyword);
    }

    @Override
    public List<Course> getCourseOrderByEngagement(int limit) {
        return courseDao.getCourseOrderByEngagement(limit);
    }

    @Override
    public List<Course> getCourseOrderByCreateTime(int limit) {
        return courseDao.getCourseOrderByTime(limit);
    }

    @Override
    public void checkUpdatePrivilege(String username, int courseId) throws ServiceException {
        User creator = UserService.getInstance().queryUser(username);
        if(creator == null)
            throw new ServiceException("您尚未登录");

        Course course = getCourseById(courseId);

        if(course == null)
            throw new ServiceException("未能找到对应课程");

        List<Course> createdCourses = getCreatedCourses(username);

        if(createdCourses == null || !createdCourses.contains(course)){
            throw new ServiceException("您无权修改此课程");
        }
    }
}
