package me.chanjar.weixin.channel.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 视频号小店 商品上下架状态
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum SpuStatus {

  /** 0 初始值 */
  INIT(0, "未上架"),
  /** 5 上架 */
  UP(5, "上架"),
  /** 6 回收站 */
  TRASH(6, "回收站"),
  /** 11 自主下架 */
  DOWN(11, "自主下架"),
  /** 13 违规下架/风控系统下架 */
  SYSTEM_DOWN(13, "违规下架");

  private final int status;
  private final String desc;

  SpuStatus(int status, String desc) {
    this.status = status;
    this.desc = desc;
  }

  public int getStatus() {
    return status;
  }

  public String getDesc() {
    return desc;
  }
}
