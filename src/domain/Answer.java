package domain;

import java.io.Serializable;
import java.util.Objects;

public class Answer implements Serializable {
    private int id;
    private String context;
    private int score;

    private User user;
    private Homework homework;

    public Answer(int id, String context, int score, User user, Homework homework) {
        this.id = id;
        this.context = context;
        this.score = score;
        this.user = user;
        this.homework = homework;
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

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Homework getHomework() {
        return homework;
    }

    public void setHomework(Homework homework) {
        this.homework = homework;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Answer answer = (Answer) o;
        return id == answer.id;
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }
}
