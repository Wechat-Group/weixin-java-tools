package com.github.binarywang.wxpay.bean;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <pre>
 * 微信支付接口请求数据封装对象
 * Created by Binary Wang on 2017-8-25.
 * </pre>
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
@Data
@NoArgsConstructor
public class WxPayApiData {
  /**
   * 接口请求地址
   */
  private String url;

  /**
   * 请求数据
   */
  private String requestData;

  /**
   * 响应数据
   */
  private String responseData;

  /**
   * 接口请求异常信息
   */
  private String exceptionMsg;

  /**
   * @param url          接口请求地址
   * @param requestData  请求数据
   * @param responseData 响应数据
   * @param exceptionMsg 接口请求异常信息
   */
  public WxPayApiData(String url, String requestData, String responseData, String exceptionMsg) {
    this.url = url;
    this.requestData = requestData;
    this.responseData = responseData;
    this.exceptionMsg = exceptionMsg;
  }

  @Override
  public String toString() {
    if (this.exceptionMsg != null) {
      return String.format("\n【请求地址】：%s\n【请求数据】：%s\n【异常信息】：%s",
        this, url, this.requestData, this.exceptionMsg);
    }

    return String.format("\n【请求地址】：%s\n【请求数据】：%s\n【响应数据】：%s",
      this.url, this.requestData, this.responseData);
  }
}
