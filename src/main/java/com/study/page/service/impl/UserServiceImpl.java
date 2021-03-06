package com.study.page.service.impl;

import com.study.page.enums.UserStatusEnum;
import com.study.page.mapper.PmsUserMapper;
import com.study.page.model.PmsUser;
import com.study.page.service.UserService;
import com.study.page.util.PinYinUtil;
import liquibase.util.MD5Util;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    PmsUserMapper userMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public PmsUser mergeUser(PmsUser user) {
        //密码两次MD5加密入库
        user.setPassword(MD5Util.computeMD5(user.getPassword()));
        if (StringUtils.isNotBlank(user.getName())) {
            PinYinUtil.PinYinMsg pinYinMsg = PinYinUtil.getPinYinString(user.getName());
            user.setPinyin(pinYinMsg.pinyin);
            user.setAcronym(pinYinMsg.acronym);
        }
        user.setRegisterTime(System.currentTimeMillis());
        user.setStatus(UserStatusEnum.AVAILABLE.getCode());
        if (StringUtils.isBlank(user.getId())) {
            user.setId(UUID.randomUUID().toString());
            userMapper.insert(user);
        } else {
            userMapper.updateByPrimaryKeySelective(user);
        }
        return user;
    }

    @Override
    public PmsUser queryUserById(String id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public PmsUser delUser(String id) {
        PmsUser pmsUser = userMapper.selectByPrimaryKey(id);
        userMapper.deleteByPrimaryKey(id);
        return pmsUser;
    }

    @Override
    public List<PmsUser> queryUserByType(Integer type) {
        return userMapper.queryUserByType(type);
    }

}
