package controller;

import org.apache.commons.fileupload.FileItem;
import service.PointService;
import util.FileUtil;
import util.ServletUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "PointFile", urlPatterns = "/pointFile")
public class PointFileController extends HttpServlet {
    private PointService pointService;


    @Override
    public void init() throws ServletException {
        pointService = PointService.getInstance();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<FileItem> fileItems = FileUtil.getFileItems(req);

        if(fileItems == null){
            ServletUtil.sendError(req, resp, "请提供参数");
            return;
        }

        String type = FileUtil.getField(fileItems, "type");
        Integer pointId = Integer.parseInt(FileUtil.getField(fileItems, "pointId"));

        String filename = String.valueOf(System.currentTimeMillis());

        if(type.equals("video")){

            FileUtil.uploadFile(fileItems, pointService.getVideoFolderLocalPath(pointId), "video", filename);

        }else if(type.equals("document")){

            FileUtil.uploadFile(fileItems, pointService.getDocumentFolderLocalPath(pointId), "document", filename);
        }

        resp.sendRedirect("/point?pointId=" + pointId);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String type = req.getParameter("type");
        Integer pointId = Integer.parseInt(req.getParameter("pointId"));

        req.setAttribute("type", type);
        req.setAttribute("pointId", pointId);

        if(type.equals("video")){
            req.setAttribute("title", "上传视频");

        }else if(type.equals("document")){
            req.setAttribute("title", "上传文档");
        }

        req.getRequestDispatcher("/WEB-INF/pages/pointFile.jsp").forward(req, resp);
    }
}
