package com.imooc.security.core.properties;

/**
 * @author pengyu
 * @Date 2019/5/6
 */
public class SocialProperties {

    String filterProcessesUrl = "/auth";

    private QQProperties qq =  new QQProperties();

    public String getFilterProcessesUrl() {
        return filterProcessesUrl;
    }

    public void setFilterProcessesUrl(String filterProcessesUrl) {
        this.filterProcessesUrl = filterProcessesUrl;
    }

    public QQProperties getQq() {
        return qq;
    }

    public void setQq(QQProperties qq) {
        this.qq = qq;
    }
}
