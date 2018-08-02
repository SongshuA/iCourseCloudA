package controller;

import domain.Course;
import service.CourseService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "SearchServlet", urlPatterns = "/search")
public class SearchServlet extends HttpServlet {
    private final static int pageSize = 10;

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

        int skip = (pageNum - 1) * pageSize;

        keyword = keyword == null ? "" : keyword;
        order = order == null ? "engagement" : order;
        type = type == null ? "name" : type;

        List<Course> result = new ArrayList<>();
        int count = 0;

        if(!keyword.isEmpty()){
            if(type.equals("name")){
                if(order.equals("engagement"))
                    result = courseService.searchCourseByNameOrderByEngagement(keyword, skip, pageSize);
                else
                    result = courseService.searchCourseByNameOrderByCreateTime(keyword, skip, pageSize);

                count = courseService.getSearchCountByName(keyword);

            }else{
                if(order.equals("engagement"))
                    result = courseService.searchCourseByCreatorNameOrderByEngagement(keyword, skip, pageSize);
                else
                    result = courseService.searchCourseByCreatorNameOrderByCreateTime(keyword, skip, pageSize);

                count = courseService.getSearchCountByCreatorName(keyword);
            }
        }

        Map<Course,String> covers = new HashMap<>();

        for(Course course : result){
            covers.put(course, courseService.getCoverURL(course.getId()));
        }

        int pageCount =  (count > 0) ? ((count - 1) / pageSize + 1) : 0;


        request.setAttribute("keyword", keyword);
        request.setAttribute("order", order);
        request.setAttribute("type", type);
        request.setAttribute("result", result);
        request.setAttribute("covers", covers);
        request.setAttribute("pageNum", pageNum);
        request.setAttribute("pageCount", pageCount);

        request.getRequestDispatcher("/WEB-INF/pages/search.jsp").forward(request, response);
    }
}
