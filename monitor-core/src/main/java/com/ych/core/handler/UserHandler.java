package com.ych.core.handler;

import com.ych.core.dto.UserDto;
import com.ych.core.enums.UserStatus;
import com.ych.core.model.User;
import com.ych.core.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by chenhao.ye on 10/03/2018.
 */
@Component
public class UserHandler {

    @Resource
    private UserService userService;


    public void create(UserDto userDTO) {
        User user = new User();
        BeanUtils.copyProperties(userDTO, user);
        userService.create(user);
    }

    public UserDto findByName(String userName, UserStatus userStatus) {
        User user = userService.findByName(userName, userStatus);
        UserDto userDTO = new UserDto();
        BeanUtils.copyProperties(user, userDTO);
        return userDTO;
    }

}
