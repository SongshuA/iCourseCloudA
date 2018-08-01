package controller;

import domain.Course;
import service.CourseService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "SearchServlet", urlPatterns = "/search")
public class SearchServlet extends HttpServlet {
    private CourseService courseService;

    @Override
    public void init() throws ServletException {
        courseService = CourseService.getInstance();
        super.init();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String keyword = request.getParameter("keyword");
        String order = request.getParameter("order");
        String type = request.getParameter("type");
        int pageNum;

        try{
            pageNum = Integer.parseInt(request.getParameter("pageNum"));
        }catch (NumberFormatException e){
            pageNum = 1;
        }

        keyword = keyword == null ? "" : keyword;
        order = order == null ? "engagement" : order;
        type = type == null ? "name" : type;

        List<Course> result;

        if(!keyword.isEmpty()){
            if(type.equals("name")){
                if(order.equals("engagement"))
                    ;
                else
                    ;
            }else{
                if(order.equals("engagement"))
                    ;
                else
                    ;
            }

        }



        request.setAttribute("keyword", keyword);
        request.setAttribute("order", order);
        request.setAttribute("type", type);

        request.getRequestDispatcher("/WEB-INF/pages/search.jsp").forward(request, response);
    }
}
