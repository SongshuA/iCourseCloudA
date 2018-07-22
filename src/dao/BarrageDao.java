package dao;

import dao.impl.BarrageDaoImpl;
import domain.Barrage;
import domain.Point;

import java.util.List;

public interface BarrageDao {
    static BarrageDao getInstance(){ return BarrageDaoImpl.getInstance(); }

    int create(Barrage barrage);

    List<Barrage> getByPoint(Point point);
}
