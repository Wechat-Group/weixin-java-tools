package me.chanjar.weixin.cp.bean.workbench;

import lombok.Data;

import java.io.Serializable;

/**
 * @author songshiyu
 * @date : create in 10:21 2020/9/28
 * @description: 关键数据型模板类型
 */
@Data
public class WorkBenchKeyData implements Serializable {
  /*
   * 关键数据名称
   * */
  private String key;
  /*
   * 关键数据
   * */
  private String data;
  /*
   * 点击跳转url，若不填且应用设置了主页url，则跳转到主页url，否则跳到应用会话窗口
   * */
  private String jumpUrl;
  /*
   * 若应用为小程序类型，该字段填小程序pagepath，若未设置，跳到小程序主页
   * */
  private String pagePath;
}
