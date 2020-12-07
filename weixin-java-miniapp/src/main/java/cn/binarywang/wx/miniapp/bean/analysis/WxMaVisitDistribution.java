package cn.binarywang.wx.miniapp.bean.analysis;

import cn.binarywang.wx.miniapp.json.WxMaGsonBuilder;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;
import java.util.Map;

/**
 * 访问分布
 * 访问来源：(index="access_source_session_cnt")
 * 1：小程序历史列表
 * 2：搜索
 * 3：会话
 * 4：二维码
 * 5：公众号主页
 * 6：聊天顶部
 * 7：系统桌面
 * 8：小程序主页
 * 9：附近的小程序
 * 10：其他
 * 11：模板消息
 * 12：客服消息
 * 13: 公众号菜单
 * 14: APP分享
 * 15: 支付完成页
 * 16: 长按识别二维码
 * 17: 相册选取二维码
 * 18: 公众号文章
 * 19：钱包
 * 20：卡包
 * 21：小程序内卡券
 * 22：其他小程序
 * 23：其他小程序返回
 * 24：卡券适用门店列表
 * 25：搜索框快捷入口
 * 26：小程序客服消息
 * 27：公众号下发
 * 访问时长：(index="access_staytime_info")
 * 1: 0-2s
 * 2: 3-5s
 * 3: 6-10s
 * 4: 11-20s
 * 5: 20-30s
 * 6: 30-50s
 * 7: 50-100s
 * 8: > 100s
 * 平均访问深度：(index="access_depth_info")
 * 1: 1页
 * 2: 2页
 * 3: 3页
 * 4: 4页
 * 5: 5页
 * 6: 6-10页
 * 7: >10页
 *
 * @author <a href="https://github.com/charmingoh">Charming</a>
 * @since 2018-04-28
 */
@Data
public class WxMaVisitDistribution implements Serializable {
  private static final long serialVersionUID = 5404250039495926632L;
  /**
   * 日期，yyyyMMdd 格式，如 20170313
   */
  @SerializedName(value = "refDate", alternate = "ref_date")
  private String refDate;
  /**
   * key: 分布类型
   * - access_source_session_cnt 访问来源分布
   * - access_staytime_info 访问时长分布
   * - access_depth_info 访问深度的分布
   * value: 场景 ID 下的值
   * - key: 场景 ID
   * - value: 场景下的值
   */
  private Map<String, Map<Integer, Integer>> list;

  public static WxMaVisitDistribution fromJson(String json) {
    return WxMaGsonBuilder.create().fromJson(json, WxMaVisitDistribution.class);
  }
}
