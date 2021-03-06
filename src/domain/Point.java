package domain;

import java.io.Serializable;


public class Point implements Serializable {
    private int id;
    private String name;
    private String description;

    private Chapter chapter;

    public Point(int id, String name, String description, Chapter chapter) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.chapter = chapter;
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

    public Chapter getChapter() {
        return chapter;
    }

    public void setChapter(Chapter chapter) {
        this.chapter = chapter;
    }
}

