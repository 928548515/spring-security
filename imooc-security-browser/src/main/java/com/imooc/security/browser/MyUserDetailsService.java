package com.imooc.security.browser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MyUserDetailsService implements UserDetailsService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("用户名："+username);

        /** 从数据库查询出密码 */
        String password = passwordEncoder.encode("123456");
        /**
         * 数据库查询出的用户权限集合(由于未使用数据库所以将以逗号分割的字符串转为权限集合)
         */
        List<GrantedAuthority> authorities = AuthorityUtils.commaSeparatedStringToAuthorityList("admin");

        //账号是否未过期
        boolean isAccountNonExpired  = true;

        //帐户是否未锁定
        boolean isAccountNonLocked = true;

        //密码是否未过期
        boolean isCredentialsNonExpired = true;

        //是否被删除
        boolean isEnabled = true;

        //User对象实现了UserDetails接口
        User user = new User(username, password,isEnabled,isCredentialsNonExpired,isAccountNonExpired,isAccountNonLocked,authorities);
        return user;
    }
}
