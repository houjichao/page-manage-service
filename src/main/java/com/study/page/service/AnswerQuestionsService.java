package com.study.page.service;

import com.study.page.model.PmsAnswerQuestions;

import java.util.List;

public interface AnswerQuestionsService {
    /**
     * 新增修改问题
     *
     * @param pmsAnswerQuestions
     * @return
     */
    String mergeQuestion(PmsAnswerQuestions pmsAnswerQuestions);

    /**
     * 根据ID查询问题
     *
     * @param id
     * @return
     */
    PmsAnswerQuestions queryQuestionById(String id);


    /**
     * 查询所有问题
     *
     * @return
     */
    List<PmsAnswerQuestions> queryAllQuestion();


    /**
     * 删除问题
     *
     * @param id
     * @return
     */
    Boolean delQuestion(String id);


    /**
     * 根据学生id查询该学生的所有问题
     *
     * @return
     */
    List<PmsAnswerQuestions> queryQuestionByStudentId(String studentId);


    /**
     * 根据教师id查询学生的所有问题
     *
     * @return
     */
    List<PmsAnswerQuestions> queryQuestionByTeacherId(String teacherId);

}
