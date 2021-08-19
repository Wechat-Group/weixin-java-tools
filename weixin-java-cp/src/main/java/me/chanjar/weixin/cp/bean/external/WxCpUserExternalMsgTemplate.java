package me.chanjar.weixin.cp.bean.external;

import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Data;
import me.chanjar.weixin.cp.bean.external.msg.Attachment;
import me.chanjar.weixin.cp.bean.external.msg.Text;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * 企业群发消息任务
 * <p>
 * Created by songfan on 2020/7/14.
 *
 * @author songfan
 */
@Data
@Builder
public class WxCpUserExternalMsgTemplate implements Serializable {
  private static final long serialVersionUID = 3172331565173474358L;

  public static final String CHAT_TYPE_SINGLE = "single";
  ;

  public static final String CHAT_TYPE_GROUP = "group";

  /**
   * 群发任务的类型，默认为single，表示发送给客户，group表示发送给客户群
   */
  @SerializedName("chat_type")
  private String chatType;

  /**
   * 客户的外部联系人id列表，仅在chat_type为single时有效，不可与sender同时为空，最多可传入1万个客户
   */
  @SerializedName("external_userid")
  private List<String> externalUserid;

  /**
   * 发送企业群发消息的成员userid，当类型为发送给客户群时必填
   */
  @SerializedName("sender")
  private String sender;

  /**
   * 消息文本内容，最多4000个字节
   */
  @SerializedName("text")
  private Text text;

  /**
   * 附件
   */
  @SerializedName("attachments")
  private List<Attachment> attachments;

  public String toJson() {
    return WxCpGsonBuilder.create().toJson(this);
  }

}
