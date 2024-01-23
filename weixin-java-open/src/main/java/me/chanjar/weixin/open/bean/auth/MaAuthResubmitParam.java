package me.chanjar.weixin.open.bean.auth;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

/**
 * 小程序认证 重新提交操作 参数
 *
 * @author <a href="https://www.sacoc.cn">广州跨界</a>
 * created on 2024/01/11
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MaAuthResubmitParam {

  /**
   * 认证信息
   */
  @NotNull
  @SerializedName("auth_data")
  private MaAuthResubmitParamAuthData authData;
}
