package cn.binarywang.wx.miniapp.bean.analysis;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;

/**
 * 访问趋势
 *
 * @author <a href="https://github.com/charmingoh">Charming</a>
 * @since 2018-04-28
 */
@Data
public class WxMaVisitTrend implements Serializable {
  private static final long serialVersionUID = 1379688517709317935L;
  /**
   * 日留存：日期，yyyyMMdd 格式，如 20170313
   * 周留存：时间，如"20170306-20170312"
   * 月留存：时间，如"201702"
   */
  @SerializedName(value = "refDate", alternate = "ref_date")
  private String refDate;
  /**
   * 打开次数
   */
  @SerializedName(value = "sessionCnt", alternate = "session_cnt")
  private Long sessionCnt;
  /**
   * 访问次数
   */
  @SerializedName(value = "visitPv", alternate = "visit_pv")
  private Long visitPv;
  /**
   * 访问人数
   */
  @SerializedName(value = "visitUv", alternate = "visit_uv")
  private Long visitUv;
  /**
   * 新用户数
   */
  @SerializedName(value = "visitUvNew", alternate = "visit_uv_new")
  private Long visitUvNew;
  /**
   * 人均停留时长 (浮点型，单位：秒)
   */
  @SerializedName(value = "stayTimeUv", alternate = "stay_time_uv")
  private Float stayTimeUv;
  /**
   * 人均停留时长 (浮点型，单位：秒)
   */
  @SerializedName(value = "stayTimeSession", alternate = "stay_time_session")
  private Float stayTimeSession;
  /**
   * 人均停留时长 (浮点型，单位：秒)
   */
  @SerializedName(value = "visitDepth", alternate = "visit_depth")
  private Float visitDepth;
}
