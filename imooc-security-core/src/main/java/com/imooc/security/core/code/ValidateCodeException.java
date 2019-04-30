package com.imooc.security.core.code;


import org.springframework.security.core.AuthenticationException;

/**
 * @author pengyu
 * @Date 2019/4/30
 */
public class ValidateCodeException extends AuthenticationException {

    public ValidateCodeException(String msg) {
        super(msg);
    }
}
