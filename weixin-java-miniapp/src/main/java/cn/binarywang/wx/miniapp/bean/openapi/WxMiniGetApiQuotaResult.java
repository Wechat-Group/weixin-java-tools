package cn.binarywang.wx.miniapp.bean.openapi;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

/**
 * 查询API调用额度 返回数据
 *
 * @author shuiyihan12
 * @since 2023/7/10 16:52
 */
@Data
public class WxMiniGetApiQuotaResult {

  /**
   * 当天该账号可调用该接口的次数
   */
  @SerializedName("daily_limit")
  private Integer dailyLimit;
  /**
   * 当天已经调用的次数
   */
  private Integer used;
  /**
   * 当天剩余调用次数
   */
  private Integer remain;
}
