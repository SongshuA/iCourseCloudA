package controller;

import domain.Answer;
import domain.Course;
import domain.Homework;
import exception.ServiceException;
import service.AnswerService;
import service.CourseService;
import service.HomeworkService;
import util.ServletUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "Homework", urlPatterns = "/homework")
public class HomeworkController extends HttpServlet {
    private HomeworkService homeworkService;
    private AnswerService answerService;
    private CourseService courseService;

    @Override
    public void init() throws ServletException {
        homeworkService = HomeworkService.getInstance();
        answerService = AnswerService.getInstance();
        courseService = CourseService.getInstance();
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

        boolean isCreator = false;
        boolean isSelector = false;

        String username = (String)req.getSession().getAttribute("username");

        try{
            Course course = homework.getCourse();
            List<Course> selectedCourses = courseService.getSelectedCourses(username);
            List<Course> createdCourses = courseService.getCreatedCourses(username);
            if(selectedCourses.contains(course))
                isSelector = true;

            if(createdCourses.contains(course))
                isCreator = true;

        }catch (NullPointerException | ServiceException e){
            //do nothing
        }

        boolean accessible = isCreator || isSelector;

        Answer answer = answerService.getAnswer(username, homeworkId);
        List<Answer> answers = answerService.getAnswersByHomeworkId(homeworkId);

        req.setAttribute("homework", homework);
        req.setAttribute("answer", answer);
        req.setAttribute("answers", answers);
        req.setAttribute("isCreator", isCreator);
        req.setAttribute("isSelector", isSelector);
        req.setAttribute("accessible", accessible);


        req.getRequestDispatcher("/WEB-INF/pages/homework.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
