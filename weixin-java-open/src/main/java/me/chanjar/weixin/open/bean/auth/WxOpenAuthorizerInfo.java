package me.chanjar.weixin.open.bean.auth;

import lombok.Data;

import java.io.Serializable;
import java.util.Map;

/**
 * @author <a href="https://github.com/007gzs">007</a>
 */
@Data
public class WxOpenAuthorizerInfo implements Serializable {
  private static final long serialVersionUID = -5327886953416394738L;

  private String nickName;
  private String headImg;
  private Integer serviceTypeInfo;
  private Integer verifyTypeInfo;
  private String userName;
  private String principalName;
  private Map<String, Integer> businessInfo;
  private String alias;
  private String qrcodeUrl;
}
