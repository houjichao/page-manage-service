package com.study.page.mapper;

import com.study.page.model.PmsUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public interface PmsUserMapper {
    int deleteByPrimaryKey(String id);

    int insert(PmsUser record);

    int insertSelective(PmsUser record);

    PmsUser selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(PmsUser record);

    int updateByPrimaryKey(PmsUser record);

    /**
     * 查询所有用户
     *
     * @return
     */
    List<PmsUser> queryAllUser();

    /**
     * 查询多个
     *
     * @param ids
     * @return
     */
    List<PmsUser> queryUserByIds(@Param(value = "ids") Set<String> ids);


    PmsUser  checkLoginUserByName(@Param("user") PmsUser pmsUser);
}