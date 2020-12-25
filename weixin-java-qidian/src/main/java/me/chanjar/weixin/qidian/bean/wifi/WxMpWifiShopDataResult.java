package me.chanjar.weixin.qidian.bean.wifi;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import me.chanjar.weixin.common.util.json.GsonParser;
import me.chanjar.weixin.qidian.util.json.WxMpGsonBuilder;

import java.util.List;

/**
 * 门店Wi-Fi信息.
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 * @date 2019-06-16
 */
@Data
public class WxMpWifiShopDataResult {
  public static WxMpWifiShopDataResult fromJson(String json) {
    return WxMpGsonBuilder.create().fromJson(
      GsonParser.parse(json).get("data"),
      WxMpWifiShopDataResult.class);
  }

  /**
   * 门店名称.
   */
  @SerializedName("shop_name")
  private String shopName;

  /**
   * 无线网络设备的ssid，未添加设备为空，多个ssid时显示第一个.
   */
  @SerializedName("ssid")
  private String ssid;

  /**
   * 无线网络设备的ssid列表，返回数组格式.
   */
  @SerializedName("ssid_list")
  private String[] ssidList;

  /**
   * ssid和密码的列表，数组格式。当为密码型设备时，密码才有值.
   */
  @SerializedName("ssid_password_list")
  private List<SsidPassword> ssidPasswordList;

  /**
   * 设备密码，当设备类型为密码型时返回.
   */
  @SerializedName("password")
  private String password;

  /**
   * 门店内设备的设备类型，0-未添加设备，4-密码型设备，31-portal型设备.
   */
  @SerializedName("protocol_type")
  private Integer protocolType;

  /**
   * 门店内设备总数.
   */
  @SerializedName("ap_count")
  private Integer apCount;

  /**
   * 商家主页模板类型.
   */
  @SerializedName("template_id")
  private Integer templateId;

  /**
   * 商家主页链接.
   */
  @SerializedName("homepage_url")
  private String homepageUrl;

  /**
   * 顶部常驻入口上显示的文本内容：0--欢迎光临+公众号名称；1--欢迎光临+门店名称；2--已连接+公众号名称+WiFi；3--已连接+门店名称+Wi-Fi.
   */
  @SerializedName("bar_type")
  private Integer barType;

  /**
   * 连网完成页链接.
   */
  @SerializedName("finishpage_url")
  private String finishPageUrl;

  /**
   * 商户自己的id，与门店poi_id对应关系，建议在添加门店时候建立关联关系，具体请参考“微信门店接口”.
   */
  @SerializedName("sid")
  private String sid;

  /**
   * 门店ID（适用于微信卡券、微信门店业务），具体定义参考微信门店，与shop_id一一对应.
   */
  @SerializedName("poi_id")
  private String poiId;

  @Data
  public static class SsidPassword {
    /**
     * 无线网络设备的ssid.
     */
    private String ssid;

    /**
     * 无线网络设备的password.
     */
    private String password;
  }
}
