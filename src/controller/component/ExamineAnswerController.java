package controller.component;

import exception.ServiceException;
import service.AnswerService;
import util.JSONResponse;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ExamineAnswer", urlPatterns = "/examineAnswer")
public class ExamineAnswerController extends HttpServlet {
    private AnswerService answerService;

    @Override
    public void init() throws ServletException {
        answerService = AnswerService.getInstance();
        super.init();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("utf8");

        PrintWriter out = resp.getWriter();

        try {
            Integer answerId = Integer.parseInt(req.getParameter("answerId"));
            Integer score = Integer.parseInt(req.getParameter("score"));
            answerService.examineAnswerById(answerId, score);

        } catch (ServiceException e) {
            out.print(new JSONResponse(false, e.getMessage()));
            return;

        }catch (NumberFormatException e){
            out.print(new JSONResponse(false, "请输入正确的分数"));
            return;
        }

        out.print(new JSONResponse(true, "评分成功"));
    }
}
