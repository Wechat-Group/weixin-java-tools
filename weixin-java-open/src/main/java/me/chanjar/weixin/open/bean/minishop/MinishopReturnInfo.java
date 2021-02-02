package me.chanjar.weixin.open.bean.minishop;

import lombok.Data;

import java.io.Serializable;

@Data
public class MinishopReturnInfo implements Serializable {

  /**
   * 退货地址
   */
  private MinishopAddressInfo addressInfo;

  /**
   * 邮箱
   */
  private String email;

  /**
   * 公司地址
   */
  private MinishopAddressInfo companyAddress;
}
