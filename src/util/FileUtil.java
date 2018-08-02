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

    /**
     * 遍历一个文件夹中的所有文件
     * @param path 文件夹路径
     * @return 文件名列表
     */
    static List<String> walkThroughFolder(String path){
        File file = new File(path);

        File[] fs = file.listFiles();

        if(fs == null)
            return new ArrayList<>();

        List<String> files = new ArrayList<>();

        for(File f : fs){
            if(!f.isDirectory() && !f.getName().startsWith("."))
                files.add(f.getName());
        }

        return files;
    }

    /**
     * 获取一个用户提交的包含文件上传的表单的所有数据（包括用户上传的文件）
     * @param request 请求对象
     * @return 文件对象列表（用于之后的uploadFile（保存上传的文件）方法和getField（取表单普通的字段名）方法）
     */
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


    /**
     * 上传文件（将用户上传的文件保存到本地）
     * @param fileItems getFileItems 方法获取的表单数据
     * @param localDir 本地文件夹路径
     * @param fieldName 表单中上传文件的 <input> 标签的 name 属性
     * @param storeFilename 文件存储到本地的文件名，建议使用随机名称： new String(System.currentTimeMillis() + "")
     * @return 上传成功与否
     */
    static boolean uploadFile(List<FileItem> fileItems, String localDir, String fieldName, String storeFilename){
        if(fileItems == null)
            return false;

        File file = new File(localDir);
        if(!file.exists())
            FileUtil.mkdir(file);

        try {
            for(FileItem fileItem : fileItems){
                if(!fileItem.isFormField() && fieldName.equals(fileItem.getFieldName()) && fileItem.getSize() > 0){
                    File storeFile = new File(localDir + File.separator + storeFilename);
                    fileItem.write(storeFile);
                    return true;
                }
            }
        }catch (Exception e){

            e.printStackTrace();
        }


        return false;
    }


    /**
     * 获取表单普通字段名
     * @param fileItems getFileItems 方法获取的表单数据
     * @param fieldName <input> 标签的 name 属性
     * @return 字段值
     */
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


    /**
     * 递归创建文件夹，构建file路径中的所有文件夹
     * 例如：/User/abc/document ，则会依次创建User,abc,document文件夹
     * @param file 文件路径
     * @return 文件夹创建成功与否
     */
    static boolean mkdir(File file){
        if(!file.exists())
            FileUtil.mkdir(file.getParentFile());
        return file.mkdir();
    }
}
