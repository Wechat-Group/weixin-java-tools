package me.chanjar.weixin.cp.constant;

/**
 * <pre>
 * 企业微信常量
 * Created by Binary Wang on 2018/8/25.
 * </pre>
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
public class WxCpConsts {
  /**
   * 企业微信端推送过来的事件类型.
   * 参考文档：https://work.weixin.qq.com/api/doc#12974
   */
  public static enum EventType {
    /**
     * 成员关注事件.
     */
    SUBSCRIBE("subscribe"),

    /**
     * 成员取消关注事件.
     */
    UNSUBSCRIBE("unsubscribe"),

    /**
     * 进入应用事件.
     */
    ENTER_AGENT("enter_agent"),

    /**
     * 上报地理位置.
     */
    LOCATION("LOCATION"),

    /**
     * 异步任务完成事件推送.
     */
    BATCH_JOB_RESULT("batch_job_result"),

    /**
     * 企业微信通讯录变更事件.
     */
    CHANGE_CONTACT("change_contact"),

    /**
     * 点击菜单拉取消息的事件推送.
     */
    CLICK("click"),

    /**
     * 点击菜单跳转链接的事件推送.
     */
    VIEW("view"),

    /**
     * 扫码推事件的事件推送.
     */
    SCANCODE_PUSH("scancode_push"),

    /**
     * 扫码推事件且弹出“消息接收中”提示框的事件推送.
     */
    SCANCODE_WAITMSG("scancode_waitmsg"),

    /**
     * 弹出系统拍照发图的事件推送.
     */
    PIC_SYSPHOTO("pic_sysphoto"),

    /**
     * 弹出拍照或者相册发图的事件推送.
     */
    PIC_PHOTO_OR_ALBUM("pic_photo_or_album"),

    /**
     * 弹出微信相册发图器的事件推送.
     */
    PIC_WEIXIN("pic_weixin"),

    /**
     * 弹出地理位置选择器的事件推送.
     */
    LOCATION_SELECT("location_select"),

    /**
     * 任务卡片事件推送.
     */
    TASKCARD_CLICK("taskcard_click"),

    /**
     * 企业成员添加外部联系人事件推送
     */
    CHANGE_EXTERNAL_CONTACT("change_external_contact"),

    /**
     * 企业微信审批事件推送（自建应用审批）
     */
    OPEN_APPROVAL_CHANGE("open_approval_change"),

    /**
     * 企业微信审批事件推送（系统审批）
     */
    SYS_APPROVAL_CHANGE("sys_approval_change"),

    /**
     * 修改日历事件
     */
    MODIFY_CALENDAR("modify_calendar"),

    /**
     * 删除日历事件
     */
    DELETE_CALENDAR("delete_calendar"),

    /**
     * 添加日程事件
     */
    ADD_SCHEDULE("add_schedule"),

    /**
     * 修改日程事件
     */
    MODIFY_SCHEDULE("modify_schedule"),

    /**
     * 删除日程事件
     */
    DELETE_SCHEDULE("delete_schedule");

    private final String name;

    EventType(String name) {
      this.name = name;
    }

    public String getName() {
      return name;
    }

    @Override
    public String toString() {
      return this.name;
    }
  }

  /**
   * 企业外部联系人变更事件的CHANGE_TYPE
   */
  public static enum ExternalContactChangeType {
    /**
     * 新增外部联系人
     */
    ADD_EXTERNAL_CONTACT("add_external_contact"),
    /**
     * 删除外部联系人
     */
    DEL_EXTERNAL_CONTACT("del_external_contact"),

    /**
     * 外部联系人免验证添加成员事件
     */
    ADD_HALF_EXTERNAL_CONTACT("add_half_external_contact"),
    /**
     * 删除跟进成员事件
     */
    DEL_FOLLOW_USER("del_follow_user");

    private final String name;

    ExternalContactChangeType(String name) {
      this.name = name;
    }

    public String getName() {
      return name;
    }

    @Override
    public String toString() {
      return this.name;
    }
  }

  /**
   * 企业微信通讯录变更事件.
   */
  public static enum ContactChangeType {
    /**
     * 新增成员事件.
     */
    CREATE_USER("create_user"),

    /**
     * 更新成员事件.
     */
    UPDATE_USER("update_user"),

    /**
     * 删除成员事件.
     */
    DELETE_USER("delete_user"),

    /**
     * 新增部门事件.
     */
    CREATE_PARTY("create_party"),

    /**
     * 更新部门事件.
     */
    UPDATE_PARTY("update_party"),

    /**
     * 删除部门事件.
     */
    DELETE_PARTY("delete_party"),

    /**
     * 标签成员变更事件.
     */
    UPDATE_TAG("update_tag");

    private final String name;

    ContactChangeType(String name) {
      this.name = name;
    }

    public String getName() {
      return name;
    }

    @Override
    public String toString() {
      return this.name;
    }
  }

  /**
   * 互联企业发送应用消息的消息类型.
   */
  public static enum LinkedCorpMsgType {
    /**
     * 文本消息.
     */
    TEXT("text"),
    /**
     * 图片消息.
     */
    IMAGE("image"),
    /**
     * 视频消息.
     */
    VIDEO("video"),
    /**
     * 图文消息（点击跳转到外链）.
     */
    NEWS("news"),
    /**
     * 图文消息（点击跳转到图文消息页面）.
     */
    MPNEWS("mpnews"),
    /**
     * markdown消息.
     * （目前仅支持markdown语法的子集，微工作台（原企业号）不支持展示markdown消息）
     */
    MARKDOWN("markdown"),
    /**
     * 发送文件.
     */
    FILE("file"),
    /**
     * 文本卡片消息.
     */
    TEXTCARD("textcard"),

    /**
     * 小程序通知消息.
     */
    MINIPROGRAM_NOTICE("miniprogram_notice");

    private final String name;

    LinkedCorpMsgType(String name) {
      this.name = name;
    }

    public String getName() {
      return name;
    }

    @Override
    public String toString() {
      return this.name;
    }

    public static LinkedCorpMsgType getType(String name){
      for (LinkedCorpMsgType value : values()) {
        if(value.getName().equals(name)){
          return value;
        }
      }
      return null;
    }
  }

  /**
   * 群机器人的消息类型.
   */
  public static enum GroupRobotMsgType {
    /**
     * 文本消息.
     */
    TEXT("text"),

    /**
     * 图片消息.
     */
    IMAGE("image"),

    /**
     * markdown消息.
     */
    MARKDOWN("markdown"),

    /**
     * 图文消息（点击跳转到外链）.
     */
    NEWS("news");

    private final String name;

    GroupRobotMsgType(String name) {
      this.name = name;
    }

    public String getName() {
      return name;
    }

    @Override
    public String toString() {
      return this.name;
    }

    public static GroupRobotMsgType getType(String name){
      for (GroupRobotMsgType value : values()) {
        if(value.getName().equals(name)){
          return value;
        }
      }
      return null;
    }
  }

  /**
   * 应用推送消息的消息类型.
   */
  public static enum AppChatMsgType {
    /**
     * 文本消息.
     */
    TEXT("text"),
    /**
     * 图片消息.
     */
    IMAGE("image"),
    /**
     * 语音消息.
     */
    VOICE("voice"),
    /**
     * 视频消息.
     */
    VIDEO("video"),
    /**
     * 发送文件（CP专用）.
     */
    FILE("file"),
    /**
     * 文本卡片消息（CP专用）.
     */
    TEXTCARD("textcard"),
    /**
     * 图文消息（点击跳转到外链）.
     */
    NEWS("news"),
    /**
     * 图文消息（点击跳转到图文消息页面）.
     */
    MPNEWS("mpnews"),
    /**
     * markdown消息.
     */
    MARKDOWN("markdown");

    private final String name;

    AppChatMsgType(String name) {
      this.name = name;
    }

    public String getName() {
      return name;
    }

    @Override
    public String toString() {
      return this.name;
    }

    public static AppChatMsgType getType(String name) {
      for (AppChatMsgType value : values()) {
        if (value.getName().equals(name)) {
          return value;
        }
      }
      return null;
    }
  }

  public static enum WorkBenchType {
    /**
     * 关键数据型
     */
    KEYDATA("keydata"),
    /**
     * 图片型
     */
    IMAGE("image"),
    /**
     * 列表型
     */
    LIST("list"),
    /**
     * webview型
     */
    WEBVIEW("webview");

    private final String name;

    WorkBenchType(String name) {
      this.name = name;
    }

    public String getName() {
      return name;
    }

    @Override
    public String toString() {
      return this.name;
    }

    public static WorkBenchType getType(String name){
      for (WorkBenchType value : values()) {
        if(value.getName().equals(name)){
          return value;
        }
      }
      return null;
    }
  }

  public static enum AttachmentMsgType {
    /**
     * 图片消息.
     */
    IMAGE("image"),
    /**
     * 图文消息.
     */
    LINK("link"),
    /**
     * 视频消息.
     */
    VIDEO("video"),
    /**
     * 小程序消息.
     */
    MINIPROGRAM("miniprogram");

    private final String name;

    AttachmentMsgType(String name) {
      this.name = name;
    }

    public String getName() {
      return name;
    }

    @Override
    public String toString() {
      return this.name;
    }
  }
}
