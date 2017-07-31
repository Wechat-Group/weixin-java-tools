package me.chanjar.weixin.mp.bean.store;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;
import me.chanjar.weixin.common.annotation.Required;
import me.chanjar.weixin.common.util.ToStringUtils;
import me.chanjar.weixin.mp.util.json.WxMpGsonBuilder;

import java.math.BigDecimal;
import java.util.List;

/**
 * 门店基础信息
 *
 * @author <a href="https://github.com/binarywang">binarywang(Binary Wang)</a>
 *         Created by Binary Wang on 2016-09-23.
 */
public class WxMpStoreBaseInfo {
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
  private Integer offsetType = 1;
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

  public static WxMpStoreBaseInfoBuilder builder() {
    return new WxMpStoreBaseInfoBuilder();
  }

  @Override
  public String toString() {
    return ToStringUtils.toSimpleString(this);
  }

  public String toJson() {
    JsonElement base_info = WxMpGsonBuilder.create().toJsonTree(this);
    JsonObject jsonObject = new JsonObject();
    jsonObject.add("base_info", base_info);
    JsonObject business = new JsonObject();
    business.add("business", jsonObject);
    return business.toString();
  }

  public String getSid() {
    return this.sid;
  }

  public void setSid(String sid) {
    this.sid = sid;
  }

  public String getBusinessName() {
    return this.businessName;
  }

  public void setBusinessName(String businessName) {
    this.businessName = businessName;
  }

  public String getBranchName() {
    return this.branchName;
  }

  public void setBranchName(String branchName) {
    this.branchName = branchName;
  }

  public String getProvince() {
    return this.province;
  }

  public void setProvince(String province) {
    this.province = province;
  }

