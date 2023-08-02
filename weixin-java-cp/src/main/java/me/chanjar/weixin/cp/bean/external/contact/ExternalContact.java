package me.chanjar.weixin.cp.bean.external.contact;

import com.google.gson.annotations.SerializedName;
import lombok.*;

import java.io.Serializable;
import java.util.List;

/**
 * 外部联系人.
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a> created on  2020-11-04
 */
@Getter
@Setter
public class ExternalContact implements Serializable {
  private static final long serialVersionUID = -1049085217436072418L;

  @SerializedName("external_userid")
  private String externalUserId;

  @SerializedName("position")
  private String position;

  @SerializedName("name")
  private String name;

  @SerializedName("nickname")
  private String nickname;

  @SerializedName("avatar")
  private String avatar;

  @SerializedName("corp_name")
  private String corpName;

  @SerializedName("corp_full_name")
  private String corpFullName;

  @SerializedName("type")
  private Integer type;

  @SerializedName("gender")
  private Integer gender;

  @SerializedName("unionid")
  private String unionId;

  @SerializedName("external_profile")
  private ExternalProfile externalProfile;

  /**
   * The type External profile.
   */
  @Data
  public static class ExternalProfile implements Serializable {
    private static final long serialVersionUID = -2899906589789022765L;

    @SerializedName("external_corp_name")
    private String externalCorpName;

    @SerializedName("wechat_channels")
    private WechatChannel wechatChannels;

    @SerializedName("external_attr")
    private List<ExternalAttribute> externalAttrs;
  }

  /**
   * The type Wechat channel.
   */
  @Data
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  public static class WechatChannel implements Serializable {

    @SerializedName("nickname")
    private String nickname;

    @SerializedName("status")
    private Integer status;

  }

  /**
   * The type External attribute.
   */
  @Data
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  public static class ExternalAttribute implements Serializable {
    private static final long serialVersionUID = -1262278808286421085L;

    private int type;

    private String name;

    private Text text;

    private Web web;

    @SerializedName("miniprogram")
    private MiniProgram miniProgram;

    /**
     * The type Text.
     */
    @Data
    public static class Text implements Serializable {
      private static final long serialVersionUID = -8161579335600269094L;

      private String value;
    }

    /**
     * The type Web.
     */
    @Data
    public static class Web implements Serializable {
      private static final long serialVersionUID = 3664557135411521862L;

      private String title;
      private String url;
    }

    /**
     * The type Mini program.
     */
    @Data
    public static class MiniProgram implements Serializable {
      private static final long serialVersionUID = -5329210594501835796L;

      @SerializedName("pagepath")
      private String pagePath;

      private String appid;

      private String title;
    }
  }
}
