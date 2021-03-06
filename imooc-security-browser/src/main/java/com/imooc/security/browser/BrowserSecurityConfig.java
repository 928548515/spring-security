package com.imooc.security.browser;

import com.imooc.security.browser.authentication.ImoocAuthenicationSuccessHandler;
import com.imooc.security.browser.authentication.ImoocAuthenticationFailureHandler;
import com.imooc.security.core.authentication.AbstractChannelSecurityConfig;
import com.imooc.security.core.code.ValidateCodeFilter;
import com.imooc.security.core.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.social.security.SpringSocialConfigurer;

@Configuration
public class BrowserSecurityConfig extends AbstractChannelSecurityConfig {


    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private ImoocAuthenicationSuccessHandler imoocAuthenicationSuccessHandler;

    @Autowired
    private ImoocAuthenticationFailureHandler imoocAuthenticationFailureHandler;

    @Autowired
    private SpringSocialConfigurer imoocSocialSecurityConfig;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        applyPasswordAuthenticationConfig(http);

        ValidateCodeFilter validateCodeFilter = new ValidateCodeFilter();
        validateCodeFilter.setAuthenticationFailureHandler(imoocAuthenticationFailureHandler);
        validateCodeFilter.setSecurityProperties(securityProperties);
        validateCodeFilter.afterPropertiesSet();
//        http.httpBasic()
          http.apply(imoocSocialSecurityConfig)
                  .and()
//                .addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class)
                .formLogin()
                .loginPage("/authentication/require")
                .loginProcessingUrl("/authentication/form")
                //登录成功后的处理器
                .successHandler(imoocAuthenicationSuccessHandler)
                //登录失败后的处理器
                .failureHandler(imoocAuthenticationFailureHandler)
                .and()
                .authorizeRequests()
//                .antMatchers("/user/**").permitAll()
                .antMatchers(
                        "/authentication/require",
                        securityProperties.getBrowser().getLoginPage(),
                        "/code/image",
                        securityProperties.getBrowser().getSignUpUrl(),
                        "/user/regist").permitAll()
                .anyRequest().authenticated()
                .and().csrf().disable();
//        super.configure(http);
    }
}
