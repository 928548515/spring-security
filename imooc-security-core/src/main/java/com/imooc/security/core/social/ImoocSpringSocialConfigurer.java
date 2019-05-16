package com.imooc.security.core.social;

import org.springframework.social.security.SocialAuthenticationFilter;
import org.springframework.social.security.SpringSocialConfigurer;

/**
 * @author pengyu
 * @Date 2019/5/8
 */
public class ImoocSpringSocialConfigurer extends SpringSocialConfigurer {

    private String filterProcessesUrl ;

    public ImoocSpringSocialConfigurer(String filterProcessesUrl) {
        this.filterProcessesUrl = filterProcessesUrl;
    }

    @Override
    protected <T> T postProcess(T object) {
        SocialAuthenticationFilter filter =  (SocialAuthenticationFilter)super.postProcess(object);
        filter.setFilterProcessesUrl(filterProcessesUrl);
        return (T) filter;
    }
}
