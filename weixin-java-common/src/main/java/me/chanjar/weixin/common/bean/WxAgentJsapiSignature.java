package me.chanjar.weixin.common.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * agent jspai signature.
 *
 * @author Mark Tian
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WxAgentJsapiSignature implements Serializable {
  private static final long serialVersionUID = -1116808193154384804L;

  private String appId;

  private String nonceStr;

  private long timestamp;

  private String url;

  private Integer agentid;

  private String signature;

}
