package controller;

import domain.Chapter;
import domain.Course;
import domain.Homework;
import domain.Point;
import exception.ServiceException;
import service.ChapterService;
import service.CourseService;
import service.PointService;
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

@WebServlet(name = "Point", urlPatterns = "/point")
public class PointController extends HttpServlet {
    private PointService pointService;
    private ChapterService chapterService;
    private CourseService courseService;

    @Override
    public void init() throws ServletException {
        pointService = PointService.getInstance();
        chapterService = ChapterService.getInstance();
        courseService = CourseService.getInstance();
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int pointId;
        try{
            pointId = Integer.parseInt(req.getParameter("pointId"));

        }catch (NumberFormatException e){
            ServletUtil.sendError(req, resp, "请输入正确的参数");
            return;
        }

        Point point = pointService.getPointById(pointId);

        if(point == null){
            ServletUtil.sendError(req, resp, "未能找到对应的知识点");
            return;
        }

        List<String> videos = pointService.getListOfVideoFilename(point.getId());
        List<String> documents = pointService.getListOfDocumentFilename(point.getId());

        List<Chapter> chapters;
        Map<Chapter, List<Point>> points;

        Course course = point.getChapter().getCourse();

        boolean isCreator = false;
        boolean isSelector = false;



        try{
            String username = (String)(req.getSession().getAttribute("username"));
            List<Course> selectedCourses = courseService.getSelectedCourses(username);
            List<Course> createdCourses = courseService.getCreatedCourses(username);
            if(selectedCourses.contains(course))
                isSelector = true;

            if(createdCourses.contains(course))
                isCreator = true;
        }catch (ServiceException e){
            //do nothing
        }

        boolean accessible = isCreator || isSelector;

        try {

            chapters = chapterService.getChapters(course.getId());
            points = new HashMap<>();
            for(Chapter chapter : chapters){
                points.put(chapter, pointService.getPoints(chapter.getId()));
            }


        } catch (ServiceException e) {
            ServletUtil.sendError(req, resp, e.getMessage());
            return;
        }

        req.setAttribute("point", point);
        req.setAttribute("videoFolderURL", pointService.getVideoFolderURL(pointId));
        req.setAttribute("videos", videos);
        req.setAttribute("documentFolderURL", pointService.getDocumentFolderURL(pointId));
        req.setAttribute("documents", documents);
        req.setAttribute("chapters", chapters);
        req.setAttribute("points", points);
        req.setAttribute("isCreator", isCreator);
        req.setAttribute("isSelector", isSelector);
        req.setAttribute("accessible", accessible);
        req.setAttribute("course", course);

        req.getRequestDispatcher("/WEB-INF/pages/point.jsp").forward(req, resp);
    }
}
