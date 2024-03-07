package me.chanjar.weixin.open.bean.auth;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.chanjar.weixin.open.bean.result.WxOpenResult;
import org.jetbrains.annotations.NotNull;

/**
 * 小程序认证 上传补充材料操作 响应
 *
 * @author <a href="https://www.sacoc.cn">广州跨界</a>
 * created on 2024/01/11
 */
@Getter
@Setter
@NoArgsConstructor
public class MaAuthUploadResult extends WxOpenResult {

  /**
   * 媒体ID
   */
  @NotNull
  @SerializedName("mediaid")
  private String mediaId;
}
