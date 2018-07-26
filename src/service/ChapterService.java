package service;

import domain.Chapter;
import exception.ServiceException;
import service.impl.ChapterServiceImpl;

import java.util.List;

/**
 *  章节操作类
 */
public interface ChapterService {
    static ChapterService getInstance() { return ChapterServiceImpl.getInstance(); }

    /**
     * 获取一个课程的所有章节列表
     * @param courseId 课程ID
     * @return 章节列表
     * @throws ServiceException 操作失败时抛出的异常，通过e.getMessage()获得错误原因
     */
    List<Chapter> getChapters(int courseId) throws ServiceException;

    /**
     * 为一个课程创建特定的章节
     * @param name 章节名
     * @param courseId 课程ID
     * @return 创建的章节ID
     * @throws ServiceException 操作失败时抛出的异常，通过e.getMessage()获得错误原因
     */
    int createChapter(String name, int courseId) throws ServiceException;


    /**
     * 通过ID获取一个章节对象
     * @param id 章节ID
     * @return 章节对象
     */
    Chapter getChapterById(int id);

    /**
     * 删除一个章节对象
     * @param id 要删除的章节对象ID
     */
    void deleteChapterById(int id);

    /**
     * 修改一个章节的内容
     * @param id 要修改的章节ID
     * @param name 新的章节名称
     * @throws ServiceException 操作失败时抛出的异常，通过e.getMessage()获得错误原因
     */
    void updateChapterById(int id, String name) throws ServiceException;
}
