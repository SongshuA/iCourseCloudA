package controller;

import domain.Homework;
import service.HomeworkService;
import util.ServletUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Homework", urlPatterns = "/homework")
public class HomeworkController extends HttpServlet {
    private HomeworkService homeworkService;

    @Override
    public void init() throws ServletException {
        homeworkService = HomeworkService.getInstance();
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int homeworkId = 0;
        try{
            homeworkId = Integer.parseInt(req.getParameter("homeworkId"));

        }catch (NumberFormatException e){
            ServletUtil.sendError(req, resp, "请输入正确的参数");
        }

        Homework homework = homeworkService.getHomeworkById(homeworkId);

        req.setAttribute("homework", homework);

        req.getRequestDispatcher("/WEB-INF/pages/homework.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
