package me.chanjar.weixin.cp.bean.license.account;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import me.chanjar.weixin.cp.bean.WxCpBaseResp;
import me.chanjar.weixin.cp.bean.license.WxCpTpLicenseActiveCodeInfo;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

/**
 * 查询的激活码详情
 * 文档地址：https://developer.work.weixin.qq.com/document/path/95553
 * @author Totoro
 * @date 2022/6/27 14:28
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class WxCpTpLicenseCodeInfoResp extends WxCpBaseResp {
  private static final long serialVersionUID = 8058798194938243361L;

  @SerializedName("active_info")
  private WxCpTpLicenseActiveCodeInfo activeCodeInfo;


  public static WxCpTpLicenseCodeInfoResp fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpTpLicenseCodeInfoResp.class);
  }

}
