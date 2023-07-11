package cn.binarywang.wx.miniapp.bean.openapi;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

/**
 * 查询rid信息 返回数据
 * @author shuiyihan12
 * @since 2023/7/10 16:53
 */
@Data
public class WxMiniGetRidInfoResult {

  /**
   * 发起请求的时间戳
   */
  @SerializedName("invoke_time")
  private Integer invokeTime;
  /**
   * 请求毫秒级耗时
   */
  @SerializedName("cost_in_ms")
  private Integer costInMs;
  /**
   * 请求的URL参数
   */
  @SerializedName("request_url")
  private String requestUrl;
  /**
   * post请求的请求参数
   */
  @SerializedName("request_body")
  private String requestBody;
  /**
   * 接口请求返回参数
   */
  @SerializedName("response_body")
  private String responseBody;
  /**
   * 接口请求的客户端ip
   */
  @SerializedName("client_ip")
  private String clientIp;
}
