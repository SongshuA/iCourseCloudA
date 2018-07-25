package service;

import domain.Answer;
import exception.ServiceException;
import service.impl.AnswerServiceImpl;

public interface AnswerService {
    static AnswerService getInstance() { return AnswerServiceImpl.getInstance(); }

    Answer getAnswer(String username, int homeworkId);

    int createAnswer(String context, String username, int homeworkId) throws ServiceException;

    Answer getAnswerById(int id);

    void updateAnswerById(int id, String context) throws ServiceException;

    void examineAnswerById(int id, int score) throws ServiceException;
}
