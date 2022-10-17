package me.chanjar.weixin.cp.bean.license;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * 激活码信息
 * 文档地址：https://developer.work.weixin.qq.com/document/path/95553
 *
 * @author Totoro  created on  2022/6/27 14:34
 */
@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class WxCpTpLicenseActiveCodeInfo extends WxCpTpLicenseBaseAccount {
  private static final long serialVersionUID = 7696395903786956694L;

  @SerializedName("active_code")
  private String activeCode;

  private Integer status;

  @SerializedName("create_time")
  private Long createTime;

  @SerializedName("active_time")
  private Long activeTime;

  @SerializedName("expire_time")
  private Long expireTime;


}
