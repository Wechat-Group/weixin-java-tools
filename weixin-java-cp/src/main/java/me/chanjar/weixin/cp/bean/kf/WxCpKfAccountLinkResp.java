package me.chanjar.weixin.cp.bean.kf;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.cp.bean.WxCpBaseResp;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

/**
 * 获取客服帐号链接-结果
 *
 * @author Fu  created on  2022/1/19 19:18
 */
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Data
public class WxCpKfAccountLinkResp extends WxCpBaseResp {

  private static final long serialVersionUID = 910205439597092481L;

  /**
   * 客服链接，开发者可将该链接嵌入到H5页面中，用户点击链接即可向对应的微信客服帐号发起咨询。开发者也可根据该url自行生成需要的二维码图片
   */
  @SerializedName("url")
  private String url;

  /**
   * From json wx cp kf account link resp.
   *
   * @param json the json
   * @return the wx cp kf account link resp
   */
  public static WxCpKfAccountLinkResp fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpKfAccountLinkResp.class);
  }
}
