package dao.impl;

import dao.BarrageDao;
import dao.PointDao;
import dao.UserDao;
import domain.Barrage;
import domain.Point;
import util.JDBCTools.SQLExecute;
import util.JDBCTools.SQLQuery;

import java.util.List;

public class BarrageDaoImpl implements BarrageDao {
    private BarrageDaoImpl(){}

    private static class SingletonFactory{
        private static BarrageDaoImpl singleton = new BarrageDaoImpl();
    }

    public static BarrageDaoImpl getInstance(){
        return SingletonFactory.singleton;
    }

    @Override
    public boolean create(Barrage barrage) {
        SQLExecute execute = new SQLExecute("INSERT INTO barrage (context, time, userId, pointId) VALUES(?, ?, ?, ?)", statement -> {
            statement.setString(1, barrage.getContext());
            statement.setInt(2, barrage.getTime());
            statement.setInt(3, barrage.getUser().getId());
            statement.setInt(4, barrage.getPoint().getId());
        });

        boolean result = execute.run();
        execute.free();
        return result;
    }

    @Override
    public List<Barrage> getByPoint(Point point) {
        UserDao userDao = UserDao.getInstance();
        PointDao pointDao = PointDao.getInstance();

        SQLQuery<Barrage> query = new SQLQuery<>("SELECT * FROM barrage WHERE pointId = ?", statement -> statement.setInt(1, point.getId()), (rs, list) -> {
            while(rs.next())
                list.add(new Barrage(rs.getInt("id"), rs.getString("context"), rs.getInt("time"),
                        userDao.getById(rs.getInt("userId")), pointDao.getById(rs.getInt("pointId"))));
        });

        List<Barrage> list = query.run();
        query.free();
        return list;
    }
}
