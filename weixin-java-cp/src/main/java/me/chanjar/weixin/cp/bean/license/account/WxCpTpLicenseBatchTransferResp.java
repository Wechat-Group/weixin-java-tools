package me.chanjar.weixin.cp.bean.license.account;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import me.chanjar.weixin.cp.bean.WxCpBaseResp;
import me.chanjar.weixin.cp.bean.license.WxCpTpLicenseTransfer;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.util.List;

/**
 * 基础结果返回信息
 * 文档地址：https://developer.work.weixin.qq.com/document/path/95673
 *
 * @author Totoro  created on  2022/6/27 15:49
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class WxCpTpLicenseBatchTransferResp extends WxCpBaseResp {
  private static final long serialVersionUID = 5443977430756597486L;

  @SerializedName("transfer_result")
  private List<WxCpTpLicenseTransfer> transferResult;

  /**
   * From json wx cp tp license batch transfer resp.
   *
   * @param json the json
   * @return the wx cp tp license batch transfer resp
   */
  public static WxCpTpLicenseBatchTransferResp fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpTpLicenseBatchTransferResp.class);
  }


}
