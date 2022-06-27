package me.chanjar.weixin.cp.bean.license.account;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import me.chanjar.weixin.cp.bean.WxCpBaseResp;
import me.chanjar.weixin.cp.bean.license.WxCpTpLicenseCorpAccount;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.util.List;

/**
 * 企业的帐号列表（已激活）
 * 文档地址：https://developer.work.weixin.qq.com/document/path/95544
 * @author Totoro
 * @date 2022/6/27 15:15
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class WxCpTpLicenseCorpAccountListResp extends WxCpBaseResp {
  private static final long serialVersionUID = -7976008813041959375L;

  @SerializedName("next_cursor")
  private String nextCursor;

  @SerializedName("has_more")
  private Integer hasMore;

  @SerializedName("account_list")
  private List<WxCpTpLicenseCorpAccount> orderList;


  public static WxCpTpLicenseCorpAccountListResp fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpTpLicenseCorpAccountListResp.class);
  }

}
