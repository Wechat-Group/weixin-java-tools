package me.chanjar.weixin.cp.bean.external.acquisition;

import lombok.Data;
import lombok.EqualsAndHashCode;
import me.chanjar.weixin.cp.bean.WxCpBaseResp;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

/**
 * 创建获客助手链接结果
 *
 * @author alien_zyl
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class WxCpCustomerAcquisitionCreateResult extends WxCpBaseResp {
  private static final long serialVersionUID = -6301164294371861558L;

  private WxCpCustomerAcquisitionInfo.Link link;

  public static WxCpCustomerAcquisitionCreateResult fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpCustomerAcquisitionCreateResult.class);
  }
}
