package service.impl;

import dao.BarrageDao;
import domain.Barrage;
import domain.Point;
import domain.User;
import exception.ServiceException;
import service.BarrageService;
import service.PointService;
import service.UserService;

import java.util.List;

public class BarrageServiceImpl implements BarrageService {
    private BarrageDao barrageDao;

    private BarrageServiceImpl() {
        barrageDao = BarrageDao.getInstance();
    }

    private static class SingletonFactory{
        private static BarrageServiceImpl singleton = new BarrageServiceImpl();
    }

    public static BarrageServiceImpl getInstance(){
        return SingletonFactory.singleton;
    }


    @Override
    public List<Barrage> getBarrages(int pointId) throws ServiceException {
        Point point = PointService.getInstance().getPointById(pointId);

        if(point == null)
            throw new ServiceException("弹幕对应的知识点不存在");

        return barrageDao.getByPoint(point);
    }



    @Override
    public int createBarrage(String context, int time, String username, int pointId) throws ServiceException {
        Point point = PointService.getInstance().getPointById(pointId);
        User user = UserService.getInstance().queryUser(username);

        if(point == null)
            throw new ServiceException("弹幕对应的知识点不存在");

        if(user == null)
            throw new ServiceException("弹幕对应的用户不存在");

        return barrageDao.create(new Barrage(0, context, time, user, point));
    }
}
