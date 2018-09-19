package cn.binarywang.wx.miniapp.bean.analysis;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;

/**
 * 小程序概况趋势
 *
 * @author <a href="https://github.com/charmingoh">Charming</a>
 * @since 2018-04-28
 */
@Data
public class WxMaSummaryTrend implements Serializable {
  private static final long serialVersionUID = 1379688517709317935L;
  /**
   * 日期，yyyyMMdd 格式，如 20170313
   */
  @SerializedName(value = "refDate", alternate = "ref_date")
  private String refDate;
  /**
   * 累计用户数
   */
  @SerializedName(value = "visitTotal", alternate = "visit_total")
  private Long visitTotal;
  /**
   * 转发次数
   */
  @SerializedName(value = "sharePv", alternate = "share_pv")
  private Long sharePv;
  /**
   * 转发人数
   */
  @SerializedName(value = "shareUv", alternate = "share_uv")
  private Long shareUv;
}
