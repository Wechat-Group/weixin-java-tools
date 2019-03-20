package me.chanjar.weixin.open.bean.ma;

import lombok.Builder;
import lombok.Data;

/**
 * @author: momorans
 * @create: 2019-03-12
 **/
@Data
@Builder
public class WxMaOpenSubpackage {
  /**
   * 分包根目录
   */
  private String root;

  /**
   * 分包别名，分包预下载时可以使用
   */
  private String name;


  /**
   * 分包页面路径，相对与分包根目录
   */
  private String pages;

  /**
   * 分包是否是独立分包
   */
  private Boolean independent;


}
