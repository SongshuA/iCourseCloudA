package service;

import service.impl.BarrageServiceImpl;

public interface BarrageService {
    static BarrageService getInstance() { return BarrageServiceImpl.getInstance(); }
}
