package com.imooc.security.core.social.qq;

import com.imooc.security.core.properties.SecurityProperties;
import com.imooc.security.core.social.qq.connect.QQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.social.SocialAutoConfigurerAdapter;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.connect.ConnectionFactory;

/**
 * @author pengyu
 * @Date 2019/5/6
 */
@Configuration
@ConditionalOnProperty(prefix =  "imooc.security.social.qq",name = "app-id")
public class QQAutoConfig extends SocialAutoConfigurerAdapter {

    @Autowired
    private SecurityProperties securityProperties;

    @Override
    protected ConnectionFactory<?> createConnectionFactory() {
        String appId = securityProperties.getSocial().getQq().getAppId();

        String appSecret = securityProperties.getSocial().getQq().getAppSecret();

        String providerId = securityProperties.getSocial().getQq().getProviderId();

        return new QQConnectionFactory(providerId,appId,appSecret);
    }
}
