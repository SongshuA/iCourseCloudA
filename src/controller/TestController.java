package controller;

import org.apache.commons.fileupload.FileItem;
import service.PointService;
import util.FileUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "Test", urlPatterns = "/test")

public class TestController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();

        List<FileItem> fileItemList = FileUtil.getFileItems(req);
        //对于有上传文件的表单只能用这种方式获取参数
        String pointIdStr = FileUtil.getField(fileItemList, "pointId");

        if(pointIdStr != null){
            int pointId = Integer.parseInt(pointIdStr);

            String newFileName = System.currentTimeMillis() + "";

            if(FileUtil.uploadFile(fileItemList, PointService.getInstance().getDocumentFolderLocalPath(pointId), "file", newFileName)){
                //打印已上传的文件列表

                List<String> fileList = PointService.getInstance().getListOfDocumentFilename(pointId);

                for(String fileName : fileList){
                    out.println(PointService.getInstance().getDocumentFolderURL(pointId) + fileName);
                }
            }

        }else{
            out.println("文件上传失败，请指定 pointId");
        }
    }
}
