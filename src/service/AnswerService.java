package service;

import service.impl.AnswerServiceImpl;

public interface AnswerService {
    static AnswerService getInstance() { return AnswerServiceImpl.getInstance(); }
}
