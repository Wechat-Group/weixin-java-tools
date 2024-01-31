package me.chanjar.weixin.cp.bean.oa.mail;

import com.google.gson.annotations.SerializedName;
import lombok.*;
import lombok.experimental.Accessors;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * 发送普通邮件请求.
 *
 * @author Hugo
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class WxCpMailCommonSendRequest implements Serializable {
  private static final long serialVersionUID = -4961239393895454138L;

  /**
   * 收件人，to.emails 和 to.userids 至少传一个
   */
  @SerializedName("to")
  private TO to;

  /**
   * 抄送
   */
  @SerializedName("cc")
  private CC cc;

  /**
   * 文档类型, 3:文档 4:表格
   */
  @SerializedName("bcc")
  private BCC bcc;

  /**
   * 标题
   */
  @SerializedName("subject")
  private String subject;

  /**
   * 内容
   */
  @SerializedName("content")
  private String content;

  /**
   * 附件相关
   */
  @SerializedName("attachment_list")
  private List<Attachment> attachmentList;

  /**
   * 内容类型 html，text（默认是html）
   */
  @SerializedName("content_type")
  private String contentType;

  /**
   * 表示是否开启id转译，0表示否，1表示是，默认0。仅第三方应用需要用到，企业自建应用可以忽略。
   * 目前仅subject、content、attachment_list[].file_name字段支持转译。
   */
  @SerializedName("enable_id_trans")
  private Integer enableIdTrans;

  @Getter
  @Setter
  public static class TO implements Serializable {
    private static final long serialVersionUID = -4860239393895754598L;

    /**
     * 收件人，邮箱地址
     */
    @SerializedName("emails")
    private List<String> emails;

    /**
     * 收件人，企业内成员的userid
     */
    @SerializedName("userids")
    private List<String> userIds;

    /**
     * From json space info.
     *
     * @param json the json
     * @return the space info
     */
    public static WxCpMailCommonSendRequest.TO fromJson(String json) {
      return WxCpGsonBuilder.create().fromJson(json, WxCpMailCommonSendRequest.TO.class);
    }

    /**
     * To json string.
     *
     * @return the string
     */
    public String toJson() {
      return WxCpGsonBuilder.create().toJson(this);
    }

  }

  @Getter
  @Setter
  public static class CC implements Serializable {
    private static final long serialVersionUID = -4863239393895754598L;

    /**
     * 抄送人，邮箱地址
     */
    @SerializedName("emails")
    private List<String> emails;

    /**
     * 抄送人，企业内成员的userid
     */
    @SerializedName("userids")
    private List<String> userIds;

    /**
     * From json space info.
     *
     * @param json the json
     * @return the space info
     */
    public static WxCpMailCommonSendRequest.CC fromJson(String json) {
      return WxCpGsonBuilder.create().fromJson(json, WxCpMailCommonSendRequest.CC.class);
    }

    /**
     * To json string.
     *
     * @return the string
     */
    public String toJson() {
      return WxCpGsonBuilder.create().toJson(this);
    }

  }

  @Getter
  @Setter
  public static class BCC implements Serializable {
    private static final long serialVersionUID = -4860239393885754598L;

    /**
     * 密送人，邮箱地址
     */
    @SerializedName("emails")
    private List<String> emails;

    /**
     * 密送人，企业内成员的userid
     */
    @SerializedName("userids")
    private List<String> userIds;

    /**
     * From json space info.
     *
     * @param json the json
     * @return the space info
     */
    public static WxCpMailCommonSendRequest.BCC fromJson(String json) {
      return WxCpGsonBuilder.create().fromJson(json, WxCpMailCommonSendRequest.BCC.class);
    }

    /**
     * To json string.
     *
     * @return the string
     */
    public String toJson() {
      return WxCpGsonBuilder.create().toJson(this);
    }

  }

  @Getter
  @Setter
  public static class Attachment implements Serializable {
    private static final long serialVersionUID = -4860230393895754598L;

    /**
     * 文件名
     */
    @SerializedName("file_name")
    private String fileName;

    /**
     * 文件内容（base64编码），所有附件加正文的大小不允许超过50M, 且附件个数不能超过200个
     */
    @SerializedName("content")
    private String content;

    /**
     * From json space info.
     *
     * @param json the json
     * @return the space info
     */
    public static WxCpMailCommonSendRequest.Attachment fromJson(String json) {
      return WxCpGsonBuilder.create().fromJson(json, WxCpMailCommonSendRequest.Attachment.class);
    }

    /**
     * To json string.
     *
     * @return the string
     */
    public String toJson() {
      return WxCpGsonBuilder.create().toJson(this);
    }

  }

  /**
   * From json wx cp space create request.
   *
   * @param json the json
   * @return the wx cp space create request
   */
  public static WxCpMailCommonSendRequest fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpMailCommonSendRequest.class);
  }

  /**
   * To json string.
   *
   * @return the string
   */
  public String toJson() {
    return WxCpGsonBuilder.create().toJson(this);
  }

}
