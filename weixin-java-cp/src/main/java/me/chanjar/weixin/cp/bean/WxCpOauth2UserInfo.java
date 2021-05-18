package me.chanjar.weixin.cp.bean;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * <pre>
 *  用oauth2获取用户信息的结果类
 *  Created by BinaryWang on 2019/5/26.
 * </pre>
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WxCpOauth2UserInfo implements Serializable {
  private static final long serialVersionUID = -4301684507150486556L;
  
  private String openId;
  private String deviceId;
  private String userId;
  private String userTicket;
  private String expiresIn;
  private String externalUserId;
}
