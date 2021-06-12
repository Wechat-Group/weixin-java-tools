package me.chanjar.weixin.open.bean.result;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class WxOpenMaBatchCloudDescribeStaticStoreResult extends WxOpenResult {

  private static final long serialVersionUID = -1529882075015400566L;

  @SerializedName("Data")
  private List<Data> data;

  @lombok.Data
  public static class Data {

    /***
     * 环境名称
     */
    @SerializedName("env")
    private String env;

    /***
     * 静态域名
     */
    @SerializedName("domain")
    private String domain;

    /***
     * COS桶
     */
    @SerializedName("bucket")
    private String bucket;

    /***
     * 所在区域
     */
    @SerializedName("regoin")
    private String region;

    /***
     * 状态
     */
    @SerializedName("status")
    private String status;
  }
}
