package me.chanjar.weixin.open.bean.result;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 查询 rid 信息
 * <a href="https://developers.weixin.qq.com/doc/offiaccount/openApi/get_rid_info.html">查询 rid 信息</a>
 **/
@Data
@EqualsAndHashCode(callSuper = false)
public class WxOpenGetRidResult extends WxOpenResult implements Serializable {


  @SerializedName("request")
  private RidRequestBean request;


  @Data
  public static class RidRequestBean {

    /**
     * 发起请求的时间戳
     */
    @SerializedName("invoke_time")
    private Long invokeTime;
    /**
     * 请求毫秒级耗时
     */
    @SerializedName("cost_in_ms")
    private Integer costInMs;

    /**
     * 请求的 URL 参数
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

}
