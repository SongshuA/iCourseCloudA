package service.impl;

import dao.AnswerDao;
import domain.Answer;
import domain.Homework;
import domain.User;
import exception.ServiceException;
import service.AnswerService;
import service.HomeworkService;
import service.UserService;

import java.util.ArrayList;
import java.util.List;

public class AnswerServiceImpl implements AnswerService {
    private AnswerDao answerDao;

    private AnswerServiceImpl() {
        answerDao = AnswerDao.getInstance();
    }

    private static class SingletonFactory{
        private static AnswerServiceImpl singleton = new AnswerServiceImpl();
    }

    public static AnswerServiceImpl getInstance(){
        return SingletonFactory.singleton;
    }

    @Override
    public Answer getAnswer(String username, int homeworkId) {
        User user = UserService.getInstance().queryUser(username);
        Homework homework = HomeworkService.getInstance().getHomeworkById(homeworkId);

        if(user == null || homework == null)
            return null;

        return answerDao.getByUserAndCourse(user, homework);
    }

    @Override
    public List<Answer> getAnswersByHomeworkId(int homeworkId) {
        List<Answer> answers = new ArrayList<>();

        Homework homework = HomeworkService.getInstance().getHomeworkById(homeworkId);

        if(homework == null)
            return answers;

        return answerDao.getByHomework(homework);
    }


    @Override
    public int createAnswer(String context, String username, int homeworkId) throws ServiceException {
        if(getAnswer(username, homeworkId) != null)
            throw new ServiceException("该用户已发表过对应作业的答案");

        User user = UserService.getInstance().queryUser(username);
        Homework homework = HomeworkService.getInstance().getHomeworkById(homeworkId);

        if(user == null)
            throw new ServiceException("用户不存在");

        if(homework == null)
            throw new ServiceException("作业不存在");

        Answer answer = new Answer(0, context, 0, user, homework);

        return answerDao.create(answer);
    }

    @Override
    public Answer getAnswerById(int id) {
        return answerDao.getById(id);
    }

    @Override
    public void updateAnswerById(int id, String context) throws ServiceException {
        Answer answer = getAnswerById(id);
        if(answer == null)
            throw new ServiceException("未找到需要修改的答案对象");

        answer.setContext(context);
        answerDao.update(id, answer);
    }

    @Override
    public void examineAnswerById(int id, int score) throws ServiceException {
        Answer answer = getAnswerById(id);
        if(answer == null)
            throw new ServiceException("未找到需要评分的答案对象");

        answer.setScore(score);
        answerDao.update(id, answer);
    }
}
