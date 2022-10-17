package me.chanjar.weixin.cp.bean.license.order;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import me.chanjar.weixin.cp.bean.WxCpBaseResp;
import me.chanjar.weixin.cp.bean.license.WxCpTpLicenseInvalidAccount;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.util.List;

/**
 * 创建下单购买帐号任务返回结果
 * 文档地址：https://developer.work.weixin.qq.com/document/path/95646
 *
 * @author Totoro  created on  2022-6-27 11:15:20
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class WxCpTpLicenseRenewOrderJobResp extends WxCpBaseResp {

  private static final long serialVersionUID = -4469875729545594102L;
  /**
   * 任务ID
   */
  @SerializedName("jobid")
  private String jobId;
  /**
   * 有效的续费账号列表
   */
  @SerializedName("invalid_account_list")
  private List<WxCpTpLicenseInvalidAccount> invalidAccountList;


  /**
   * From json wx cp tp license renew order job resp.
   *
   * @param json the json
   * @return the wx cp tp license renew order job resp
   */
  public static WxCpTpLicenseRenewOrderJobResp fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpTpLicenseRenewOrderJobResp.class);
  }


}
