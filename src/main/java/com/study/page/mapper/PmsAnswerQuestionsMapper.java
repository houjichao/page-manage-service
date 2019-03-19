package com.study.page.mapper;

import com.study.page.model.PmsAnswerQuestions;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PmsAnswerQuestionsMapper {
    int deleteByPrimaryKey(String id);

    int insert(PmsAnswerQuestions record);

    int insertSelective(PmsAnswerQuestions record);

    PmsAnswerQuestions selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(PmsAnswerQuestions record);

    int updateByPrimaryKey(PmsAnswerQuestions record);

    /**
     * 查询所有问题
     *
     * @return
     */
    List<PmsAnswerQuestions> queryAllQuestion();


    /**
     * 根据学生id查询问题
     *
     * @param studentId
     * @return
     */
    List<PmsAnswerQuestions> queryQuestionByStudentId(@Param(value = "studentId") String studentId);


    /**
     * 根据教师id查询问题
     *
     * @param teacherId
     * @return
     */
    List<PmsAnswerQuestions> queryQuestionByTeacherId(@Param(value = "teacherId") String teacherId);
}