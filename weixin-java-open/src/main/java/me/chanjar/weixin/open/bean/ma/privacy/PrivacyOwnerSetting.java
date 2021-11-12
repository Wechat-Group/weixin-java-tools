package me.chanjar.weixin.open.bean.ma.privacy;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

/**
 * 小程序用户隐私保护指引 收集方（开发者）信息配置
 *
 * @author <a href="https://www.sacoc.cn">广州跨界</a>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PrivacyOwnerSetting {

  /**
   * 信息收集方（开发者）的邮箱地址，4种联系方式至少要填一种
   */
  @SerializedName("contact_email")
  private String contactEmail;

  /**
   * 信息收集方（开发者）的手机号，4种联系方式至少要填一种
   */
  @SerializedName("contact_phone")
  private String contactPhone;

  /**
   * 信息收集方（开发者）的qq号，4种联系方式至少要填一种
   */
  @SerializedName("contact_qq")
  private String contactQq;

  /**
   * 信息收集方（开发者）的微信号，4种联系方式至少要填一种
   */
  @SerializedName("contact_weixin")
  private String contactWeixin;

  /**
   * 如果开发者不使用微信提供的标准化用户隐私保护指引模板，也可以上传自定义的用户隐私保护指引，通过上传接口上传后可获取media_id
   */
  @SerializedName("ext_file_media_id")
  private String extFileMediaId;

  /**
   * 通知方式，指的是当开发者收集信息有变动时，通过该方式通知用户。这里服务商需要按照实际情况填写，例如通过弹窗或者公告或者其他方式。
   */
  @NotNull
  @SerializedName("notice_method")
  private String noticeMethod;

  /**
   * 存储期限，指的是开发者收集用户信息存储多久。如果不填则展示为【开发者承诺，除法律法规另有规定，开发者对你的信息保存期限应当为实现处理目的所必要的最短时间】，
   * 如果填请填数字+天，例如“30天”，否则会出现87072的报错。
   */
  @SerializedName("store_expire_timestamp")
  private String storeExpireTimestamp;

}
