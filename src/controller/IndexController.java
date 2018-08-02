package controller;

import domain.Course;
import service.CourseService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@WebServlet(name = "Index", urlPatterns = "/index")
public class IndexController extends HttpServlet {
    private CourseService courseService;

    @Override
    public void init() throws ServletException {
        courseService = CourseService.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Course> hotCourses = courseService.getCourseOrderByEngagement(4);
        List<Course> newCourses = courseService.getCourseOrderByCreateTime(4);
        Map<Course, String> covers = new HashMap<>();

        for(Course course : hotCourses){
            covers.put(course, courseService.getCoverURL(course.getId()));
        }

        for(Course course : newCourses){
            covers.put(course, courseService.getCoverURL(course.getId()));
        }


        req.setAttribute("hotCourses", hotCourses);
        req.setAttribute("newCourses", newCourses);
        req.setAttribute("covers", covers);

        req.getRequestDispatcher("/WEB-INF/pages/index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
