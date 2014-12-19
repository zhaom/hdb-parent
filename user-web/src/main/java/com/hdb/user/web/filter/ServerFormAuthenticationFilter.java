package com.hdb.user.web.filter;

import com.hdb.user.realm.AppUsernamePasswordToken;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.util.StringUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;


public class ServerFormAuthenticationFilter extends FormAuthenticationFilter {

    public static final String DEFAULT_APP_SOURCE_PARAM = "app";

    public static final String DEFAULT_APP_SOURCE_VALUE = "hdb";

    private String appParam = "app";

    public String getAppParam() {
        return appParam;
    }

    public void setAppParam(String appParam) {
        this.appParam = appParam;
    }

    protected void issueSuccessRedirect(ServletRequest request, ServletResponse response) throws Exception {
        String fallbackUrl = (String) getSubject(request, response)
                .getSession().getAttribute("authc.fallbackUrl");
        if(StringUtils.isEmpty(fallbackUrl)) {
            fallbackUrl = getSuccessUrl();
        }
        WebUtils.redirectToSavedRequest(request, response, fallbackUrl);
    }

    protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) {
        String username = this.getUsername(request);
        String password = this.getPassword(request);
        String app = this.getApp(request);
        if (StringUtils.isEmpty(app)) {
            app = DEFAULT_APP_SOURCE_VALUE;
        }
        boolean rememberMe = this.isRememberMe(request);
        String host = this.getHost(request);
        return new AppUsernamePasswordToken(app, username, password, rememberMe, host);
    }

    protected String getApp(ServletRequest request) {
        return WebUtils.getCleanParam(request, this.getAppParam());
    }

}
