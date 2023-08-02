package me.chanjar.weixin.open.bean.minishop;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 小商店运费模版信息
 *
 * @author kelven.law@gmail.com
 */
@Data
public class MinishopDeliveryTemplateResult implements Serializable {
  private static final long serialVersionUID = -3330428091957969299L;
  /**
   * 错误码
   */
  private Integer errCode;

  /**
   * 错误信息
   */
  private String errMsg;

  /**
   * 运费模版列表
   */
  private List<MinishopDeliveryTemplate> templateList;
}
