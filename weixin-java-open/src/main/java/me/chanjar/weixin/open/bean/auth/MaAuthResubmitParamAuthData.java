package me.chanjar.weixin.open.bean.auth;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

/**
 * 小程序认证 重新提交操作 认证参数
 *
 * @author <a href="https://www.sacoc.cn">广州跨界</a>
 * created on 2024/01/11
 */
@Getter
@Setter
public class MaAuthResubmitParamAuthData extends MaAuthSubmitParamAuthData {

  /**
   * 认证任务id
   */
  @NotNull
  @SerializedName("taskid")
  private String taskId;
}
