package com.imooc.security.annotation;

import javax.validation.Constraint;
import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyFirstAnnotation {

    String value();

}
