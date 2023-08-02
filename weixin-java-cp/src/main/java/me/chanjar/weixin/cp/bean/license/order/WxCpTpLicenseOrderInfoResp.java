package me.chanjar.weixin.cp.bean.license.order;

import lombok.Data;
import lombok.EqualsAndHashCode;
import me.chanjar.weixin.cp.bean.WxCpBaseResp;
import me.chanjar.weixin.cp.bean.license.WxCpTpLicenseOrder;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

/**
 * 订单详情结果
 * 文档：https://developer.work.weixin.qq.com/document/path/95648
 *
 * @author Totoro  created on  2022/06/27 11:56:03
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class WxCpTpLicenseOrderInfoResp extends WxCpBaseResp {

  private static final long serialVersionUID = 7000171280773370910L;

  private WxCpTpLicenseOrder order;


  /**
   * From json wx cp tp license order info resp.
   *
   * @param json the json
   * @return the wx cp tp license order info resp
   */
  public static WxCpTpLicenseOrderInfoResp fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpTpLicenseOrderInfoResp.class);
  }


}
