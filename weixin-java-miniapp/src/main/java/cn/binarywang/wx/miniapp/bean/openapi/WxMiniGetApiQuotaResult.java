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
   * quota详情
   */
  private WxMiniGetApiQuotaDetail quota;
  /**
   * 普通调用频率限制
   */
  private WxMiniGetApiQuotaRateLimit rateLimit;
  /**
   * 代调用频率限制
   */
  private WxMiniGetApiQuotaComponentRateLimit componentRateLimit;


  /**
   * quota详情
   */
  @Data
  private static class WxMiniGetApiQuotaDetail {
    /**
     * 当天该账号可调用该接口的次数
     */
    @SerializedName("daily_limit")
    private Long dailyLimit;
    /**
     * 当天已经调用的次数
     */
    private Long used;
    /**
     * 当天剩余调用次数
     */
    private Long remain;
  }

  /**
   * 普通调用频率限制
   */
  @Data
  private static class WxMiniGetApiQuotaRateLimit {
    /**
     * 周期内可调用数量，单位 次
     */
    @SerializedName("call_count")
    private Long callCount;
    /**
     * 更新周期，单位 秒
     */
    @SerializedName("refresh_second")
    private Long refreshSecond;
  }

  /**
   * 代调用频率限制
   */
  @Data
  private static class WxMiniGetApiQuotaComponentRateLimit {
    /**
     * 周期内可调用数量，单位 次
     */
    @SerializedName("call_count")
    private Long callCount;
    /**
     * 更新周期，单位 秒
     */
    @SerializedName("refresh_second")
    private Long refreshSecond;
  }

}
