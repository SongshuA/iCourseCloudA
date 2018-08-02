package service.impl;

import config.GlobalConfig;
import dao.PointDao;
import domain.Chapter;
import domain.Point;
import exception.ServiceException;
import service.ChapterService;
import service.PointService;
import util.FileUtil;

import java.util.List;

public class PointServiceImpl implements PointService {
    private PointDao pointDao;

    private PointServiceImpl() {
        pointDao = PointDao.getInstance();
    }

    private static class SingletonFactory{
        private static PointServiceImpl singleton = new PointServiceImpl();
    }

    public static PointServiceImpl getInstance(){
        return SingletonFactory.singleton;
    }

    @Override
    public int createPoint(String name, String description, int chapterId) throws ServiceException {
        Chapter chapter = ChapterService.getInstance().getChapterById(chapterId);

        if(chapter == null)
            throw new ServiceException("知识点对应的章节不存在");

        return pointDao.create(new Point(0, name, description, chapter));
    }


    @Override
    public List<Point> getPoints(int chapterId) throws ServiceException {
        Chapter chapter = ChapterService.getInstance().getChapterById(chapterId);

        if(chapter == null)
            throw new ServiceException("知识点对应的章节不存在");

        return pointDao.getByChapter(chapter);
    }

    @Override
    public String getVideoFolderURL(int pointId) {
        return String.format("/assets/point/%d/video/", pointId);
    }

    @Override
    public String getDocumentFolderURL(int pointId) {
        return String.format("/assets/point/%d/document/", pointId);
    }

    @Override
    public String getVideoFolderLocalPath(int pointId) {
        return String.format("%s/point/%d/video", GlobalConfig.assetPath, pointId);
    }

    @Override
    public String getDocumentFolderLocalPath(int pointId) {
        return String.format(String.format("%s/point/%d/document", GlobalConfig.assetPath, pointId));
    }


    @Override
    public List<String> getListOfVideoFilename(int pointId) {
        return FileUtil.walkThroughFolder(getVideoFolderLocalPath(pointId));
    }

    @Override
    public List<String> getListOfDocumentFilename(int pointId) {
        return FileUtil.walkThroughFolder(getDocumentFolderLocalPath(pointId));
    }


    @Override
    public Point getPointById(int id) {
        return pointDao.getById(id);
    }


    @Override
    public void deletePointById(int id) {
        pointDao.delete(id);
    }

    @Override
    public void updatePointById(int id, String name, String description) throws ServiceException {
        Point point = getPointById(id);
        if(point == null)
            throw new ServiceException("未找到需要修改的知识点对象");

        point.setName(name);
        point.setDescription(description);
        pointDao.update(id, point);
    }
}
