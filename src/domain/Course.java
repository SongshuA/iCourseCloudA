package domain;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class Course implements Serializable {
    private int id;
    private String name;
    private String description;
    private String assertFolderPath;

    /* Foreign Key */
    private User creator;
    private Set<User> members;
    private List<Homework> homework;
    private List<Chapter> chapters;

    public Course(int id, String name, String description, String assertFolderPath, User creator, Set<User> members, List<Homework> homework, List<Chapter> chapters) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.assertFolderPath = assertFolderPath;
        this.creator = creator;
        this.members = members;
        this.homework = homework;
        this.chapters = chapters;
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

    public Set<User> getMembers() {
        return members;
    }

    public void setMembers(Set<User> members) {
        this.members = members;
    }

    public List<Homework> getHomework() {
        return homework;
    }

    public void setHomework(List<Homework> homework) {
        this.homework = homework;
    }

    public List<Chapter> getChapters() {
        return chapters;
    }

    public void setChapters(List<Chapter> chapters) {
        this.chapters = chapters;
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
