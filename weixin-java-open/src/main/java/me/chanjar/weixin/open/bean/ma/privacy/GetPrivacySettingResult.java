package me.chanjar.weixin.open.bean.ma.privacy;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import me.chanjar.weixin.open.bean.result.WxOpenResult;

import java.util.List;

/**
 * 查询小程序用户隐私保护指引 响应
 *
 * @author <a href="https://www.sacoc.cn">广州跨界</a>
 */
@Getter
@Setter
public class GetPrivacySettingResult extends WxOpenResult {

  /**
   * 代码是否存在， 0 不存在， 1 存在 。如果最近没有通过commit接口上传代码，则会出现 code_exist=0的情况。
   */
  @SerializedName("code_exist")
  private Integer codeExist;

  /**
   * 代码检测出来的用户信息类型（privacy_key）
   */
  @SerializedName("privacy_list")
  private List<String> privacyList;

  /**
   * 要收集的用户信息配置
   */
  @SerializedName("setting_list")
  private List<Setting> settingList;

  /**
   * 更新时间
   */
  @SerializedName("update_time")
  private Long updateTime;

  /**
   * 收集方（开发者）信息配置
   */
  @SerializedName("owner_setting")
  private PrivacyOwnerSetting ownerSetting;

  /**
   * 收集方（开发者）信息配置
   */
  @SerializedName("privacy_desc")
  private PrivacyDesc privacyDesc;


  @Data
  public static class Setting {

    /**
     * 官方的可选值参考下方说明；该字段也支持自定义
     *
     * @see PrivacyKeyEnum
     * @see PrivacyKeyEnum#getKey()
     */
    @SerializedName("privacy_key")
    private String privacyKey;

    /**
     * 请填写收集该信息的用途。例如privacy_key=Location（位置信息），那么privacy_text则填写收集位置信息的用途。
     * 无需再带上“为了”或者“用于”这些字眼，小程序端的显示格式是为了xxx，因此开发者只需要直接填写用途即可。
     */
    @SerializedName("privacy_text")
    private String privacyText;

    /**
     * 用户信息类型的中文名称
     *
     * @see PrivacyKeyEnum#getDesc() ()
     */
    @SerializedName("privacy_label")
    private String privacyLabel;
  }


  @Data
  public static class PrivacyDesc {

    /**
     * 用户信息类型
     */
    @SerializedName("privacy_desc_list")
    private List<PrivacyDescItem> privacyDescList;
  }

  @Data
  public static class PrivacyDescItem {

    /**
     * 用户信息类型的英文key
     *
     * @see PrivacyKeyEnum
     * @see PrivacyKeyEnum#getKey()
     */
    @SerializedName("privacy_key")
    private String privacyKey;

    /**
     * 用户信息类型的中文描述
     *
     * @see PrivacyKeyEnum#getDesc()
     */
    @SerializedName("privacy_desc")
    private String privacyDesc;
  }


}
