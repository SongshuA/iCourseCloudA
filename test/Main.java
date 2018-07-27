import domain.Course;
import domain.User;
import exception.ServiceException;
import service.CourseService;
import service.UserService;

import java.util.List;


public class Main {
    public static void main(String[] args) throws ServiceException {
        /*UserService.getInstance().register("admin","admin");
        User admin = UserService.getInstance().queryUser("admin");
        CourseService.getInstance().createCourse(admin.getUsername(), "Java 程序设计", "Java SDK 基础");
        CourseService.getInstance().createCourse(admin.getUsername(), "Web 高级应用", "最新Web技术概览");
        CourseService.getInstance().createCourse(admin.getUsername(), "课程三", "这是课程三的简介");
        CourseService.getInstance().createCourse(admin.getUsername(), "课程四", "这是课程四的简介");
        CourseService.getInstance().createCourse(admin.getUsername(), "课程五", "这是课程五的简介");*/

        List<Course> courses = CourseService.getInstance().searchCourse("Javac", 0, 100);

        for(Course course : courses){
            System.out.println(course.getName());
        }
    }
}
