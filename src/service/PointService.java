package service;

import domain.Point;
import exception.ServiceException;
import service.impl.PointServiceImpl;

import java.util.List;

public interface PointService {
    static PointService getInstance() { return PointServiceImpl.getInstance(); }

    void createPoint(String name, String description, String videoFolderPath, String documentFolderPath, int chapterId) throws ServiceException;

    List<Point> getPoints(int chapterId) throws ServiceException;

    List<String> getListOfVideoPath(String videoFolderPath);

    List<String> getListOfDocumentPath(String documentFolderPath);

    Point getPointById(int id);
}
