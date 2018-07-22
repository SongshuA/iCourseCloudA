package util;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public interface FileUtil {
    int MEMORY_THRESHOLD   = 1024 * 1024 * 3;  // 3MB
    int MAX_FILE_SIZE      = 1024 * 1024 * 40; // 40MB
    int MAX_REQUEST_SIZE   = 1024 * 1024 * 50; // 50MB

    static List<String> walkThroughFolder(String path){
        File file = new File(path);

        File[] fs = file.listFiles();

        if(fs == null)
            return null;

        List<String> files = new ArrayList<>();

        for(File f : fs){
            files.add(f.getName());
        }

        return files;
    }

    static List<FileItem> getFileItems(HttpServletRequest request){

        if(!ServletFileUpload.isMultipartContent(request))
            return null;

        // 配置上传参数
        DiskFileItemFactory factory = new DiskFileItemFactory();

        // 设置内存临界值 - 超过后将产生临时文件并存储于临时目录中
        factory.setSizeThreshold(MEMORY_THRESHOLD);

        // 设置临时存储目录
        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

        ServletFileUpload upload = new ServletFileUpload(factory);

        // 设置最大文件上传值
        upload.setFileSizeMax(MAX_FILE_SIZE);

        // 设置最大请求值 (包含文件和表单数据)
        upload.setSizeMax(MAX_REQUEST_SIZE);
        upload.setHeaderEncoding("UTF-8");


        try {
            return upload.parseRequest(request);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }


    static boolean uploadFile(List<FileItem> fileItems, String localDir, String fieldName){
        if(fileItems == null)
            return false;

        File file = new File(localDir);
        if(!file.exists())
            FileUtil.mkdir(file);

        try {
            for(FileItem fileItem : fileItems){
                if(!fileItem.isFormField() && fieldName.equals(fileItem.getFieldName())){
                    String fileName = new File(fileItem.getName()).getName();
                    String extName = fileName.substring(fileName.lastIndexOf(".") + 1);
                    fileName = System.currentTimeMillis() + "." + extName;

                    File storeFile = new File(localDir + File.separator + fileName);
                    fileItem.write(storeFile);
                    return true;
                }
            }
        }catch (Exception e){

            e.printStackTrace();
        }


        return false;
    }


    static String getField(List<FileItem> fileItems, String fieldName){
        try {

            for(FileItem fileItem : fileItems){
                if(fileItem.isFormField() && fieldName.equals(fileItem.getFieldName())){

                    return fileItem.getString("UTF-8");
                }
            }

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return null;
    }


    static boolean mkdir(File file){
        if(!file.exists())
            FileUtil.mkdir(file.getParentFile());
        return file.mkdir();
    }
}
