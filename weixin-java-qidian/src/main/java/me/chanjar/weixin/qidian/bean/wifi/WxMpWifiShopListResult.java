package me.chanjar.weixin.qidian.bean.wifi;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import me.chanjar.weixin.common.util.json.GsonParser;
import me.chanjar.weixin.qidian.util.json.WxMpGsonBuilder;

import java.util.List;

/**
 * <pre>
 *  Created by BinaryWang on 2018/6/10.
 * </pre>
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
@Data
public class WxMpWifiShopListResult {
  public static WxMpWifiShopListResult fromJson(String json) {
    return WxMpGsonBuilder.create().fromJson(
      GsonParser.parse(json).get("data"),
      WxMpWifiShopListResult.class);
  }

  /**
   * 总数.
   */
  @SerializedName("totalcount")
  private int totalCount;

  /**
   * 分页下标.
   */
  @SerializedName("pageindex")
  private int pageIndex;

  /**
   * 分页页数.
   */
  @SerializedName("pagecount")
  private int pageCount;

  private List<Record> records;

  @Data
  public static class Record {

    /**
     * 门店ID（适用于微信连Wi-Fi业务）.
     */
    @SerializedName("shop_id")
    private Integer shopId;

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
    private List<String> ssidList;

    /**
     * 门店内设备的设备类型.
     * 0-未添加设备，1-专业型设备，4-密码型设备，5-portal自助型设备，31-portal改造型设备
     */
    @SerializedName("protocol_type")
    private Integer protocolType;

    /**
     * 商户自己的id.
     * 与门店poi_id对应关系，建议在添加门店时候建立关联关系，具体请参考“微信门店接口”
     */
    @SerializedName("sid")
    private String sid;

    /**
     * 门店ID（适用于微信卡券、微信门店业务）.
     * 具体定义参考微信门店，与shop_id一一对应
     */
    @SerializedName("poi_id")
    private String poiId;
  }
}
