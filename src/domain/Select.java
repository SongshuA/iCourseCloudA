package domain;

import java.util.Objects;

public class Select {
    private int id;

    private User user;
    private Course course;

    public Select(int id, User user, Course course) {
        this.id = id;
        this.user = user;
        this.course = course;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Select select = (Select) o;
        return id == select.id;
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }
}
