package service;

import service.impl.CourseServiceImpl;

public interface CourseService {
    static CourseService getInstance() { return CourseServiceImpl.getInstance(); }
}
