package controller;


import config.GlobalConfig;
import org.apache.commons.io.IOUtils;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLDecoder;

@WebServlet(name = "Assert", urlPatterns = "/asserts/*")
public class AssertController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String URI = req.getRequestURI();
        URI = URLDecoder.decode(URI, "utf8");

        String prefix = "/asserts/";
        String path = URI.substring(URI.indexOf(prefix) + prefix.length());
        path = String.format("%s/%s", GlobalConfig.assertPath, path);
        String filename = new File(path).getName();
        String contentType = this.getServletContext().getMimeType(path);
        String contentDisposition = "attachment;filename=" + filename;


        if(!(new File(path).exists())){
            resp.sendError(404, "文件 " + URI + " 不存在");
            return;
        }

        FileInputStream input = new FileInputStream(path);

        resp.setHeader("Content-Type", contentType);
        resp.setHeader("Content-Deposition",  contentDisposition);

        ServletOutputStream output = resp.getOutputStream();

        IOUtils.copy(input, output);

        input.close();
    }
}
