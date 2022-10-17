package me.chanjar.weixin.cp.bean.license.account;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import me.chanjar.weixin.cp.bean.WxCpBaseResp;
import me.chanjar.weixin.cp.bean.license.WxCpTpLicenseActiveAccount;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.util.List;

/**
 * 批量激活帐号结果
 * 文档地址：https://developer.work.weixin.qq.com/document/path/95553
 *
 * @author Totoro  created on  2022-6-27 16:19:21
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class WxCpTpLicenseBatchActiveResultResp extends WxCpBaseResp {

  private static final long serialVersionUID = 8799524570217687659L;

  @SerializedName("active_result")
  private List<WxCpTpLicenseActiveAccount> activeResults;


  /**
   * From json wx cp tp license batch active result resp.
   *
   * @param json the json
   * @return the wx cp tp license batch active result resp
   */
  public static WxCpTpLicenseBatchActiveResultResp fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpTpLicenseBatchActiveResultResp.class);
  }

}
