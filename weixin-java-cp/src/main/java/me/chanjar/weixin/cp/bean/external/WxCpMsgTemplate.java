package me.chanjar.weixin.cp.bean.external;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
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
 * @author songfan & Mr.Pan
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WxCpMsgTemplate implements Serializable {
  private static final long serialVersionUID = 3172331565173474358L;

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
  private String sender;

  /**
   * 消息文本内容，最多4000个字节
   */
  private Text text;

  /**
   * 附件，最多支持添加9个附件
   */
  private List<Attachment> attachments;

  public static WxCpMsgTemplate fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpMsgTemplate.class);
  }

  public String toJson() {
    return WxCpGsonBuilder.create().toJson(this);
  }

}
