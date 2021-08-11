package com.binaywang.spring.starter.wxjava.cp.annotation;

import com.binaywang.spring.starter.wxjava.cp.config.WxCpConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 强依赖redis template
 *
 * @author caiqy
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(WxCpConfiguration.class)
public @interface EnableWxCp {
}
