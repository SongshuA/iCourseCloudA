package service;

import domain.Barrage;
import exception.ServiceException;
import service.impl.BarrageServiceImpl;

import java.util.List;

public interface BarrageService {
    static BarrageService getInstance() { return BarrageServiceImpl.getInstance(); }

    /**
     * 根据知识点ID获取所有的弹幕对象
     * @param pointId 知识点ID
     * @return 弹幕对象集合
     * @throws ServiceException 操作失败时抛出的异常，通过e.getMessage()获得错误原因
     */
    List<Barrage> getBarrages(int pointId) throws ServiceException;

    /**
     * 创建弹幕对象（用于用户创建弹幕）
     * @param context 弹幕内容
     * @param time 在视频中出现的时间
     * @param username 发表者用户名
     * @param pointId 对应的知识点ID
     * @return 创建的弹幕对象ID
     * @throws ServiceException 操作失败时抛出的异常，通过e.getMessage()获得错误原因
     */
    int createBarrage(String context, int time, String username, int pointId) throws ServiceException;
}
