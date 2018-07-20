package service;

import service.impl.HomeworkServiceImpl;

public interface HomeworkService {
    static HomeworkService getInstance() { return HomeworkServiceImpl.getInstance(); }
}
