package me.chanjar.weixin.mp.bean.invoice.reimburse;

import lombok.Data;

/**
 * <pre>
 * 发票商品信息
 * </pre>
 * @author <a href="https://github.com/mr-xiaoyu">xiaoyu</a>
 * @since 2021-03-23
 */
@Data
public class InvoiceCommodityInfo {

  /**
   * 项目（商品）名称
   */
  private String name;

  /**
   * 项目数量
   */
  private Integer num;

  /**
   * 项目单位
   */
  private String unit;

  /**
   * 单价，以分为单位
   */
  private Integer price;
}
