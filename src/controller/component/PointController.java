package controller.component;

import domain.Chapter;
import domain.User;
import exception.ServiceException;
import service.ChapterService;
import service.CourseService;
import service.PointService;
import service.UserService;
import util.JSONResponse;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "CreatePoint", urlPatterns = "/createPoint")
public class PointController extends HttpServlet {
    private PointService pointService;
    private ChapterService chapterService;
    private UserService userService;
    private CourseService courseService;

    @Override
    public void init() throws ServletException {
        pointService = PointService.getInstance();
        chapterService = ChapterService.getInstance();
        userService = UserService.getInstance();
        courseService = CourseService.getInstance();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("utf8");

        PrintWriter out = resp.getWriter();
        User user = userService.queryUser((String) req.getSession().getAttribute("username"));

        if(user == null){
            out.print(new JSONResponse(false, "您尚未登录"));
            return;
        }

        String name = req.getParameter("name");
        String description = req.getParameter("description");

        Integer courseId = null;
        Integer chapterId;
        Integer pointId = null;

        try{
            chapterId = Integer.parseInt(req.getParameter("chapterId"));
            Chapter chapter = chapterService.getChapterById(chapterId);
            if(chapter == null){
                out.print(new JSONResponse(false, "未能找到章节"));
                return;
            }
            courseId = chapter.getCourse().getId();

        }catch (NumberFormatException e){
            out.print(new JSONResponse(false, "请提供章节ID"));
            return;
        }



        try{
            if(name == null || name.isEmpty()){
                out.print(new JSONResponse(false, "知识点名不能为空"));
                return;
            }

            courseService.checkUpdatePrivilege(user.getUsername(), courseId);

            pointId = Integer.parseInt(req.getParameter("pointId"));

        }catch (NumberFormatException e){
            //do nothing

        } catch (ServiceException e) {
            out.print(new JSONResponse(false, e.getMessage()));
            return;
        }


        try{
            if(pointId == null){
                //创建知识点
                pointService.createPoint(name, description, chapterId);

            }else{
                //修改章节
                pointService.updatePointById(pointId, name, description);
            }
        }catch (ServiceException e){
            out.print(new JSONResponse(false, e.getMessage()));
            return;
        }


        if(pointId == null)
            out.print(new JSONResponse(true, "知识点创建成功"));
        else
            out.print(new JSONResponse(true, "知识点修改成功"));

    }
}
