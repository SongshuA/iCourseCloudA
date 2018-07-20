package domain;

import java.io.Serializable;
import java.util.Objects;

public class Course implements Serializable {
    private int id;
    private String name;
    private String description;
    private String assertFolderPath;

    private User creator;

    public Course(int id, String name, String description, String assertFolderPath, User creator) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.assertFolderPath = assertFolderPath;
        this.creator = creator;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAssertFolderPath() {
        return assertFolderPath;
    }

    public void setAssertFolderPath(String assertFolderPath) {
        this.assertFolderPath = assertFolderPath;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return id == course.id;
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }
}
