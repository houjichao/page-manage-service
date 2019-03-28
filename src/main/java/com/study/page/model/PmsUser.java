package com.study.page.model;

import java.io.Serializable;

public class PmsUser implements Serializable {
    /**
     * 用户表ID，唯一标识
     */
    private String id;

    /**
     * 用户登录名
     */
    private String loginName;

    /**
     * 用户姓名
     */
    private String name;

    /**
     * 用户姓名拼音
     */
    private String pinyin;

    /**
     * 用户拼音首字母
     */
    private String acronym;

    /**
     * 用户电子邮箱，可用于登录
     */
    private String email;

    /**
     * 用户手机号，可用于登录
     */
    private String phoneNo;

    /**
     * 密码
     */
    private String password;

    /**
     * 用户证件号
     */
    private String idCardNumber;

    /**
     * 用户性别
     */
    private Integer gender;

    /**
     * 用户类型
     */
    private Integer userType;

    /**
     * 用户组织ID
     */
    private String userOrg;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 注册时间
     */
    private Long registerTime;

    /**
     * 论文编号
     */
    private Long pageId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName == null ? null : loginName.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin == null ? null : pinyin.trim();
    }

    public String getAcronym() {
        return acronym;
    }

    public void setAcronym(String acronym) {
        this.acronym = acronym == null ? null : acronym.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo == null ? null : phoneNo.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getIdCardNumber() {
        return idCardNumber;
    }

    public void setIdCardNumber(String idCardNumber) {
        this.idCardNumber = idCardNumber == null ? null : idCardNumber.trim();
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public String getUserOrg() {
        return userOrg;
    }

    public void setUserOrg(String userOrg) {
        this.userOrg = userOrg == null ? null : userOrg.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Long registerTime) {
        this.registerTime = registerTime;
    }

    public Long getPageId() {
        return pageId;
    }

    public void setPageId(Long pageId) {
        this.pageId = pageId;
    }
}