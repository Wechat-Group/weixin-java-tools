package me.chanjar.weixin.cp.bean.license;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 基础的信息
 *
 * @author Totoro  created on  2022/6/27 15:50
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WxCpTpLicenseTransfer implements Serializable {
  private static final long serialVersionUID = -5194757640985570778L;


  /**
   * 转移成员加密的userid
   */
  @SerializedName("handover_userid")
  private String handoverUserId;

  /**
   * 接收成员加密的userid
   */
  @SerializedName("takeover_userid")
  private String takeoverUserId;

  /**
   * 基础成功标识符，在请求继承的时候无需传入该参数，参数为企业微信返回
   * 0为成功
   */
  @SerializedName("errcode")
  private Integer errCode;


}
