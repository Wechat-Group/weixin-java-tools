package me.chanjar.weixin.qidian.bean.store;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Data;
import me.chanjar.weixin.common.annotation.Required;
import me.chanjar.weixin.qidian.util.json.WxMpGsonBuilder;

/**
 * <pre>
 *  门店基础信息
 *  Created by Binary Wang on 2016-09-23.
 * </pre>
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
@Data
@Builder
public class WxMpStoreBaseInfo implements Serializable {
  private static final long serialVersionUID = 829577606838118218L;

  /**
   * sid
   * 商户自己的id，用于后续审核通过收到poi_id 的通知时，做对应关系。请商户自己保证唯一识别性
   */
  @SerializedName("sid")
  private String sid;
  /**
   * business_name
   * 门店名称（仅为商户名，如：国美、麦当劳，不应包含地区、地址、分店名等信息，错误示例：北京国美）
   * 不能为空，15个汉字或30个英文字符内
   */
  @Required
  @SerializedName("business_name")
  private String businessName;
  /**
   * branch_name
   * 分店名称（不应包含地区信息，不应与门店名有重复，错误示例：北京王府井店）
   * 10个字以内
   */
  @Required
  @SerializedName("branch_name")
  private String branchName;
  /**
   * province
   * 门店所在的省份（直辖市填城市名,如：北京市）
   * 10个字以内
   */
  @Required
  @SerializedName("province")
  private String province;
  /**
   * city
   * 门店所在的城市
   * 10个字以内
   */
  @Required
  @SerializedName("city")
  private String city;
  /**
   * district
   * 门店所在地区
   * 10个字以内
   */
  @Required
  @SerializedName("district")
  private String district;
  /**
   * address
   * 门店所在的详细街道地址（不要填写省市信息）
   * （东莞等没有“区”行政区划的城市，该字段可不必填写。其余城市必填。）
   */
  @Required
  @SerializedName("address")
  private String address;
  /**
   * telephone
   * 门店的电话（纯数字，区号、分机号均由“-”隔开）
   */
  @Required
  @SerializedName("telephone")
  private String telephone;
  /**
   * categories
   * 门店的类型（不同级分类用“,”隔开，如：美食，川菜，火锅。详细分类参见附件：微信门店类目表）
   */
  @Required
  @SerializedName("categories")
  private String[] categories;
  /**
   * offsetType
   * 坐标类型，1 为火星坐标（目前只能选1）
   */
  @Required
  @SerializedName("offset_type")
  @Builder.Default
  private final Integer offsetType = 1;
  /**
   * longitude
   * 门店所在地理位置的经度
   */
  @Required
  @SerializedName("longitude")
  private BigDecimal longitude;
  /**
   * latitude
   * 门店所在地理位置的纬度（经纬度均为火星坐标，最好选用腾讯地图标记的坐标）
   */
  @Required
  @SerializedName("latitude")
  private BigDecimal latitude;
  /**
   * photo_list
   * 图片列表，url 形式，可以有多张图片，尺寸为 640*340px。必须为上一接口生成的url。
   * 图片内容不允许与门店不相关，不允许为二维码、员工合照（或模特肖像）、营业执照、无门店正门的街景、地图截图、公交地铁站牌、菜单截图等
   */
  @SerializedName("photo_list")
  private List<WxMpStorePhoto> photos;
  /**
   * recommend
   * 推荐品，餐厅可为推荐菜；酒店为推荐套房；景点为推荐游玩景点等，针对自己行业的推荐内容
   * 200字以内
   */
  @SerializedName("recommend")
  private String recommend;
  /**
   * special
   * 特色服务，如免费wifi，免费停车，送货上门等商户能提供的特色功能或服务
   */
  @SerializedName("special")
  private String special;
  /**
   * introduction
   * 商户简介，主要介绍商户信息等
   * 300字以内
   */
  @SerializedName("introduction")
  private String introduction;
  /**
   * open_time
   * 营业时间，24 小时制表示，用“-”连接，如  8:00-20:00
   */
  @SerializedName("open_time")
  private String openTime;
  /**
   * avg_price
   * 人均价格，大于0 的整数
   */
  @SerializedName("avg_price")
  private Integer avgPrice;
  /**
   * 门店是否可用状态。1 表示系统错误、2 表示审核中、3 审核通过、4 审核驳回。当该字段为1、2、4 状态时，poi_id 为空
   */
  @SerializedName("available_state")
  private Integer availableState;
  /**
   * 扩展字段是否正在更新中。1 表示扩展字段正在更新中，尚未生效，不允许再次更新； 0 表示扩展字段没有在更新中或更新已生效，可以再次更新
   */
  @SerializedName("update_status")
  private Integer updateStatus;
  /**
   * 门店poi id
   */
  @SerializedName("poi_id")
  private String poiId;

  public static WxMpStoreBaseInfo fromJson(String json) {
    return WxMpGsonBuilder.create().fromJson(json, WxMpStoreBaseInfo.class);
  }

  @Override
  public String toString() {
    return this.toJson();
  }

  public String toJson() {
    JsonElement baseInfo = WxMpGsonBuilder.create().toJsonTree(this);
    JsonObject jsonObject = new JsonObject();
    jsonObject.add("base_info", baseInfo);
    JsonObject business = new JsonObject();
    business.add("business", jsonObject);
    return business.toString();
  }

  @Data
  public static class WxMpStorePhoto implements Serializable {
    private static final long serialVersionUID = -2942447907614186861L;
    /**
     * 照片url.
     */
    @SerializedName("photo_url")
    private String photoUrl;

  }

}
