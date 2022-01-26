package me.chanjar.weixin.cp.bean.kf.msg;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author leiin
 * @date 2022/1/26 5:32 下午
 */
@NoArgsConstructor
@Data
public class WxCpKfLocationMsg {
  /**
   * 参数：name
   * 是否必须：否
   * 类型：string
   * 说明：位置名
   */
  private String name;
  /**
   * 参数：address
   * 是否必须：否
   * 类型：string
   * 说明：地址详情说明
   */
  private String address;
  /**
   * 参数：latitude
   * 是否必须：是
   * 类型：float
   * 说明：纬度，浮点数，范围为90 ~ -90
   */
  private Float latitude;
  /**
   * 参数：longitude
   * 是否必须：是
   * 类型：float
   * 说明：经度，浮点数，范围为180 ~ -180
   */
  private Float longitude;
}
