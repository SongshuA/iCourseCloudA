package domain;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

public class User implements Serializable {
    private int id;
    private String username;
    private String password;

    /*Foreign Key*/
    private Set<Course> selectedCourses;
    private Set<Course> createdCourses;

    public User(int id, String username, String password, Set<Course> selectedCourses, Set<Course> createdCourses) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.selectedCourses = selectedCourses;
        this.createdCourses = createdCourses;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Course> getSelectedCourses() {
        return selectedCourses;
    }

    public void setSelectedCourses(Set<Course> selectedCourses) {
        this.selectedCourses = selectedCourses;
    }

    public Set<Course> getCreatedCourses() {
        return createdCourses;
    }

    public void setCreatedCourses(Set<Course> createdCourses) {
        this.createdCourses = createdCourses;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id;
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }
}
