package com.imooc.security.UserDetailsService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.social.security.SocialUser;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MyUserDetailsService implements UserDetailsService, SocialUserDetailsService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("表单登录用户名："+username);
        return buildUser(username);
    }


    @Override
    public SocialUserDetails loadUserByUserId(String userId) throws UsernameNotFoundException {
        logger.info("社交登录用户名："+userId);
        return buildUser(userId);
    }

    private SocialUserDetails buildUser(String userId){
        String password = passwordEncoder.encode("123456");
        logger.info("数据库密码是:"+password);
        //账号是否未过期
        boolean isAccountNonExpired  = true;
        //帐户是否未锁定
        boolean isAccountNonLocked = true;
        //密码是否未过期
        boolean isCredentialsNonExpired = true;
        //是否被删除
        boolean isEnabled = true;
        /**
         * 数据库查询出的用户权限集合(由于未使用数据库所以将以逗号分割的字符串转为权限集合)
         */
        List<GrantedAuthority> authorities = AuthorityUtils.commaSeparatedStringToAuthorityList("admin");
        return new SocialUser(userId,password,isEnabled,isCredentialsNonExpired,isAccountNonExpired,isAccountNonLocked,authorities);

    }

}
