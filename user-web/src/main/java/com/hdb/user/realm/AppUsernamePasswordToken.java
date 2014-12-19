package com.hdb.user.realm;

import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * Created by user on 2014/12/18.
 */
public class AppUsernamePasswordToken extends UsernamePasswordToken {

    private String app;

    public String getApp() {
        return app;
    }

    public void setApp(String app) {
        this.app = app;
    }

    public AppUsernamePasswordToken(String app, String username, String password, boolean rememberMe, String host){
        super(username, password, rememberMe, host);
        this.app = app;
    }
}
