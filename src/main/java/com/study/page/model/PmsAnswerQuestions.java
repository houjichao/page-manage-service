package com.study.page.model;

import java.io.Serializable;

public class PmsAnswerQuestions implements Serializable {
    /**
     * 问题唯一标识
     */
    private String id;

    /**
     * 问题名称
     */
    private String questionName;

    /**
     * 学生id
     */
    private String studentId;

    /**
     * 问题解答
     */
    private String answer;

    /**
     * 教师id
     */
    private String teacherId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getQuestionName() {
        return questionName;
    }

    public void setQuestionName(String questionName) {
        this.questionName = questionName == null ? null : questionName.trim();
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId == null ? null : studentId.trim();
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer == null ? null : answer.trim();
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId == null ? null : teacherId.trim();
    }
}