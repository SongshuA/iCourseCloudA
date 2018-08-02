package controller;

import org.apache.commons.fileupload.FileItem;
import service.CourseService;
import util.FileUtil;
import util.ServletUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "Resource", urlPatterns = "/resource")
public class ResourceController extends HttpServlet {
    private CourseService courseService;

    @Override
    public void init() throws ServletException {
        courseService = CourseService.getInstance();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<FileItem> fileItems = FileUtil.getFileItems(req);

        if(fileItems == null){
            ServletUtil.sendError(req, resp, "请输入有效参数");
            return;
        }

        Integer courseId = Integer.parseInt(FileUtil.getField(fileItems, "courseId"));

        String filename = FileUtil.getField(fileItems, "name");

        if(filename == null || filename.isEmpty()){
            ServletUtil.sendError(req, resp, "资源名不能为空");
            return;
        }

        FileUtil.uploadFile(fileItems, courseService.getResourceFolderLocalPath(courseId), "file", filename);

        resp.sendRedirect("/detail?frame=2&courseId=" + courseId);

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer courseId = Integer.parseInt(req.getParameter("courseId"));

        req.setAttribute("courseId", courseId);

        req.getRequestDispatcher("/WEB-INF/pages/resource.jsp").forward(req, resp);
    }
}
