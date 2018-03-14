package me.chanjar.weixin.common.api;

import java.util.HashMap;
import java.util.Map;

/**
 * 微信开发所使用到的常量类.
 *
 * @author chanjarster & binarywang
 */
public class WxConsts {
  /**
   * 微信推送过来的消息的类型，和发送给微信xml格式消息的消息类型.
   */
  public static class XmlMsgType {
    public static final String TEXT = "text";
    public static final String IMAGE = "image";
    public static final String VOICE = "voice";
    public static final String SHORTVIDEO = "shortvideo";
    public static final String VIDEO = "video";
    public static final String NEWS = "news";
    public static final String MUSIC = "music";
    public static final String LOCATION = "location";
    public static final String LINK = "link";
    public static final String EVENT = "event";
    public static final String DEVICE_TEXT = "device_text";
    public static final String DEVICE_EVENT = "device_event";
    public static final String DEVICE_STATUS = "device_status";
    public static final String HARDWARE = "hardware";
    public static final String TRANSFER_CUSTOMER_SERVICE = "transfer_customer_service";
  }

  /**
   * 主动发送消息(即客服消息)的消息类型.
   */
  public static class KefuMsgType {
    /**
     * 文本消息.
     */
    public static final String TEXT = "text";
    /**
     * 图片消息.
     */
    public static final String IMAGE = "image";
    /**
     * 语音消息.
     */
    public static final String VOICE = "voice";
    /**
     * 视频消息.
     */
    public static final String VIDEO = "video";
    /**
     * 音乐消息.
     */
    public static final String MUSIC = "music";
    /**
     * 图文消息（点击跳转到外链）.
     */
    public static final String NEWS = "news";
    /**
     * 图文消息（点击跳转到图文消息页面）.
     */
    public static final String MPNEWS = "mpnews";
    /**
     * 发送文件（CP专用）.
     */
    public static final String FILE = "file";
    /**
     * 文本卡片消息（CP专用）.
     */
    public static final String TEXTCARD = "textcard";
    /**
     * 卡券消息.
     */
    public static final String WXCARD = "wxcard";
    /**
     * 转发到客服的消息.
     */
    public static final String TRANSFER_CUSTOMER_SERVICE = "transfer_customer_service";
  }

  /**
   * 表示是否是保密消息，0表示否，1表示是，默认0.
   */
  public static class KefuMsgSafe {
    public static final String NO = "0";
    public static final String YES = "1";
  }

  /**
   * 群发消息的消息类型.
   */
  public static class MassMsgType {
    public static final String MPNEWS = "mpnews";
    public static final String TEXT = "text";
    public static final String VOICE = "voice";
    public static final String IMAGE = "image";
    public static final String MPVIDEO = "mpvideo";
  }

  /**
   * 群发消息后微信端推送给服务器的反馈消息.
   */
  public static class MassMsgStatus {
    public static final String SEND_SUCCESS = "send success";
    public static final String SEND_FAIL = "send fail";
    public static final String ERR_10001 = "err(10001)";
    public static final String ERR_20001 = "err(20001)";
    public static final String ERR_20004 = "err(20004)";
    public static final String ERR_20002 = "err(20002)";
    public static final String ERR_20006 = "err(20006)";
    public static final String ERR_20008 = "err(20008)";
    public static final String ERR_20013 = "err(20013)";
    public static final String ERR_22000 = "err(22000)";
    public static final String ERR_21000 = "err(21000)";

    /**
     * 群发反馈消息代码所对应的文字描述.
     */
    public static final Map<String, String> STATUS_DESC = new HashMap<>();

    static {
      STATUS_DESC.put(SEND_SUCCESS, "发送成功");
      STATUS_DESC.put(SEND_FAIL, "发送失败");
      STATUS_DESC.put(ERR_10001, "涉嫌广告");
      STATUS_DESC.put(ERR_20001, "涉嫌政治");
      STATUS_DESC.put(ERR_20004, "涉嫌社会");
      STATUS_DESC.put(ERR_20002, "涉嫌色情");
      STATUS_DESC.put(ERR_20006, "涉嫌违法犯罪");
      STATUS_DESC.put(ERR_20008, "涉嫌欺诈");
      STATUS_DESC.put(ERR_20013, "涉嫌版权");
      STATUS_DESC.put(ERR_22000, "涉嫌互推_互相宣传");
      STATUS_DESC.put(ERR_21000, "涉嫌其他");
    }
  }

