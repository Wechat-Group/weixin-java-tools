package me.chanjar.weixin.open.bean.auth;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import org.jetbrains.annotations.NotNull;

/**
 * 小程序认证 重新提交操作 参数
 *
 * @author <a href="https://www.sacoc.cn">广州跨界</a>
 * created on 2024/01/11
 */
@Data
public class MaAuthResubmitParam {

  /**
   * 认证信息
   */
  @NotNull
  @SerializedName("auth_data")
  private MaAuthResubmitParamAuthData authData;
}
