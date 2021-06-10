package me.chanjar.weixin.open.bean;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;

@Data
public class WxCloudFunctionTrigger implements Serializable {

  private static final long serialVersionUID = -2732337999007679318L;

  /**
   * 触发器名称
   */
  @SerializedName("trigger_name")
  private String triggerName;

  /**
   * 填"Timer"
   */
  @SerializedName("type")
  private String type;

  /**
   * 定时触发器触发周期 (cron 表达式)
   */
  @SerializedName("config")
  private String config;
}
