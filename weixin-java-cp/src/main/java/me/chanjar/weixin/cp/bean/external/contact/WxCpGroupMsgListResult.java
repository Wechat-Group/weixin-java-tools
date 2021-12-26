package me.chanjar.weixin.cp.bean.external.contact;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;
import me.chanjar.weixin.cp.bean.WxCpBaseResp;
import me.chanjar.weixin.cp.bean.external.msg.Attachment;
import me.chanjar.weixin.cp.bean.external.msg.Text;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * <pre>
 * 获取企业群发成员执行结果
 * 参考文档：https://work.weixin.qq.com/api/doc/90000/90135/93338
 * </pre>
 *
 * @author <a href="https://github.com/wslongchen">Mr.Pan</a>
 */
@Getter
@Setter
public class WxCpGroupMsgListResult extends WxCpBaseResp implements Serializable {

  private static final long serialVersionUID = 3464981991558716620L;

  @SerializedName("group_msg_list")
  private List<ExternalContactGroupMsgInfo> groupMsgList;

  @SerializedName("next_cursor")
  private String nextCursor;

  @Getter
  @Setter
  public static class ExternalContactGroupMsgInfo implements Serializable {

    private static final long serialVersionUID = 3108435608725559381L;
    @SerializedName("msgid")
    private String msgId;

    private String creator;

    private Text text;

    private List<Attachment> attachments;

    @SerializedName("create_type")
    private Integer createType;

    @SerializedName("create_time")
    private Long createTime;

  }

  public static WxCpGroupMsgListResult fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpGroupMsgListResult.class);
  }

}
