package com.ych.gateway.controller;

import com.ych.core.common.Response;
import com.ych.core.dto.UserDTO;
import com.ych.core.enums.UserStatus;
import com.ych.core.enums.user.UserResponseEnum;
import com.ych.core.exception.BizException;
import com.ych.core.handler.UserHandler;
import com.ych.gateway.helper.PasswordHelper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Created by chenhao.ye on 14/03/2018.
 */
@RestController
public class UserController {

    @Resource
    private UserHandler userHandler;

    @Resource
    private PasswordHelper passwordHelper;

//    public Response login() {
//
//    }



    @PostMapping("/api/regist")
    public Response regist(@RequestBody UserDTO userDTO) {
        userDTO.setCreateTime(new Date());
        userDTO.setUpdateTime(new Date());
        userDTO.setDeleteFlag(UserStatus.NORMAL.getValue());
        passwordHelper.encryptPassword(userDTO);
        userHandler.create(userDTO);
        return Response.success();
    }






}
