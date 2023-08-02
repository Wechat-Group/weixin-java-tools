package me.chanjar.weixin.cp.bean.external.msg;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 地理位置
 *
 * @author leiin  created on  2021-10-29
 */
@Data
@Accessors(chain = true)
public class Location {
  private String latitude;
  private String longitude;
  private String name;
}
