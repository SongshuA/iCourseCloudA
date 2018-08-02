package controller;

import domain.Course;
import domain.User;
import exception.ServiceException;
import org.apache.commons.fileupload.FileItem;
import service.CourseService;
import service.UserService;
import util.FileUtil;
import util.JSONResponse;
import util.ServletUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "Course", urlPatterns = "/course")
public class CourseController extends HttpServlet {
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
        Integer courseId = null;

        List<FileItem> fileItems = FileUtil.getFileItems(req);
        if(fileItems == null){
            ServletUtil.sendError(req, resp, "请提供足够的参数");
            return;
        }

        try{
            String username = (String)req.getSession().getAttribute("username");

            courseId = Integer.parseInt(FileUtil.getField(fileItems, "courseId"));

            courseService.checkUpdatePrivilege(username, courseId);


        }catch (NumberFormatException e){
            //do nothing

        } catch (ServiceException e) {
            ServletUtil.sendError(req, resp, e.getMessage());
            return;
        }


        String name = FileUtil.getField(fileItems, "name");
        String description = FileUtil.getField(fileItems, "description");

        if(name == null || name.isEmpty() || description == null || description.isEmpty()){
            ServletUtil.sendError(req, resp, "课程名和课程简介不能为空");
            return;
        }

        try {
            if(courseId != null)
                courseService.updateCourse(courseId, name, description);
            else{
                User user = userService.queryUser((String)req.getSession().getAttribute("username"));
                if(user == null){
                    ServletUtil.sendError(req, resp, "您尚未登录");
                    return;
                }
                courseId = courseService.createCourse(user.getUsername(), name, description);
            }


        } catch (ServiceException e) {
            ServletUtil.sendError(req, resp, e.getMessage());
            return;
        }

        String folderPath = new File(courseService.getCoverLocalPath(courseId)).getParent();
        FileUtil.uploadFile(fileItems, folderPath, "cover", "cover");

        resp.sendRedirect("/detail?courseId=" + courseId);

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int courseId = 0;
        Course course;

        try{
            String username = (String) req.getSession().getAttribute("username");
            User creator = UserService.getInstance().queryUser(username);
            if(creator == null){
                ServletUtil.sendError(req, resp, "您尚未登录");
                return;
            }

            courseId = Integer.parseInt(req.getParameter("courseId"));
            course = courseService.getCourseById(courseId);
            if(course == null){
                ServletUtil.sendError(req, resp, "找不到对应课程");
                return;
            }

            List<Course> createdCourses = courseService.getCreatedCourses((String)req.getSession().getAttribute("username"));
            if(createdCourses == null || !createdCourses.contains(course)){
                ServletUtil.sendError(req, resp, "您无权修改此课程");
                return;
            }

            req.setAttribute("course", course);

        }catch (NumberFormatException e){
            req.setAttribute("course", null);

        } catch (ServiceException e) {
            ServletUtil.sendError(req, resp, "您无权修改此课程");
        }

        req.getRequestDispatcher("/WEB-INF/pages/course.jsp").forward(req, resp);
    }
}
