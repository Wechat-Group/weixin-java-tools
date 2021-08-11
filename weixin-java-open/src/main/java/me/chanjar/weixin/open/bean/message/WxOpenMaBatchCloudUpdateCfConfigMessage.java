package me.chanjar.weixin.open.bean.message;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class WxOpenMaBatchCloudUpdateCfConfigMessage implements Serializable {

  private static final long serialVersionUID = -8370447665690077491L;

  /**
   * 环境ID
   * 必填:是
   */
  @SerializedName("env")
  private String env;

  /**
   * 函数名
   * 必填:是
   */
  @SerializedName("functionname")
  private String functionName;

  /**
   * 内存大小
   * 必填:否
   */
  @SerializedName("memorysize")
  private Long memorySize;

  /**
   * 超时时间，单位：秒
   * 必填:否
   */
  @SerializedName("timeout")
  private Long timeout;

  /**
   * 环境变量
   * 必填:否
   */
  @SerializedName("environment_variables")
  private List<EnvironmentVariable> environmentVariables;

  /**
   * 公网访问配置
   * 必填:否
   */
  @SerializedName("public_net_config")
  private PublicNetConfig publicNetConfig;

  /**
   * 私有网络配置
   * 必填:否
   */
  @SerializedName("vpc_config")
  private VpcConfig vpcConfig;

  @Data
  public static class EnvironmentVariable {

    @SerializedName("key")
    private String key;

    @SerializedName("value")
    private String value;
  }

  @Data
  public static class PublicNetConfig {

    /**
     * 是否开启公网访问能力，取值：'DISABLE','ENABLE'
     */
    @SerializedName("public_net_status")
    private String publicNetStatus;

    /**
     * Eip开启状态，取值：'DISABLE','ENABLE'
     */
    @SerializedName("eip_status")
    private String eipStatus;
  }

  public static class VpcConfig{

    @SerializedName("vpcid")
    private String vpcId;

    /**
     * 子网id
     */
    @SerializedName("subnetid")
    private String subNetId;
  }
}
