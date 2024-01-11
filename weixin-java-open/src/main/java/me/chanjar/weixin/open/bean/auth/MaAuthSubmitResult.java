package me.chanjar.weixin.open.bean.auth;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.chanjar.weixin.open.bean.result.WxOpenResult;
import org.jetbrains.annotations.NotNull;

/**
 * 小程序认证 提交操作 响应
 *
 * @author <a href="https://www.sacoc.cn">广州跨界</a>
 * created on 2024/01/11
 */
@Getter
@Setter
@NoArgsConstructor
public class MaAuthSubmitResult extends WxOpenResult {

  /**
   * 任务ID
   */
  @NotNull
  @SerializedName("taskid")
  private String taskId;

  /**
   * 小程序管理员授权链接
   */
  @NotNull
  @SerializedName("auth_url")
  private String authUrl;
}
