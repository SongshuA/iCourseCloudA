package dao;

import dao.impl.AnswerDaoImpl;
import domain.Answer;
import domain.Homework;

import java.util.List;

public interface AnswerDao {
    static AnswerDao getInstance(){ return AnswerDaoImpl.getInstance(); }

    boolean create(Answer answer);

    List<Answer> getByHomework(Homework homework);
}
