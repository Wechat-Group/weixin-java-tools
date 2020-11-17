package me.chanjar.weixin.cp.constant;

import lombok.experimental.UtilityClass;

/**
 * <pre>
 * 企业微信常量
 * Created by Binary Wang on 2018/8/25.
 * </pre>
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
@UtilityClass
public class WxCpConsts {
  /**
   * 企业微信端推送过来的事件类型.
   * 参考文档：https://work.weixin.qq.com/api/doc#12974
   */
  @UtilityClass
  public static class EventType {
    /**
     * 成员关注事件.
     */
    public static final String SUBSCRIBE = "subscribe";

    /**
     * 成员取消关注事件.
     */
    public static final String UNSUBSCRIBE = "unsubscribe";

    /**
     * 进入应用事件.
     */
    public static final String ENTER_AGENT = "enter_agent";

    /**
     * 上报地理位置.
     */
    public static final String LOCATION = "LOCATION";

    /**
     * 异步任务完成事件推送.
     */
    public static final String BATCH_JOB_RESULT = "batch_job_result";

    /**
     * 企业微信通讯录变更事件.
     */
    public static final String CHANGE_CONTACT = "change_contact";

    /**
     * 点击菜单拉取消息的事件推送.
     */
    public static final String CLICK = "click";

    /**
     * 点击菜单跳转链接的事件推送.
     */
    public static final String VIEW = "view";

    /**
     * 扫码推事件的事件推送.
     */
    public static final String SCANCODE_PUSH = "scancode_push";

    /**
     * 扫码推事件且弹出“消息接收中”提示框的事件推送.
     */
    public static final String SCANCODE_WAITMSG = "scancode_waitmsg";

    /**
     * 弹出系统拍照发图的事件推送.
     */
    public static final String PIC_SYSPHOTO = "pic_sysphoto";

    /**
     * 弹出拍照或者相册发图的事件推送.
     */
    public static final String PIC_PHOTO_OR_ALBUM = "pic_photo_or_album";

    /**
     * 弹出微信相册发图器的事件推送.
     */
    public static final String PIC_WEIXIN = "pic_weixin";

    /**
     * 弹出地理位置选择器的事件推送.
     */
    public static final String LOCATION_SELECT = "location_select";

    /**
     * 任务卡片事件推送.
     */
    public static final String TASKCARD_CLICK = "taskcard_click";

    /**
     * 企业成员添加外部联系人事件推送
     */
    public static final String CHANGE_EXTERNAL_CONTACT = "change_external_contact";

    /**
     * 企业微信审批事件推送（自建应用审批）
     */
    public static final String OPEN_APPROVAL_CHANGE = "open_approval_change";

    /**
     * 企业微信审批事件推送（系统审批）
     */
    public static final String SYS_APPROVAL_CHANGE = "sys_approval_change";

    /**
     * 修改日历事件
     */
    public static final String MODIFY_CALENDAR = "modify_calendar";

    /**
     * 删除日历事件
     */
    public static final String DELETE_CALENDAR = "delete_calendar";

    /**
     * 添加日程事件
     */
    public static final String ADD_SCHEDULE = "add_schedule";

    /**
     * 修改日程事件
     */
    public static final String MODIFY_SCHEDULE = "modify_schedule";

    /**
     * 删除日程事件
     */
    public static final String DELETE_SCHEDULE = "delete_schedule";

  }

  /**
   * 企业外部联系人变更事件的CHANGE_TYPE
   */
  @UtilityClass
  public static class ExternalContactChangeType {
    /**
     * 新增外部联系人
     */
    public static final String ADD_EXTERNAL_CONTACT = "add_external_contact";
    /**
     * 删除外部联系人
     */
    public static final String DEL_EXTERNAL_CONTACT = "del_external_contact";

    /**
     * 外部联系人免验证添加成员事件
     */
    public static final String ADD_HALF_EXTERNAL_CONTACT = "add_half_external_contact";
    /**
     * 删除跟进成员事件
     */
    public static final String DEL_FOLLOW_USER = "del_follow_user";
  }

  /**
   * 企业微信通讯录变更事件.
   */
  @UtilityClass
  public static class ContactChangeType {
    /**
     * 新增成员事件.
     */
    public static final String CREATE_USER = "create_user";

    /**
     * 更新成员事件.
     */
    public static final String UPDATE_USER = "update_user";

    /**
     * 删除成员事件.
     */
    public static final String DELETE_USER = "delete_user";

    /**
     * 新增部门事件.
     */
    public static final String CREATE_PARTY = "create_party";

    /**
     * 更新部门事件.
     */
    public static final String UPDATE_PARTY = "update_party";

    /**
     * 删除部门事件.
     */
    public static final String DELETE_PARTY = "delete_party";

    /**
     * 标签成员变更事件.
     */
    public static final String UPDATE_TAG = "update_tag";

  }

  /**
   * 互联企业发送应用消息的消息类型.
   */
  @UtilityClass
  public static class LinkedCorpMsgType {
    /**
     * 文本消息.
     */
    public static final String TEXT = "text";
    /**
     * 图片消息.
     */
    public static final String IMAGE = "image";
    /**
     * 视频消息.
     */
    public static final String VIDEO = "video";
    /**
     * 图文消息（点击跳转到外链）.
     */
    public static final String NEWS = "news";
    /**
     * 图文消息（点击跳转到图文消息页面）.
     */
    public static final String MPNEWS = "mpnews";
    /**
     * markdown消息.
     * （目前仅支持markdown语法的子集，微工作台（原企业号）不支持展示markdown消息）
     */
    public static final String MARKDOWN = "markdown";
    /**
     * 发送文件.
     */
    public static final String FILE = "file";
    /**
     * 文本卡片消息.
     */
    public static final String TEXTCARD = "textcard";

    /**
     * 小程序通知消息.
     */
    public static final String MINIPROGRAM_NOTICE = "miniprogram_notice";
  }

  /**
   * 群机器人的消息类型.
   */
  @UtilityClass
  public static class GroupRobotMsgType {
    /**
     * 文本消息.
     */
    public static final String TEXT = "text";

    /**
     * 图片消息.
     */
    public static final String IMAGE = "image";

    /**
     * markdown消息.
     */
    public static final String MARKDOWN = "markdown";

    /**
     * 图文消息（点击跳转到外链）.
     */
    public static final String NEWS = "news";
  }

  /**
   * 应用推送消息的消息类型.
   */
  @UtilityClass
  public static class AppChatMsgType {
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
     * 发送文件（CP专用）.
     */
    public static final String FILE = "file";
    /**
     * 文本卡片消息（CP专用）.
     */
    public static final String TEXTCARD = "textcard";
    /**
     * 图文消息（点击跳转到外链）.
     */
    public static final String NEWS = "news";
    /**
     * 图文消息（点击跳转到图文消息页面）.
     */
    public static final String MPNEWS = "mpnews";
    /**
     * markdown消息.
     */
    public static final String MARKDOWN = "markdown";
  }

  @UtilityClass
  public static class WorkBenchType {
    /*
    * 关键数据型
    * */
    public static final String KEYDATA = "keydata";
    /*
    * 图片型
    * */
    public static final String IMAGE = "image";
    /*
    * 列表型
    * */
    public static final String LIST = "list";
    /*
    * webview型
    * */
    public static final String WEBVIEW = "webview";
  }
}
