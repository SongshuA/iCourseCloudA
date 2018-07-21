package service.impl;

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
    public void createPoint(String name, String description, String videoFolderPath, String documentFolderPath, int chapterId) throws ServiceException {
        Chapter chapter = ChapterService.getInstance().getChapterById(chapterId);

        if(chapter == null)
            throw new ServiceException("知识点对应的章节不存在");

        pointDao.create(new Point(0, name, description, videoFolderPath, documentFolderPath, chapter));
    }


    @Override
    public List<Point> getPoints(int chapterId) throws ServiceException {
        Chapter chapter = ChapterService.getInstance().getChapterById(chapterId);

        if(chapter == null)
            throw new ServiceException("知识点对应的章节不存在");

        return pointDao.getByChapter(chapter);
    }

    @Override
    public List<String> getListOfVideoPath(String videoFolderPath) {
        return FileUtil.walkThroughFolder(videoFolderPath);
    }

    @Override
    public List<String> getListOfDocumentPath(String documentFolderPath) {
        return FileUtil.walkThroughFolder(documentFolderPath);
    }

    @Override
    public Point getPointById(int id) {
        return pointDao.getById(id);
    }
}
