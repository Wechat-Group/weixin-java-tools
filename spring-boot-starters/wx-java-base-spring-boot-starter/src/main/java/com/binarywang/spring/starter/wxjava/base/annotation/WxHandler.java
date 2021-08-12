package com.binarywang.spring.starter.wxjava.base.annotation;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Indexed;

import java.lang.annotation.*;

/**
 * @author caiqy
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Indexed
@Component
public @interface WxHandler {

  @AliasFor(annotation = Component.class)
  String value() default "";
}
