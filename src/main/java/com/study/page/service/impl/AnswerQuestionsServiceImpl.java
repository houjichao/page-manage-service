package com.study.page.service.impl;

import com.study.page.mapper.PmsAnswerQuestionsMapper;
import com.study.page.model.PmsAnswerQuestions;
import com.study.page.service.AnswerQuestionsService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AnswerQuestionsServiceImpl implements AnswerQuestionsService {

    @Autowired
    PmsAnswerQuestionsMapper pmsAnswerQuestionsMapper;

    @Override
    public String mergeQuestion(PmsAnswerQuestions pmsAnswerQuestions) {
        if (StringUtils.isBlank(pmsAnswerQuestions.getId())) {
            pmsAnswerQuestions.setId(UUID.randomUUID().toString());
            pmsAnswerQuestionsMapper.insert(pmsAnswerQuestions);
        }else{
            pmsAnswerQuestionsMapper.updateByPrimaryKeySelective(pmsAnswerQuestions);
        }
        return pmsAnswerQuestions.getId();
    }

    @Override
    public PmsAnswerQuestions queryQuestionById(String id) {
        return pmsAnswerQuestionsMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<PmsAnswerQuestions> queryAllQuestion() {
        return pmsAnswerQuestionsMapper.queryAllQuestion();
    }

    @Override
    public Boolean delQuestion(String id) {
        pmsAnswerQuestionsMapper.deleteByPrimaryKey(id);
        return true;
    }

    @Override
    public List<PmsAnswerQuestions> queryQuestionByStudentId(String studentId) {
        return pmsAnswerQuestionsMapper.queryQuestionByStudentId(studentId);
    }

    @Override
    public List<PmsAnswerQuestions> queryQuestionByTeacherId(String teacherId) {
        return pmsAnswerQuestionsMapper.queryQuestionByTeacherId(teacherId);
    }
}
