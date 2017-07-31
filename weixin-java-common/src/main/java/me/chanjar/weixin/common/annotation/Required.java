package me.chanjar.weixin.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 标识某个字段是否是必填的
 * <p>
 * Created by Binary Wang on 2016/9/25.
 *
 * @author binarywang (https://github.com/binarywang)
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Required {

}
