package service;

import service.impl.PointServiceImpl;

public interface PointService {
    static PointService getInstance() { return PointServiceImpl.getInstance(); }
}
