package service;

import domain.Point;
import exception.ServiceException;
import service.impl.PointServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface PointService {
    static PointService getInstance() { return PointServiceImpl.getInstance(); }

    int createPoint(String name, String description, int chapterId) throws ServiceException;

    List<Point> getPoints(int chapterId) throws ServiceException;

    String getVideoFolderURL(int pointId);

    String getDocumentFolderURL(int pointId);

    String getVideoFolderLocalPath(int pointId);

    String getDocumentFolderLocalPath(int pointId);

    List<String> getListOfVideoFilename(int pointId);

    List<String> getListOfDocumentFilename(int pointId);

    Point getPointById(int id);

    void deletePointById(int id);

    void updatePointById(int id, String name, String description) throws ServiceException;
}
