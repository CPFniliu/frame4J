package com.cpf.frame4j.annotation;

import java.lang.annotation.*;

/**
 * AOP切面注解
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Aspect {

    Class<? extends Annotation> value();

}
