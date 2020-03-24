package cn.binarywang.wx.miniapp.bean.code;

import cn.binarywang.wx.miniapp.util.json.WxMaGsonBuilder;
import lombok.Data;

import java.util.Map;

/**
 * 小程序代码版本号分布
 *
 * @author <a href="https://github.com/charmingoh">Charming</a>
 * @since 2018-04-26 19:45
 */
@Data
public class WxMaCodeVersionDistribution {
  /**
   * 当前版本
   */
  private String nowVersion;
  /**
   * 受影响用户占比
   * key: version, 版本号
   * value: percentage, 受影响比例
   */
  private Map<String, Float> uvInfo;

  public static WxMaCodeVersionDistribution fromJson(String json) {
    return WxMaGsonBuilder.create().fromJson(json, WxMaCodeVersionDistribution.class);
  }
}
