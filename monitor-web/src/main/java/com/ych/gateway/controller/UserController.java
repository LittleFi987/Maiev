package com.ych.gateway.controller;

import com.ych.core.common.Response;
import com.ych.core.dto.LoginDTO;
import com.ych.core.dto.UserDTO;
import com.ych.core.enums.UserStatus;
import com.ych.core.enums.user.UserResponseEnum;
import com.ych.core.handler.UserHandler;
import com.ych.gateway.config.JWTConfig;
import com.ych.gateway.helper.PasswordHelper;
import com.ych.gateway.realm.UserToken;
import com.ych.gateway.util.JWTGenerateUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ThreadContext;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * Created by chenhao.ye on 14/03/2018.
 */
@RestController
@Slf4j
public class UserController {

    @Resource
    private UserHandler userHandler;

    @Resource
    private PasswordHelper passwordHelper;


    @Resource
    private JWTConfig jwtConfig;

    @PostMapping("/api/login")
    public Response login(@RequestBody LoginDTO loginDto) {
        String username = loginDto.getUsername();
        String pwd = loginDto.getPwd();
        UserDTO userDto = userHandler.findByName(username, UserStatus.NORMAL);
        UserToken token = new UserToken(username, pwd);

        try {
            ensureUserIsLoggedOut();
            Subject subject = getSubject();
            subject.login(token);
            subject.logout();
            return Response.success(userDto.getId() + "--" + JWTGenerateUtils.generateJWT(jwtConfig.getJwtSecret(), userDto.getId().longValue(), jwtConfig.getIssure()));
        } catch (Exception e) {
            return Response.failed(UserResponseEnum.USER_LOGIN_FAIL);
        }
    }

    @GetMapping("/api/user/info")
    public Response getUserInfo(@RequestParam String token) {
        return Response.success(token);
    }


    @PostMapping("/api/regist")
    public Response regist(@RequestBody UserDTO userDTO) {
        passwordHelper.encryptPassword(userDTO);
        userHandler.create(userDTO);
        return Response.success();
    }


    private Subject getSubject() {
        Subject currentUser = ThreadContext.getSubject();
        if (currentUser == null) {
            currentUser = SecurityUtils.getSubject();
        }
        return currentUser;
    }

    private void ensureUserIsLoggedOut() {
        try {
            Subject currentUser = getSubject();
            if (currentUser == null)
                return;
            currentUser.logout();
            Session session = currentUser.getSession(false);
            if (session == null)
                return;
            session.stop();
        } catch (Exception e) {
        }
    }




}
