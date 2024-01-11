package me.chanjar.weixin.open.bean.message;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import me.chanjar.weixin.open.bean.ma.WxMaVerifyBetaWeappVerifyInfo;

import java.io.Serializable;

/**
 * 试用小程序快速认证(仅供第三方开发者代小程序调用)
 *
 * @author yqx
 * created on  2018/9/13
 */
@Data
public class WxOpenMaVerifyBetaWeappMessage implements Serializable {
  private static final long serialVersionUID = 4595618023108631478L;

  /**
   * 企业法人认证需要的信息
   */
  @SerializedName("verify_info")
  private WxMaVerifyBetaWeappVerifyInfo verifyInfo;
}
