package controller;

import domain.Answer;
import domain.Homework;
import domain.User;
import exception.ServiceException;
import service.AnswerService;
import util.ServletUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "CreateAnswer", urlPatterns = "/createAnswer")
public class CreateAnswerController extends HttpServlet {
    private AnswerService answerService;

    @Override
    public void init() throws ServletException {
        answerService = AnswerService.getInstance();
        super.init();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer answerId = null;

        try{
            answerId = Integer.parseInt(req.getParameter("answerId"));

        }catch (NumberFormatException e){
            //do nothing
        }

        String context = new String(req.getParameter("context").getBytes("ISO8859-1"),"UTF-8");

        if(context.isEmpty()){
            ServletUtil.sendError(req, resp, "回答内容不能为空");
            return;
        }

        try{
            if(answerId != null){
                answerService.updateAnswerById(answerId, context);
            }else{
                Integer homeworkId = Integer.parseInt(req.getParameter("homeworkId"));
                String username = (String)req.getSession().getAttribute("username");
                answerId =  answerService.createAnswer(context, username, homeworkId);
            }
        }catch (ServiceException e){
            ServletUtil.sendError(req, resp, e.getMessage());
            return;

        }catch (NumberFormatException e){
            ServletUtil.sendError(req, resp, "请输入正确的课程ID");
            return;
        }

        Answer answer = answerService.getAnswerById(answerId);

        resp.sendRedirect("/homework?homeworkId=" + answer.getHomework().getId());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Answer answer = null;
        Integer homeworkId = null;

        try{
            Integer answerId = Integer.parseInt(req.getParameter("answerId"));
            answer = answerService.getAnswerById(answerId);

        }catch (NumberFormatException e){
            //do nothing
        }

        try{
            homeworkId = Integer.parseInt(req.getParameter("homeworkId"));

        }catch (NumberFormatException e){
            //do nothing
        }


        req.setAttribute("answer", answer);
        req.setAttribute("homeworkId", homeworkId);


        req.getRequestDispatcher("/WEB-INF/pages/createAnswer.jsp").forward(req, resp);
    }
}
