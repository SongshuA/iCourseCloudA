package controller.component;

import domain.Course;
import domain.User;
import exception.ServiceException;
import service.CourseService;
import service.UserService;
import util.JSONResponse;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "Select", urlPatterns = "/select")
public class SelectController extends HttpServlet {
    private CourseService courseService;
    private UserService userService;

    @Override
    public void init() throws ServletException {
        courseService = CourseService.getInstance();
        userService = UserService.getInstance();
        super.init();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json;charset=utf8");
        PrintWriter out = resp.getWriter();

        String username = (String) req.getSession().getAttribute("username");

        int courseId;

        try{
            courseId = Integer.parseInt(req.getParameter("courseId"));
            courseService.selectCourse(username, courseId);

        }catch (NumberFormatException e){
            out.print(new JSONResponse(false, "请输入正确的课程ID"));
            return;

        } catch (ServiceException e) {
            out.print(new JSONResponse(false, e.getMessage()));
            return;
        }

        out.print(new JSONResponse(true, "选课成功"));
    }
}
