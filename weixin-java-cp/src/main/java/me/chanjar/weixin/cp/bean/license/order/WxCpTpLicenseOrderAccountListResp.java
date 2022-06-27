package me.chanjar.weixin.cp.bean.license.order;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import me.chanjar.weixin.cp.bean.WxCpBaseResp;
import me.chanjar.weixin.cp.bean.license.WxCpTpLicenseAccount;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.util.List;

/**
 * 获取订单中的帐号列表
 * 文档地址：https://developer.work.weixin.qq.com/document/path/95649
 * @author Totoro
 * @date 2022-6-27 14:14:40
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class WxCpTpLicenseOrderAccountListResp extends WxCpBaseResp {
  private static final long serialVersionUID = 470154227651487230L;

  @SerializedName("next_cursor")
  private String nextCursor;

  @SerializedName("has_more")
  private Integer hasMore;

  @SerializedName("account_list")
  private List<WxCpTpLicenseAccount> accountList;


  public static WxCpTpLicenseOrderAccountListResp fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpTpLicenseOrderAccountListResp.class);
  }



}
