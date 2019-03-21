package com.study.page;

import com.alibaba.fastjson.JSONObject;
import com.study.page.model.PmsAnswerQuestions;

public class UnitTest {
    public static void main(String[] args) {
        PmsAnswerQuestions pmsAnswerQuestions = new PmsAnswerQuestions();
        //pmsAnswerQuestions.setQuestionName("11111111");
        //pmsAnswerQuestions.setStudentId("1111111111");
		pmsAnswerQuestions.setStudentId("1111111111");
        pmsAnswerQuestions.setTeacherId("222222222");
        pmsAnswerQuestions.setId("a8f4361a-e615-49d8-863a-e3484b278030");
        pmsAnswerQuestions.setAnswer("2222222222");
        System.out.println(JSONObject.toJSONString(pmsAnswerQuestions));
    }
}
