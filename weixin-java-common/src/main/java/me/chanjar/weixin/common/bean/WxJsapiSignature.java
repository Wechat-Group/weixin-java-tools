package me.chanjar.weixin.common.bean;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * jspai signature.
 *
 * @author Daniel Qian
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WxJsapiSignature implements Serializable {
  private static final long serialVersionUID = -1116808193154384804L;

  private String appId;

  private String nonceStr;

  private long timestamp;

  private String url;

  private String signature;

}
