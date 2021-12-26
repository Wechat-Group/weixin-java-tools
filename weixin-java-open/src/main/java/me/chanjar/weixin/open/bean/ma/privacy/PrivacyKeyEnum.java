package me.chanjar.weixin.open.bean.ma.privacy;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 隐私key枚举
 *
 * @author <a href="https://www.sacoc.cn">广州跨界</a>
 */
@Getter
@AllArgsConstructor
public enum PrivacyKeyEnum {

  USER_INFO("UserInfo", "用户信息（微信昵称、头像）"),

  LOCATION("Location", "位置信息"),

  ADDRESS("Address", "地址"),

  INVOICE("Invoice", "发票信息"),

  RUN_DATA("RunData", "微信运动数据"),

  RECORD("Record", "麦克风"),

  ALBUM("Album", "选中的照片或视频信息"),

  CAMERA("Camera", "摄像头"),

  PHONE_NUMBER("PhoneNumber", "手机号码"),

  CONTACT("Contact", "通讯录（仅写入）权限"),

  DEVICE_INFO("DeviceInfo", "设备信息"),

  EXID_NUMBER("EXIDNumber", "身份证号码"),

  EX_ORDER_INFO("EXOrderInfo", "订单信息"),

  EX_USER_PUBLISH_CONTENT("EXUserPublishContent", "发布内容"),

  EX_USER_FOLLOW_ACCT("EXUserFollowAcct", "所关注账号"),

  EX_USER_OP_LOG("EXUserOpLog", "操作日志"),

  ALBUM_WRITE_ONLY("AlbumWriteOnly", "相册（仅写入）权限"),

  LICENSE_PLATE("LicensePlate", "车牌号"),

  BLUE_TOOTH("BlueTooth", "蓝牙"),

  CALENDAR_WRITE_ONLY("CalendarWriteOnly", "日历（仅写入）权限"),

  EMAIL("Email", "邮箱"),

  MESSAGE_FILE("MessageFile", "选中的文件"),
  ;

  private final String key;
  private final String desc;
}
