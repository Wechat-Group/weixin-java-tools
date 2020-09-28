package me.chanjar.weixin.common.util;

import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.api.WxErrorExceptionHandler;
import me.chanjar.weixin.common.error.WxErrorException;

/**
 * @author Daniel Qian
 */
@Slf4j
public class LogExceptionHandler implements WxErrorExceptionHandler {
  @Override
  public void handle(WxErrorException e) {
    log.error("Error happens", e);
  }

}
