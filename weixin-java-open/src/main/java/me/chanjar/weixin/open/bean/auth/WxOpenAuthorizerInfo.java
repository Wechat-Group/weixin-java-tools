package me.chanjar.weixin.open.bean.auth;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author <a href="https://github.com/007gzs">007</a>
 */
@Data
public class WxOpenAuthorizerInfo implements Serializable {
  private static final long serialVersionUID = -5327886953416394738L;

  private String nickName;
  private String headImg;
  private Integer serviceTypeInfo;
  private Integer verifyTypeInfo;
  private String userName;
  private String principalName;
  private Map<String, Integer> businessInfo;
  private String alias;
  private String qrcodeUrl;
  /**
   * 帐号状态
   * 类型	说明
   * 1	正常
   * 14	已注销
   * 16	已封禁
   * 18	已告警
   * 19	已冻结
   */
  private Integer accountStatus;
  /**
   * 账号介绍
   */
  private String signature;

  /**
   * 可根据这个字段判断是否为小程序类型授权
   */
  private MiniProgramInfo miniProgramInfo;

  /**
   * 小程序注册方式
   * 类型	说明
   * 0	普通方式注册
   * 2	通过复用公众号创建小程序 api 注册
   * 6	通过法人扫脸创建企业小程序 api 注册
   * 13	通过创建试用小程序 api 注册
   * 15	通过联盟控制台注册
   * 16	通过创建个人小程序 api 注册
   * 17	通过创建个人交易小程序 api 注册
   * 19	通过试用小程序转正 api 注册
   * 22	通过复用商户号创建企业小程序 api 注册
   * 23	通过复用商户号转正 api 注册
   */
  private Integer registerType;

  /**
   * 小程序基础配置信息
   */
  private BasicConfig basicConfig;

  @Data
  public static class MiniProgramInfo implements Serializable {
    private static final long serialVersionUID = 8857028017332191988L;
    @SerializedName("visit_status")
    private Integer visitStatus;
    /**
     * 小程序已设置的各个服务器域名.
     */
    private Network network;
    private List<Category> categories;

    @Data
    public static class Category implements Serializable {
      private static final long serialVersionUID = -5771529867281696141L;
      private String first;
      private String second;
    }

    @Data
    public static class Network implements Serializable {
      private static final long serialVersionUID = -18932624803859857L;
      @SerializedName("RequestDomain")
      private List<String> requestDomain;
      @SerializedName("WsRequestDomain")
      private List<String> wsRequestDomain;
      @SerializedName("UploadDomain")
      private List<String> uploadDomain;
      @SerializedName("DownloadDomain")
      private List<String> downloadDomain;
      @SerializedName("BizDomain")
      private List<String> bizDomain;
    }
  }

  @Data
  public static class BasicConfig implements Serializable {
    private static final long serialVersionUID = -8857028017332191989L;
    @SerializedName("is_phone_configured")
    private Boolean isPhoneConfigured;
    @SerializedName("is_email_configured")
    private Boolean isEmailConfigured;
  }
}
