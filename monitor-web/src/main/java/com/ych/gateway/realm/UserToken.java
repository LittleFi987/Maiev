package com.ych.gateway.realm;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * Created by chenhao.ye on 2017/9/20.
 */
public class UserToken implements AuthenticationToken {
    private static final long serialVersionUID = -4371009043895847003L;

    private String identity;
    private String password;

    public UserToken(String identity, String password) {
        this.identity = identity;
        this.password = password;
    }

    @Override
    public Object getPrincipal() {
        return identity;
    }

    @Override
    public Object getCredentials() {
        return password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

}
