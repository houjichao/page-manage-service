package com.study.page.service;

import com.study.page.model.PmsUser;

import java.util.List;

public interface UserService {
    /**
     * 保存、更新用户
     *
     * @param user
     * @return
     */
    PmsUser mergeUser(PmsUser user);

    /**
     * 查询用户
     *
     * @param id
     * @return
     */
    PmsUser queryUserById(String id);

    /**
     * 删除用户
     *
     * @param id
     * @return
     */
    PmsUser delUser(String id);


    /**
     * 根据用户
     *
     * @param type
     * @return
     */
    List<PmsUser> queryUserByType(Integer type);

}