  /**
   * 微信端推送过来的事件类型.
   */
  public static class EventType {
    public static final String SUBSCRIBE = "subscribe";
    public static final String UNSUBSCRIBE = "unsubscribe";
    public static final String SCAN = "SCAN";
    public static final String LOCATION = "LOCATION";
    public static final String CLICK = "CLICK";
    public static final String VIEW = "VIEW";
    public static final String MASS_SEND_JOB_FINISH = "MASSSENDJOBFINISH";
    public static final String SCANCODE_PUSH = "scancode_push";
    public static final String SCANCODE_WAITMSG = "scancode_waitmsg";
    public static final String PIC_SYSPHOTO = "pic_sysphoto";
    public static final String PIC_PHOTO_OR_ALBUM = "pic_photo_or_album";
    public static final String PIC_WEIXIN = "pic_weixin";
    public static final String LOCATION_SELECT = "location_select";
    public static final String TEMPLATE_SEND_JOB_FINISH = "TEMPLATESENDJOBFINISH";
    public static final String ENTER_AGENT = "enter_agent";
  }

  /**
   * 上传多媒体（临时素材）文件的类型.
   */
  public static class MediaFileType {
    public static final String IMAGE = "image";
    public static final String VOICE = "voice";
    public static final String VIDEO = "video";
    public static final String THUMB = "thumb";
    public static final String FILE = "file";
  }

  /**
   * 自定义菜单的按钮类型.
   */
  public static class MenuButtonType {
    /**
     * 点击推事件.
     */
    public static final String CLICK = "click";
    /**
     * 跳转URL.
     */
    public static final String VIEW = "view";
    /**
     * 跳转到小程序.
     */
    public static final String MINIPROGRAM = "miniprogram";
    /**
     * 扫码推事件.
     */
    public static final String SCANCODE_PUSH = "scancode_push";
    /**
     * 扫码推事件且弹出“消息接收中”提示框.
     */
    public static final String SCANCODE_WAITMSG = "scancode_waitmsg";
    /**
     * 弹出系统拍照发图.
     */
    public static final String PIC_SYSPHOTO = "pic_sysphoto";
    /**
     * 弹出拍照或者相册发图.
     */
    public static final String PIC_PHOTO_OR_ALBUM = "pic_photo_or_album";
    /**
     * 弹出微信相册发图器.
     */
    public static final String PIC_WEIXIN = "pic_weixin";
    /**
     * 弹出地理位置选择器.
     */
    public static final String LOCATION_SELECT = "location_select";
    /**
     * 下发消息（除文本消息）.
     */
    public static final String MEDIA_ID = "media_id";
    /**
     * 跳转图文消息URL.
     */
    public static final String VIEW_LIMITED = "view_limited";
  }

  /**
   * oauth2网页授权的scope.
   */
  public static class OAuth2Scope {
    /**
     * 不弹出授权页面，直接跳转，只能获取用户openid.
     */
    public static final String SNSAPI_BASE = "snsapi_base";
    /**
     * 弹出授权页面，可通过openid拿到昵称、性别、所在地。并且，即使在未关注的情况下，只要用户授权，也能获取其信息.
     */
    public static final String SNSAPI_USERINFO = "snsapi_userinfo";
  }

  /**
   * 网页应用登录授权作用域.
   */
  public static class QrConnectScope {
    public static final String SNSAPI_LOGIN = "snsapi_login";
  }

  /**
   * 永久素材类型.
   */
  public static class MaterialType {
    public static final String NEWS = "news";
    public static final String VOICE = "voice";
    public static final String IMAGE = "image";
    public static final String VIDEO = "video";
  }
}
