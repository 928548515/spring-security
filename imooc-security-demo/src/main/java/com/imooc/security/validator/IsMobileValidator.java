package com.imooc.security.validator;

import com.imooc.security.tool.AccountValidatorUtil;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author pengyu
 * @Date 2019/4/26
 */
public class IsMobileValidator implements ConstraintValidator<IsMobile, String> {

    private boolean flag = false;

    @Override
    public void initialize(IsMobile isMobile) {
        flag = isMobile.required();
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (flag) {
            return AccountValidatorUtil.isMobile(s);
        } else {
            if (StringUtils.isEmpty(s)) {
                return true;
            } else {
                return AccountValidatorUtil.isMobile(s);
            }
        }
    }
}
