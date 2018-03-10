package com.ych.core.service.impl;

import com.ych.core.enums.UserStatus;
import com.ych.core.mapper.UserMapper;
import com.ych.core.model.User;
import com.ych.core.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Created by chenhao.ye on 10/03/2018.
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;


    @Override
    public void create(User user) {
        if (ObjectUtils.isEmpty(user.getDeleteFlag())) {
            user.setDeleteFlag(UserStatus.NORMAL.getValue());
        }
        user.setUpdateTime(new Date());
        user.setCreateTime(new Date());
        userMapper.insert(user);
    }

}
