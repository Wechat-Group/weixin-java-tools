package me.chanjar.weixin.cp.bean.kf;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.cp.bean.kf.msg.WxCpKfLinkMsg;
import me.chanjar.weixin.cp.bean.kf.msg.WxCpKfLocationMsg;
import me.chanjar.weixin.cp.bean.kf.msg.WxCpKfMenuMsg;
import me.chanjar.weixin.cp.bean.kf.msg.WxCpKfMiniProgramMsg;
import me.chanjar.weixin.cp.bean.kf.msg.WxCpKfResourceMsg;
import me.chanjar.weixin.cp.bean.kf.msg.WxCpKfTextMsg;

/**
 * @author leiin
 * @date 2022/1/26 7:00 下午
 */
@NoArgsConstructor
@Data
public class WxCpKfMsgSendRequest {
  /**
   * (发送欢迎语等事件响应消息) 事件响应消息对应的code。通过事件回调下发，仅可使用一次。
   */
  private String code;
  /**
   * <pre>
   *   参数：touser
   *   是否必须：是
   *   类型：string
   *   说明：指定接收消息的客户UserID
   * </pre>
   */
  @SerializedName("touser")
  private String toUser;
  /**
   * <pre>
   *   参数：open_kfid
   *   是否必须：是
   *   类型：string
   *   说明：指定发送消息的客服帐号ID
   * </pre>
   */
  @SerializedName("open_kfid")
  private String openKfid;
  /**
   * <pre>
   *   参数：msgid
   *   是否必须：否
   *   类型：string
   *   说明：指定消息ID
   * </pre>
   */
  @SerializedName("msgid")
  private String msgId;
  /**
   * <pre>
   *   参数：msgtype
   *   是否必须：是
   *   类型：string
   *   说明：消息类型，（text,image,voice,video,file,link,miniprogram,msgmenu,location)
   * </pre>
   */
  @SerializedName("msgtype")
  private String msgType;
  /**
   * <pre>
   *   参数：text
   *   是否必须：是
   *   类型：obj
   *   说明：文本消息
   * </pre>
   */
  private WxCpKfTextMsg text;

  /**
   * <pre>
   *   参数：image
   *   是否必须：是
   *   类型：obj
   *   说明：图片消息
   * </pre>
   */
  private WxCpKfResourceMsg image;
  /**
   * <pre>
   *   参数：voice
   *   是否必须：是
   *   类型：obj
   *   说明：语音消息
   * </pre>
   */
  private WxCpKfResourceMsg voice;
  /**
   * <pre>
   *   参数：video
   *   是否必须：是
   *   类型：obj
   *   说明：视频消息
   * </pre>
   */
  private WxCpKfResourceMsg video;
  /**
   * <pre>
   *   参数：file
   *   是否必须：是
   *   类型：obj
   *   说明：文件消息
   * </pre>
   */
  private WxCpKfResourceMsg file;
  /**
   * <pre>
   *   参数：link
   *   是否必须：是
   *   类型：obj
   *   说明：链接消息
   * </pre>
   */
  private WxCpKfLinkMsg link;
  /**
   * <pre>
   *   参数：miniprogram
   *   是否必须：是
   *   类型：obj
   *   说明：小程序消息
   * </pre>
   */
  @SerializedName("miniprogram")
  private WxCpKfMiniProgramMsg miniProgram;

  /**
   * <pre>
   *   参数：msgmenu
   *   是否必须：是
   *   类型：obj
   *   说明：菜单消息
   * </pre>
   */
  @SerializedName("msgmenu")
  private WxCpKfMenuMsg msgMenu;

  /**
   * <pre>
   *   参数：location
   *   是否必须：是
   *   类型：obj
   *   说明：菜单消息
   * </pre>
   */
  @SerializedName("location")
  private WxCpKfLocationMsg location;
}
