package me.chanjar.weixin.open.bean.auth;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;
import me.chanjar.weixin.open.bean.result.WxOpenResult;
import org.jetbrains.annotations.NotNull;

/**
 * 小程序认证 查询操作 响应
 *
 * @author <a href="https://www.sacoc.cn">广州跨界</a>
 * created on 2024/01/11
 */
@Getter
@Setter
public class MaAuthQueryResult extends WxOpenResult {

  /**
   * 小程序ID
   */
  @NotNull
  @SerializedName("appid")
  private String appId;

  /**
   * 状态 0初始 1超24小时 2用户拒绝 3用户同意 4发起人脸 5人脸失败 6人脸ok 7人脸认证后手机验证码 8手机验证失败 9手机验证成功 11创建审核单失败 12创建审核单成功 14验证失败 15等待支付
   */
  @NotNull
  @SerializedName("task_status")
  private Integer taskStatus;

  /**
   * 授权链接
   */
  @NotNull
  @SerializedName("auth_url")
  private String authUrl;

  /**
   * 审核单状态，创建审核单成功后有效 0审核单不存在 1待支付 2审核中 3打回重填 4认证通过 5认证最终失败（不能再修改）
   */
  @SerializedName("apply_status")
  private Integer applyStatus;

  /**
   * 小程序后台展示的认证订单号
   */
  @SerializedName("orderid")
  private String orderId;

  /**
   * 当审核单被打回重填(apply_status=3)时有效
   */
  @SerializedName("refill_reason")
  private String refillReason;

  /**
   * 审核最终失败的原因(apply_status=5)时有效
   */
  @SerializedName("fail_reason")
  private String failReason;

}
