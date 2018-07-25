package service;

import domain.Homework;
import exception.ServiceException;
import service.impl.HomeworkServiceImpl;

import java.util.List;

public interface HomeworkService {
    static HomeworkService getInstance() { return HomeworkServiceImpl.getInstance(); }

    List<Homework> getHomework(int courseId) throws ServiceException;

    Homework getHomeworkById(int id);

    int createHomework(String name, String context, int courseId) throws ServiceException;

    void deleteHomeworkById(int id);

    void updateHomeworkById(int id, String name, String context) throws ServiceException;
}
