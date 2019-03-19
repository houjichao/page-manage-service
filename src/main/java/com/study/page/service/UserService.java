package com.study.page.service;

import com.study.page.model.PmsUser;

public interface UserService {
    /**
     * 保存、更新用户
     *
     * @param user
     * @return
     */
    String mergeUser(PmsUser user);

    /**
     * 查询用户
     *
     * @param id
     * @return
     */
    PmsUser queryUserById(String id);

}
