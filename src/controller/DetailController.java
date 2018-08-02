package controller;

import domain.*;
import exception.ServiceException;
import service.*;
import util.ServletUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "Detail", urlPatterns = "/detail")
public class DetailController extends HttpServlet {
    private CourseService courseService;
    private ChapterService chapterService;
    private PointService pointService;
    private HomeworkService homeworkService;

    @Override
    public void init() throws ServletException {
        courseService = CourseService.getInstance();
        chapterService = ChapterService.getInstance();
        pointService = PointService.getInstance();
        homeworkService = HomeworkService.getInstance();
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int courseId = 0;
        int frame = 0;
        try{
            courseId = Integer.parseInt(req.getParameter("courseId"));
            String frameStr = req.getParameter("frame");
            if(frameStr != null)
                frame = Integer.parseInt(frameStr);

        }catch (NumberFormatException e){
            ServletUtil.sendError(req, resp, "请输入正确的页面参数");
            return;
        }

        Course course = courseService.getCourseById(courseId);

        if(course == null){
            ServletUtil.sendError(req, resp, "未能找到对应课程");
        }

        List<Chapter> chapters;
        Map<Chapter, List<Point>> points;
        List<Homework> homeworks;
        List<String> resources;
        boolean isCreator = false;
        boolean isSelector = false;

        try {
            homeworks = homeworkService.getHomework(courseId);
            resources = courseService.getListOfResourceFilename(courseId);

            chapters = chapterService.getChapters(courseId);
            points = new HashMap<>();

            for(Chapter chapter : chapters){
                points.put(chapter, pointService.getPoints(chapter.getId()));
            }

            String username = (String) req.getSession().getAttribute("username");

            if(username != null){
                if(courseService.getSelectedCourses(username).contains(course))
                    isSelector = true;

                if(courseService.getCreatedCourses(username).contains(course))
                    isCreator = true;
            }

        } catch (ServiceException e) {
            ServletUtil.sendError(req, resp, e.getMessage());
            return;
        }

        boolean accessibe = isCreator || isSelector;

        req.setAttribute("course", course);
        req.setAttribute("chapters", chapters);
        req.setAttribute("points", points);
        req.setAttribute("cover", courseService.getCoverURL(courseId));
        req.setAttribute("resourceFolderURL", courseService.getResourceFolderURL(courseId));
        req.setAttribute("homeworks", homeworks);
        req.setAttribute("resources", resources);
        req.setAttribute("isSelector", isSelector);
        req.setAttribute("isCreator", isCreator);
        req.setAttribute("accessible", accessibe);
        req.setAttribute("frame", frame);

        req.getRequestDispatcher("/WEB-INF/pages/detail.jsp").forward(req, resp);

    }
}
