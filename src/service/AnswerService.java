package service;

import domain.Answer;
import exception.ServiceException;
import service.impl.AnswerServiceImpl;

import java.util.List;

/**
 * （作业的）答案操作类
 */

public interface AnswerService {
    static AnswerService getInstance() { return AnswerServiceImpl.getInstance(); }

    /**
     * 通过用户名与作业对象ID来获取该用户在该作业上的的答案
     * @param username 用户名
     * @param homeworkId 作业对象ID
     * @return 答案对象
     */
    Answer getAnswer(String username, int homeworkId);

    /**
     * 获取与某个具体作业有关的所有答案
     * @param homeworkId 作业对象ID
     * @return 答案对象列表
     */
    List<Answer> getAnswersByHomeworkId(int homeworkId);

    /**
     * 创建答案对象
     * @param context 内容
     * @param username 发表者用户名
     * @param homeworkId 答案对应的作业对象ID
     * @return 创建的答案对象ID
     * @throws ServiceException 操作失败时抛出的异常，通过e.getMessage()获得错误原因
     */
    int createAnswer(String context, String username, int homeworkId) throws ServiceException;

    /**
     * 通过ID获取答案对象
     * @param id ID
     * @return 答案对象
     */
    Answer getAnswerById(int id);


    /**
     * 修改答案内容å
     * @param id 修改的答案ID
     * @param context 内容
     * @throws ServiceException 操作失败时抛出的异常，通过e.getMessage()获得错误原因
     */
    void updateAnswerById(int id, String context) throws ServiceException;


    /**
     * 为答案进行评分（用于老师批改）
     * @param id 答案ID
     * @param score 分数
     * @throws ServiceException 操作失败时抛出的异常，通过e.getMessage()获得错误原因
     */
    void examineAnswerById(int id, int score) throws ServiceException;
}
