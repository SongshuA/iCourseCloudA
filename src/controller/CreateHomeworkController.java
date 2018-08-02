package controller;

import domain.Homework;
import exception.ServiceException;
import service.HomeworkService;
import util.ServletUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "CreateHomework", urlPatterns = "/createHomework")
public class CreateHomeworkController extends HttpServlet {
    private HomeworkService homeworkService;

    @Override
    public void init() throws ServletException {
        homeworkService = HomeworkService.getInstance();
        super.init();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer homeworkId = null;

        try{
            homeworkId = Integer.parseInt(req.getParameter("homeworkId"));

        }catch (NumberFormatException e){
            //do nothing
        }

        String name = new String(req.getParameter("name").getBytes("ISO8859-1"),"UTF-8");
        String context = new String(req.getParameter("context").getBytes("ISO8859-1"),"UTF-8");

        if(name.isEmpty() || context.isEmpty()){
            ServletUtil.sendError(req, resp, "作业名与作业内容不能为空");
            return;
        }

        try{
            if(homeworkId != null){
                homeworkService.updateHomeworkById(homeworkId, name, context);
            }else{
                Integer courseId = Integer.parseInt(req.getParameter("courseId"));
                homeworkId =  homeworkService.createHomework(name, context, courseId);
            }
        }catch (ServiceException e){
            ServletUtil.sendError(req, resp, e.getMessage());
            return;

        }catch (NumberFormatException e){
            ServletUtil.sendError(req, resp, "请输入正确的课程ID");
            return;
        }

        resp.sendRedirect("/homework?homeworkId=" + homeworkId);

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Homework homework = null;
        Integer courseId = null;

        try{
            Integer homeworkId = Integer.parseInt(req.getParameter("homeworkId"));
            homework = homeworkService.getHomeworkById(homeworkId);

        }catch (NumberFormatException e){
            //do nothing
        }

        try{
            courseId = Integer.parseInt(req.getParameter("courseId"));

        }catch (NumberFormatException e){
            //do nothing
        }


        req.setAttribute("homework", homework);
        req.setAttribute("courseId", courseId);


        req.getRequestDispatcher("/WEB-INF/pages/createHomework.jsp").forward(req, resp);
    }
}
