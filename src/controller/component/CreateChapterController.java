package controller.component;

import domain.User;
import exception.ServiceException;
import service.ChapterService;
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

@WebServlet(name = "Chapter", urlPatterns = "/createChapter")
public class CreateChapterController extends HttpServlet {
    private ChapterService chapterService;
    private UserService userService;
    private CourseService courseService;

    @Override
    public void init() throws ServletException {
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
        Integer chapterId = null;
        Integer courseId;

        try{
            courseId = Integer.parseInt(req.getParameter("courseId"));

        }catch (NumberFormatException e){
            out.print(new JSONResponse(false, "请提供课程ID"));
            return;
        }


        try{
            if(name == null || name.isEmpty()){
                out.print(new JSONResponse(false, "章节名不能为空"));
                return;
            }


            courseService.checkUpdatePrivilege(user.getUsername(), courseId);

            chapterId = Integer.parseInt(req.getParameter("chapterId"));

        }catch (NumberFormatException e){
            //do nothing

        } catch (ServiceException e) {
            out.print(new JSONResponse(false, e.getMessage()));
            return;
        }


        try{
            if(chapterId == null){
                //创建章节
                chapterService.createChapter(name, courseId);

            }else{
                //修改章节
                chapterService.updateChapterById(chapterId, name);
            }
        }catch (ServiceException e){
            out.print(new JSONResponse(false, e.getMessage()));
            return;
        }


        if(chapterId == null)
            out.print(new JSONResponse(true, "章节创建成功"));
        else
            out.print(new JSONResponse(true, "章节修改成功"));

    }
}
