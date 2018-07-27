package service;

import domain.Point;
import exception.ServiceException;
import service.impl.PointServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 知识点操作类
 */
public interface PointService {
    static PointService getInstance() { return PointServiceImpl.getInstance(); }

    /**
     * 创建知识点
     * @param name 知识点名称
     * @param description 知识点介绍
     * @param chapterId 知识点所属章节ID
     * @return 创建的知识点ID
     * @throws ServiceException 操作失败时抛出的异常，通过e.getMessage()获得错误原因
     */
    int createPoint(String name, String description, int chapterId) throws ServiceException;

    /**
     * 取一个章节的所有知识点列表
     * @param chapterId 章节ID
     * @return 知识点对象列表
     * @throws ServiceException 操作失败时抛出的异常，通过e.getMessage()获得错误原因
     */
    List<Point> getPoints(int chapterId) throws ServiceException;

    /**
     * 获取知识点视频文件夹在浏览器中的URL
     * @param pointId 知识点ID
     * @return 视频文件夹URL
     */
    String getVideoFolderURL(int pointId);

    /**
     * 获取知识点文档文件夹在浏览器中的URL
     * @param pointId 知识点ID
     * @return 文档文件夹URL
     */
    String getDocumentFolderURL(int pointId);

    /**
     * 获取知识点视频文件夹在本地的路径
     * @param pointId 知识点ID
     * @return 视频文件夹路径
     */
    String getVideoFolderLocalPath(int pointId);

    /**
     * 获取知识点文档文件夹在本地的路径
     * @param pointId 知识点ID
     * @return 文档文件夹路径
     */
    String getDocumentFolderLocalPath(int pointId);

    /**
     * 取知识点的所有视频文件名列表
     * @param pointId 知识点ID
     * @return 文件名列表
     */
    List<String> getListOfVideoFilename(int pointId);

    /**
     * 取知识点的所有文档文件名列表
     * @param pointId 知识点ID
     * @return 文件名列表
     */
    List<String> getListOfDocumentFilename(int pointId);

    /**
     * 根据ID取知识点对象
     * @param id 知识点ID
     * @return 知识点对象
     */
    Point getPointById(int id);

    /**
     * 删除知识点
     * @param id 要删除的知识点ID
     */
    void deletePointById(int id);

    /**
     * 修改知识点内容
     * @param id 要修改的知识点ID
     * @param name 知识点名称
     * @param description 知识点简介
     * @throws ServiceException 操作失败时抛出的异常，通过e.getMessage()获得错误原因
     */
    void updatePointById(int id, String name, String description) throws ServiceException;
}
