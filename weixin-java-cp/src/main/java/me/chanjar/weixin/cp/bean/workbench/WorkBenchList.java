package me.chanjar.weixin.cp.bean.workbench;

import lombok.Data;

import java.io.Serializable;

/**
 * 列表模板类型 *
 *
 * @author songshiyu
 * created at 10:21 2020/9/28
 */
@Data
public class WorkBenchList implements Serializable {
  private static final long serialVersionUID = -7892708831294949257L;

  /**
   * 列表显示文字，不超过128个字节
   */
  private String title;
  /**
   * 点击跳转url，若不填且应用设置了主页url，则跳转到主页url，否则跳到应用会话窗口
   */
  private String jumpUrl;
  /**
   * 若应用为小程序类型，该字段填小程序pagepath，若未设置，跳到小程序主页
   */
  private String pagePath;
}
