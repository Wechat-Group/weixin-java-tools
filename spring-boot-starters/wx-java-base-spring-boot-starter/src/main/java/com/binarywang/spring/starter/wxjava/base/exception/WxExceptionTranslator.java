package com.binarywang.spring.starter.wxjava.base.exception;

import com.binarywang.spring.starter.wxjava.base.bean.WxCommonResult;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.Servlet;

/**
 * @author caiqy
 * @date 2021/9/2
 */
@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
@Configuration
@RestControllerAdvice
@AllArgsConstructor
@ConditionalOnClass({Servlet.class, DispatcherServlet.class})
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
public class WxExceptionTranslator {

  @ExceptionHandler(WxErrorException.class)
  @ResponseStatus(HttpStatus.OK)
  public WxCommonResult handleCustomWxError(WxErrorException e) {
    return new WxCommonResult(e.getError().getErrorCode(), e.getError().getErrorMsg(), false);
  }
}