  public String getCity() {
    return this.city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getDistrict() {
    return this.district;
  }

  public void setDistrict(String district) {
    this.district = district;
  }

  public String getAddress() {
    return this.address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getTelephone() {
    return this.telephone;
  }

  public void setTelephone(String telephone) {
    this.telephone = telephone;
  }

  public String[] getCategories() {
    return this.categories;
  }

  public void setCategories(String[] categories) {
    this.categories = categories;
  }

  public Integer getOffsetType() {
    return this.offsetType;
  }

  public void setOffsetType(Integer offsetType) {
    this.offsetType = offsetType;
  }

  public BigDecimal getLongitude() {
    return this.longitude;
  }

  public void setLongitude(BigDecimal longitude) {
    this.longitude = longitude;
  }

  public BigDecimal getLatitude() {
    return this.latitude;
  }

  public void setLatitude(BigDecimal latitude) {
    this.latitude = latitude;
  }

  public List<WxMpStorePhoto> getPhotos() {
    return this.photos;
  }

  public void setPhotos(List<WxMpStorePhoto> photos) {
    this.photos = photos;
  }

  public String getRecommend() {
    return this.recommend;
  }

  public void setRecommend(String recommend) {
    this.recommend = recommend;
  }

  public String getSpecial() {
    return this.special;
  }

  public void setSpecial(String special) {
    this.special = special;
  }

  public String getIntroduction() {
    return this.introduction;
  }

  public void setIntroduction(String introduction) {
    this.introduction = introduction;
  }

  public String getOpenTime() {
    return this.openTime;
  }

  public void setOpenTime(String openTime) {
    this.openTime = openTime;
  }

  public Integer getAvgPrice() {
    return this.avgPrice;
  }

  public void setAvgPrice(Integer avgPrice) {
    this.avgPrice = avgPrice;
  }

  public Integer getAvailableState() {
    return this.availableState;
  }

  public void setAvailableState(Integer availableState) {
    this.availableState = availableState;
  }

  public Integer getUpdateStatus() {
    return this.updateStatus;
  }

  public void setUpdateStatus(Integer updateStatus) {
    this.updateStatus = updateStatus;
  }

  public String getPoiId() {
    return this.poiId;
  }

  public void setPoiId(String poiId) {
    this.poiId = poiId;
  }

  public static class WxMpStorePhoto {
    /**
     * 照片url
     */
    @SerializedName("photo_url")
    private String photoUrl;
  }

  public static class WxMpStoreBaseInfoBuilder {
    private String sid;
    private String businessName;
    private String branchName;
    private String province;
    private String city;
    private String district;
    private String address;
    private String telephone;
    private String[] categories;
    private Integer offsetType;
    private BigDecimal longitude;
    private BigDecimal latitude;
    private List<WxMpStorePhoto> photos;
    private String recommend;
    private String special;
    private String introduction;
    private String openTime;
    private Integer avgPrice;
    private Integer availableState;
    private Integer updateStatus;
    private String poiId;

    public WxMpStoreBaseInfoBuilder sid(String sid) {
      this.sid = sid;
      return this;
    }

    public WxMpStoreBaseInfoBuilder businessName(String businessName) {
      this.businessName = businessName;
      return this;
    }

    public WxMpStoreBaseInfoBuilder branchName(String branchName) {
      this.branchName = branchName;
      return this;
    }

    public WxMpStoreBaseInfoBuilder province(String province) {
      this.province = province;
      return this;
    }

    public WxMpStoreBaseInfoBuilder city(String city) {
      this.city = city;
      return this;
    }

    public WxMpStoreBaseInfoBuilder district(String district) {
      this.district = district;
      return this;
    }

    public WxMpStoreBaseInfoBuilder address(String address) {
      this.address = address;
      return this;
    }

    public WxMpStoreBaseInfoBuilder telephone(String telephone) {
      this.telephone = telephone;
      return this;
    }

    public WxMpStoreBaseInfoBuilder categories(String[] categories) {
      this.categories = categories;
      return this;
    }

    public WxMpStoreBaseInfoBuilder offsetType(Integer offsetType) {
      this.offsetType = offsetType;
      return this;
    }

    public WxMpStoreBaseInfoBuilder longitude(BigDecimal longitude) {
      this.longitude = longitude;
      return this;
    }

    public WxMpStoreBaseInfoBuilder latitude(BigDecimal latitude) {
      this.latitude = latitude;
      return this;
    }

    public WxMpStoreBaseInfoBuilder photos(List<WxMpStorePhoto> photos) {
      this.photos = photos;
      return this;
    }

    public WxMpStoreBaseInfoBuilder recommend(String recommend) {
      this.recommend = recommend;
      return this;
    }

    public WxMpStoreBaseInfoBuilder special(String special) {
      this.special = special;
      return this;
    }

    public WxMpStoreBaseInfoBuilder introduction(String introduction) {
      this.introduction = introduction;
      return this;
    }

    public WxMpStoreBaseInfoBuilder openTime(String openTime) {
      this.openTime = openTime;
      return this;
    }

    public WxMpStoreBaseInfoBuilder avgPrice(Integer avgPrice) {
      this.avgPrice = avgPrice;
      return this;
    }

    public WxMpStoreBaseInfoBuilder availableState(Integer availableState) {
      this.availableState = availableState;
      return this;
    }

    public WxMpStoreBaseInfoBuilder updateStatus(Integer updateStatus) {
      this.updateStatus = updateStatus;
      return this;
    }

    public WxMpStoreBaseInfoBuilder poiId(String poiId) {
      this.poiId = poiId;
      return this;
    }

    public WxMpStoreBaseInfoBuilder from(WxMpStoreBaseInfo origin) {
      this.sid(origin.sid);
      this.businessName(origin.businessName);
      this.branchName(origin.branchName);
      this.province(origin.province);
      this.city(origin.city);
      this.district(origin.district);
      this.address(origin.address);
      this.telephone(origin.telephone);
      this.categories(origin.categories);
      this.offsetType(origin.offsetType);
      this.longitude(origin.longitude);
      this.latitude(origin.latitude);
      this.photos(origin.photos);
      this.recommend(origin.recommend);
      this.special(origin.special);
      this.introduction(origin.introduction);
      this.openTime(origin.openTime);
      this.avgPrice(origin.avgPrice);
      this.availableState(origin.availableState);
      this.updateStatus(origin.updateStatus);
      this.poiId(origin.poiId);
      return this;
    }

    public WxMpStoreBaseInfo build() {
      WxMpStoreBaseInfo m = new WxMpStoreBaseInfo();
      m.sid = this.sid;
      m.businessName = this.businessName;
      m.branchName = this.branchName;
      m.province = this.province;
      m.city = this.city;
      m.district = this.district;
      m.address = this.address;
      m.telephone = this.telephone;
      m.categories = this.categories;
      m.offsetType = this.offsetType;
      m.longitude = this.longitude;
      m.latitude = this.latitude;
      m.photos = this.photos;
      m.recommend = this.recommend;
      m.special = this.special;
      m.introduction = this.introduction;
      m.openTime = this.openTime;
      m.avgPrice = this.avgPrice;
      m.availableState = this.availableState;
      m.updateStatus = this.updateStatus;
      m.poiId = this.poiId;
      return m;
    }
  }

}
