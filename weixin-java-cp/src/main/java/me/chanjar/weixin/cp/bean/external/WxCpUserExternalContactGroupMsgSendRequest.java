package me.chanjar.weixin.cp.bean.external;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.io.Serializable;

/**
 * @author caiqy
 */
@Data
public class WxCpUserExternalContactGroupMsgSendRequest implements Serializable {

  private static final long serialVersionUID = -3212279121189093522L;

  /**
   * 群发消息的id，通过获取群发记录列表接口返回
   */
  @SerializedName("msgid")
  private String msgId;

  /**
   * 发送成员userid，通过获取群发成员发送任务列表接口返回
   */
  @SerializedName("userid")
  private String userId;

  /**
   * 用于分页查询的游标，字符串类型，由上一次调用返回，首次调用可不填
   */
  @SerializedName("cursor")
  private String cursor;

  /**
   * 返回的最大记录数，整型，最大值100，默认值50，超过最大值时取默认值
   */
  @SerializedName("limit")
  private int limit;

  public String toJson() {
    return WxCpGsonBuilder.create().toJson(this);
  }
}
