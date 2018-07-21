package service;

import domain.Barrage;
import exception.ServiceException;
import service.impl.BarrageServiceImpl;

import java.util.List;

public interface BarrageService {
    static BarrageService getInstance() { return BarrageServiceImpl.getInstance(); }

    List<Barrage> getBarrages(int pointId) throws ServiceException;

    void createBarrage(String context, int time, String username, int pointId) throws ServiceException;
}
