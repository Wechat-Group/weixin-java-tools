package me.chanjar.weixin.cp.bean.external;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import me.chanjar.weixin.cp.bean.external.msg.Attachment;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * @author caiqy
 */
@Data
public class WxCpUserExternalContactGroupMsgListV2Result implements Serializable {

  private static final long serialVersionUID = 5008048666042601882L;

  @SerializedName("errcode")
  private Integer errCode;

  @SerializedName("errmsg")
  private String errMsg;

  @SerializedName("next_cursor")
  private String nextCursor;

  @SerializedName("group_msg_list")
  private List<GroupMsg> groupMsgList;

  public static WxCpUserExternalContactGroupMsgListV2Result fromJson(String result) {
    return WxCpGsonBuilder.create().fromJson(result, WxCpUserExternalContactGroupMsgListV2Result.class);
  }

  @Data
  public static class GroupMsg implements Serializable {

    private static final long serialVersionUID = 8342028719617956464L;

    @SerializedName("msgid")
    private String msgId;

    @SerializedName("creator")
    private String creator;

    @SerializedName("create_time")
    private String createTime;

    /**
     * 群发消息创建来源。0：企业 1：个人
     */
    @SerializedName("create_type")
    private String createType;

    @SerializedName("attachments")
    private List<Attachment> attachments;

  }

}
