package com.study.page.model;

public class PmsPage {
    /**
     * 论文编号
     */
    private String id;

    /**
     * 学院名称
     */
    private String collegeName;

    /**
     * 论文
     */
    private String pageAuthor;

    /**
     * 论文名称
     */
    private String pageName;

    /**
     * 教师编号
     */
    private String teacherId;

    /**
     * 学生编号
     */
    private String studentNo;

    /**
     * 论文保存路径
     */
    private String savePath;

    /**
     * 备注
     */
    private String comment;

    /**
     * 论文评分
     */
    private Double score;

    /**
     * 评审意见
     */
    private String reviewerComment;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName == null ? null : collegeName.trim();
    }

    public String getPageAuthor() {
        return pageAuthor;
    }

    public void setPageAuthor(String pageAuthor) {
        this.pageAuthor = pageAuthor == null ? null : pageAuthor.trim();
    }

    public String getPageName() {
        return pageName;
    }

    public void setPageName(String pageName) {
        this.pageName = pageName == null ? null : pageName.trim();
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId == null ? null : teacherId.trim();
    }

    public String getStudentNo() {
        return studentNo;
    }

    public void setStudentNo(String studentNo) {
        this.studentNo = studentNo == null ? null : studentNo.trim();
    }

    public String getSavePath() {
        return savePath;
    }

    public void setSavePath(String savePath) {
        this.savePath = savePath == null ? null : savePath.trim();
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment == null ? null : comment.trim();
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public String getReviewerComment() {
        return reviewerComment;
    }

    public void setReviewerComment(String reviewerComment) {
        this.reviewerComment = reviewerComment == null ? null : reviewerComment.trim();
    }
}