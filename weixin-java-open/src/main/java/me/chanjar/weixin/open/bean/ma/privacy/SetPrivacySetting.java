package me.chanjar.weixin.open.bean.ma.privacy;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * 设置小程序用户隐私保护指引参数
 *
 * @author <a href="https://www.sacoc.cn">广州跨界</a>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SetPrivacySetting {

  /**
   * 用户隐私保护指引的版本，1表示现网版本；2表示开发版。默认是2开发版。
   */
  @SerializedName("privacy_ver")
  private Integer privacyVer;

  /**
   * 收集方（开发者）信息配置
   */
  @NotNull
  @SerializedName("owner_setting")
  private PrivacyOwnerSetting ownerSetting;

  /**
   * 要收集的用户信息配置
   */
  @NotNull
  @SerializedName("setting_list")
  private List<Setting> settingList;


  @Data
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  public static class Setting {

    /**
     * 官方的可选值参考下方说明；该字段也支持自定义
     *
     * @see PrivacyKeyEnum
     * @see PrivacyKeyEnum#getKey()
     */
    @NotNull
    @SerializedName("privacy_key")
    private String privacyKey;

    /**
     * 请填写收集该信息的用途。例如privacy_key=Location（位置信息），那么privacy_text则填写收集位置信息的用途。
     * 无需再带上“为了”或者“用于”这些字眼，小程序端的显示格式是为了xxx，因此开发者只需要直接填写用途即可。
     */
    @NotNull
    @SerializedName("privacy_text")
    private String privacyText;
  }
}
