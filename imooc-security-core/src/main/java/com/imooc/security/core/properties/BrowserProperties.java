package com.imooc.security.core.properties;


import org.apache.commons.lang3.StringUtils;

public class BrowserProperties {

    private  String loginPage = "/imooc-signIn.html";

    private String signUpUrl  ="/imooc-signUp.html";

    public String getLoginPage() {
        return loginPage;
    }

    public void setLoginPage(String loginPage) {
        this.loginPage = loginPage;
    }

    public String getSignUpUrl() {
        return signUpUrl;
    }

    public void setSignUpUrl(String signUpUrl) {
        this.signUpUrl = signUpUrl;
    }
}
