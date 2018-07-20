package domain;

import java.io.Serializable;
import java.util.Objects;

public class Barrage implements Serializable {
    private int id;
    private String context;
    private int time;

    private User user;
    private Point point;

    public Barrage(int id, String context, int time, User user, Point point) {
        this.id = id;
        this.context = context;
        this.time = time;
        this.user = user;
        this.point = point;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Barrage barrage = (Barrage) o;
        return id == barrage.id;
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }
}
