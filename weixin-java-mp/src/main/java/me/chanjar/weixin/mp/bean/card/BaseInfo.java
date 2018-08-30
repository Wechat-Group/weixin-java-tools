package me.chanjar.weixin.mp.bean.card;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
//子对象列表

/**
 * 微信会员卡基本信息
 * author:yuanqixun
 * date:2018-08-25 00:36
 */
@Data
public class BaseInfo implements Serializable {

  /**
   * 卡券的商户logo,建议像素为300*300。
   */
  @SerializedName("logo_url")
  private String logoUrl;

  /**
   * Code展示类型,"CODE_TYPE_TEXT" 文本 "CODE_TYPE_BARCODE" 一维码 "CODE_TYPE_QRCODE" 二维码 "CODE_TYPE_ONLY_QRCODE" 仅显示二维码 "CODE_TYPE_ONLY_BARCODE" 仅显示一维码 "CODE_TYPE_NONE" 不显示任何码型
   */
  @SerializedName("code_type")
  private String codeType = "CODE_TYPE_QRCODE";

  /**
   * 支付功能结构体,swipe_card结构
   */
  @SerializedName("pay_info")
  private PayInfo payInfo;

  /**
   * 商户名字,字数上限为12个汉字
   */
  @SerializedName("brand_name")
  private String brandName;

  /**
   * 卡券名,字数上限为9个汉字 (建议涵盖卡券属性、服务及金额)。
   */
  @SerializedName("title")
  private String title;

  /**
   * 券颜色,按色彩规范标注填写Color010-Color100
   */
  @SerializedName("color")
  private String color;

  /**
   * 卡券使用提醒,字数上限为16个汉字
   */
  @SerializedName("notice")
  private String notice;

  /**
   * 卡券使用说明,字数上限为1024个汉字。
   */
  @SerializedName("description")
  private String description;

  /**
   * 商品信息
   */
  @SerializedName("sku")
  private Sku sku;

  /**
   * 使用日期,有效期的信息。
   */
  @SerializedName("date_info")
  private DateInfo dateInfo;

  /**
   * 是否自定义Code码,填写true或false，默认为false 通常自有优惠码系统的开发者选择自定义Code码，详情见 是否自定义code
   */
  @SerializedName("use_custom_code")
  private boolean useCustomCode;

  /**
   * 是否指定用户领取,填写true或false。默认为false
   */
  @SerializedName("bind_openid")
  private boolean bindOpenid;

  /**
   * 客服电话
   */
  @SerializedName("service_phone")
  private String servicePhone;

  /**
   * 门店位置ID,调用 POI门店管理接口 获取门店位置ID。
   */
  @SerializedName("location_id_list")
  private String locationIdList;

  /**
   * 会员卡是否支持全部门店,填写后商户门店更新时会自动同步至卡券
   */
  @SerializedName("use_all_locations")
  private boolean useAllLocations = true;

  /**
   * 卡券中部居中的按钮,仅在卡券激活后且可用状态 时显示
   */
  @SerializedName("center_title")
  private String centerTitle;

  /**
   * 显示在入口下方的提示语,仅在卡券激活后且可用状态时显示
   */
  @SerializedName("center_sub_title")
  private String centerSubTitle;

  /**
   * 顶部居中的url,仅在卡券激活后且可用状态时显示
   */
  @SerializedName("center_url")
  private String centerUrl;

  /**
   * 自定义跳转外链的入口名字
   */
  @SerializedName("custom_url_name")
  private String customUrlName;

  /**
   * 自定义跳转的URL
   */
  @SerializedName("custom_url")
  private String customUrl;

  /**
   * 显示在入口右侧的提示语
   */
  @SerializedName("custom_url_sub_title")
  private String customUrlSubTitle;

  /**
   * 营销场景的自定义入口名称
   */
  @SerializedName("promotion_url_name")
  private String promotionUrlName;

  /**
   * 入口跳转外链的地址链接
   */
  @SerializedName("promotion_url")
  private String promotionUrl;

  /**
   * 显示在营销入口右侧的提示语
   */
  @SerializedName("promotion_url_sub_title")
  private String promotionUrlSubTitle;

  /**
   * 每人可领券的数量限制,建议会员卡每人限领一张
   */
  @SerializedName("get_limit")
  private Integer getLimit = 1;

  /**
   * 卡券领取页面是否可分享,默认为true
   */
  @SerializedName("can_share")
  private boolean canShare;

  /**
   * 卡券是否可转赠,默认为true
   */
  @SerializedName("can_give_friend")
  private boolean canGiveFriend;

  /**
   * 用户点击进入会员卡时推送事件,填写true为用户点击进入会员卡时推送事件，默认为false。详情见 进入会员卡事件推送
   */
  @SerializedName("need_push_on_view")
  private boolean needPushOnView;

  public String toString() {
    return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
  }
}
