package me.chanjar.weixin.open.bean.result;

import lombok.Data;
import me.chanjar.weixin.open.bean.auth.WxOpenAuthorizationInfo;
import me.chanjar.weixin.open.bean.auth.WxOpenAuthorizerInfo;

import java.io.Serializable;

/**
 * @author <a href="https://github.com/007gzs">007</a>
 */
@Data
public class WxOpenAuthorizerInfoResult implements Serializable {
  private static final long serialVersionUID = 3166298050833019785L;

  private WxOpenAuthorizationInfo authorizationInfo;
  private WxOpenAuthorizerInfo authorizerInfo;
}
