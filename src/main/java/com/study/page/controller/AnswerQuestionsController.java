package com.study.page.controller;

import com.study.page.model.PmsAnswerQuestions;
import com.study.page.service.AnswerQuestionsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/question")
@Slf4j
@Api(value = "答疑模块API", description = "答疑模块API")
public class AnswerQuestionsController {
    @Autowired
    AnswerQuestionsService answerQuestionsService;

    @ApiOperation(value = "新增问题")
    @PostMapping(value = "/add")
    public String saveQuestion(@RequestBody PmsAnswerQuestions answerQuestions) {
        return answerQuestionsService.mergeQuestion(answerQuestions);
    }

    @ApiOperation(value = "修改问题，回答")
    @PostMapping(value = "/update")
    public String updateQuestion(@RequestBody PmsAnswerQuestions answerQuestions) {
        return answerQuestionsService.mergeQuestion(answerQuestions);
    }

    @ApiOperation(value = "查询问题")
    @GetMapping(value = "/get/{id}")
    public PmsAnswerQuestions queryQuestion(@PathVariable String id) {
        return answerQuestionsService.queryQuestionById(id);
    }

    @ApiOperation(value = "删除问题")
    @DeleteMapping(value = "/del/{id}")
    public Boolean delQuestion(@PathVariable String id) {
        return answerQuestionsService.delQuestion(id);
    }

    @ApiOperation(value = "查询所有问题")
    @GetMapping(value = "/getAll")
    public List<PmsAnswerQuestions> queryAllQuestion() {
        return answerQuestionsService.queryAllQuestion();
    }

    @ApiOperation(value = "根据学生id查询问题")
    @GetMapping(value = "/queryByStudentId/{studentId}")
    public List<PmsAnswerQuestions> queryQuestionByStudentId(@PathVariable String studentId) {
        return answerQuestionsService.queryQuestionByStudentId(studentId);
    }

    @ApiOperation(value = "根据学生id查询问题")
    @GetMapping(value = "/queryByTeacherId/{teacherId}")
    public List<PmsAnswerQuestions> queryQuestionByTeacherId(@PathVariable String teacherId) {
        return answerQuestionsService.queryQuestionByTeacherId(teacherId);
    }

}
