package com.github.binarywang.wxpay.bean.marketing;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 设置消息通知地址返回结果对象
 *
 * @author thinsstar
 */
@NoArgsConstructor
@Data
public class FavorCallbacksSaveResult implements Serializable {

  private static final long serialVersionUID = 1L;

  /**
   * 修改时间
   * <p>
   * 修改时间，遵循rfc3339标准格式，格式为YYYY-MM-DDTHH:mm:ss.sss+TIMEZONE，YYYY-MM-DD表示年月日，T出现在字符串中，表示time元素的开头，HH:mm:ss.sss表示时分秒毫秒，TIMEZONE表示时区（+08:00表示东八区时间，领先UTC 8小时，即北京时间）。例如：2015-05-20T13:29:35.120+08:00表示，北京时间2015年5月20日 13点29分35秒。
   * 示例值：2015-05-20T13:29:35.120+08:00
   */
  @SerializedName("update_time")
  private String updateTime;

  /**
   * 通知地址
   * <p>
   * 通知地址
   * 示例值：api.weixin.qq.com
   */
  @SerializedName("notify_url")
  private String notifyUrl;
}
