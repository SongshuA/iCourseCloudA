package service;

import domain.Chapter;
import exception.ServiceException;
import service.impl.ChapterServiceImpl;

import java.util.List;

public interface ChapterService {
    static ChapterService getInstance() { return ChapterServiceImpl.getInstance(); }

    List<Chapter> getChapters(int courseId) throws ServiceException;

    int createChapter(String name, int courseId) throws ServiceException;

    Chapter getChapterById(int id);

    void deleteChapterById(int id);

    void updateChapterById(int id, String name) throws ServiceException;
}
