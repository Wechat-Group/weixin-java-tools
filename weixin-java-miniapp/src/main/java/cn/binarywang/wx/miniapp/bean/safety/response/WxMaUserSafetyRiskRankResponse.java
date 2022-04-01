package cn.binarywang.wx.miniapp.bean.safety.response;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.common.util.json.WxGsonBuilder;

import java.io.Serializable;

/**
 * 获取用户的安全等级响应参数
 *
 * @author <a href="https://github.com/azouever">azouever</a>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WxMaUserSafetyRiskRankResponse implements Serializable {

  private static final long serialVersionUID = -2434941857751339150L;

  /**
   * 唯一请求标识，标记单次请求
   */
  @SerializedName("unoin_id")
  private Integer unoinId;

  /**
   * 用户风险等级
   * 合法值 https://developers.weixin.qq.com/miniprogram/dev/api-backend/open-api/safety-control-capability/riskControl.getUserRiskRank.html
   */
  @SerializedName("risk_rank")
  private Integer riskRank;

  public static WxMaUserSafetyRiskRankResponse fromJson(String json) {
    return WxGsonBuilder.create().fromJson(json, WxMaUserSafetyRiskRankResponse.class);
  }
}

