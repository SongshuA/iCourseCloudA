package service;

import domain.Answer;
import exception.ServiceException;
import service.impl.AnswerServiceImpl;

public interface AnswerService {
    static AnswerService getInstance() { return AnswerServiceImpl.getInstance(); }

    Answer getAnswer(String username, int homeworkId);

    int createAnswer(String context, String username, int homeworkId) throws ServiceException;
}
