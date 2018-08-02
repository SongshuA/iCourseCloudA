package controller;

import domain.Course;
import domain.User;
import exception.ServiceException;
import service.CourseService;
import service.UserService;
import util.ServletUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "Personal", urlPatterns = "/personal")
public class PersonalController extends HttpServlet {
    private UserService userService;
    private CourseService courseService;

    @Override
    public void init() throws ServletException {
        userService = UserService.getInstance();
        courseService = CourseService.getInstance();
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = userService.queryUser((String) req.getSession().getAttribute("username"));

        if(user == null){
            ServletUtil.sendError(req, resp, "您还没有登录！");
            return;
        }

        int frame = 0;
        List<Course> createdCourses = new ArrayList<>();
        List<Course> selectedCourses = new ArrayList<>();
        Map<Course, String> covers = new HashMap<>();
        try{
            frame = Integer.parseInt(req.getParameter("frame"));
            createdCourses = courseService.getCreatedCourses(user.getUsername());
            selectedCourses = courseService.getSelectedCourses(user.getUsername());

            for(Course course : createdCourses)
                covers.put(course, courseService.getCoverURL(course.getId()));

            for(Course course : selectedCourses)
                covers.put(course, courseService.getCoverURL(course.getId()));

        }catch (NumberFormatException e){
            frame = 0;

        }catch (ServiceException e) {
            ServletUtil.sendError(req, resp, e.getMessage());
        }

        req.setAttribute("user", user);
        req.setAttribute("frame", frame);
        req.setAttribute("createdCourses", createdCourses);
        req.setAttribute("selectedCourses", selectedCourses);
        req.setAttribute("covers", covers);
        req.getRequestDispatcher("/WEB-INF/pages/personal.jsp").forward(req, resp);
    }
}
