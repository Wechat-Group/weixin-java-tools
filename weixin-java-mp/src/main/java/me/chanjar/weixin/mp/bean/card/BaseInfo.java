package me.chanjar.weixin.mp.bean.card;

import java.io.Serializable;
import java.util.List;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import me.chanjar.weixin.mp.util.json.WxMpGsonBuilder;

/**
 * 微信会员卡基本信息.
 * @author yuanqixun
 * date:2018-08-25 00:36
 */
@Data
public class BaseInfo implements Serializable {
  private static final long serialVersionUID = 4753535126193166020L;

  /**
   * 卡券的商户logo,建议像素为300*300.
   */
  @SerializedName("logo_url")
  private String logoUrl;

  /**
   * Code展示类型.
   * "CODE_TYPE_TEXT" 文本 "CODE_TYPE_BARCODE" 一维码 "CODE_TYPE_QRCODE" 二维码 "CODE_TYPE_ONLY_QRCODE" 仅显示二维码 "CODE_TYPE_ONLY_BARCODE" 仅显示一维码 "CODE_TYPE_NONE" 不显示任何码型
   */
  @SerializedName("code_type")
  private String codeType = "CODE_TYPE_QRCODE";

  /**
   * 支付功能结构体,swipe_card结构.
   */
  @SerializedName("pay_info")
  private PayInfo payInfo;

  /**
   * 是否设置该会员卡中部的按钮同时支持微信支付刷卡和会员卡二维码.
   */
  @SerializedName("is_pay_and_qrcode")
  private boolean isPayAndQrcode;

  /**
   * 商户名字,字数上限为12个汉字.
   */
  @SerializedName("brand_name")
  private String brandName;

  /**
   * 卡券名,字数上限为9个汉字 (建议涵盖卡券属性、服务及金额).
   */
  @SerializedName("title")
  private String title;

  /**
   * 券颜色,按色彩规范标注填写Color010-Color100.
   */
  @SerializedName("color")
  private String color;

  /**
   * 卡券使用提醒,字数上限为16个汉字.
   */
  @SerializedName("notice")
  private String notice;

  /**
   * 卡券使用说明,字数上限为1024个汉字.
   */
  @SerializedName("description")
  private String description;

  /**
   * 商品信息.
   */
  @SerializedName("sku")
  private Sku sku;

  /**
   * 使用日期,有效期的信息.
   */
  @SerializedName("date_info")
  private DateInfo dateInfo;

  /**
   * 是否自定义Code码,填写true或false.
   * 默认为false 通常自有优惠码系统的开发者选择自定义Code码，详情见 是否自定义code
   */
  @SerializedName("use_custom_code")
  private boolean useCustomCode;

  /**
   * 是否指定用户领取,填写true或false。默认为false.
   */
  @SerializedName("bind_openid")
  private boolean bindOpenid;

  /**
   * 客服电话.
   */
  @SerializedName("service_phone")
  private String servicePhone;

  /**
   * 门店位置ID,调用 POI门店管理接口 获取门店位置ID.
   */
  @SerializedName("location_id_list")
  private List<String> locationIdList;

  /**
   * 会员卡是否支持全部门店,填写后商户门店更新时会自动同步至卡券.
   */
  @SerializedName("use_all_locations")
  private boolean useAllLocations = true;

  /**
   * 卡券中部居中的按钮,仅在卡券激活后且可用状态 时显示.
   */
  @SerializedName("center_title")
  private String centerTitle;

  /**
   * 显示在入口下方的提示语,仅在卡券激活后且可用状态时显示.
   */
  @SerializedName("center_sub_title")
  private String centerSubTitle;

  /**
   * 顶部居中的url,仅在卡券激活后且可用状态时显示.
   */
  @SerializedName("center_url")
  private String centerUrl;

  /**
   * 自定义跳转外链的入口名字.
   */
  @SerializedName("custom_url_name")
  private String customUrlName;

  /**
   * 自定义跳转的URL.
   */
  @SerializedName("custom_url")
  private String customUrl;

  /**
   * 显示在入口右侧的提示语.
   */
  @SerializedName("custom_url_sub_title")
  private String customUrlSubTitle;

  /**
   * 营销场景的自定义入口名称.
   */
  @SerializedName("promotion_url_name")
  private String promotionUrlName;

  /**
   * 入口跳转外链的地址链接.
   */
  @SerializedName("promotion_url")
  private String promotionUrl;

  /**
   * 显示在营销入口右侧的提示语.
   */
  @SerializedName("promotion_url_sub_title")
  private String promotionUrlSubTitle;

  /**
   * 每人可领券的数量限制,建议会员卡每人限领一张.
   */
  @SerializedName("get_limit")
  private Integer getLimit = 1;

  /**
   * 每人可核销的数量限制,不填写默认为50.
   */
  @SerializedName("use_limit")
  private Integer useLimit = 50;

  /**
   * 卡券领取页面是否可分享,默认为true.
   */
  @SerializedName("can_share")
  private boolean canShare;

  /**
   * 卡券是否可转赠,默认为true.
   */
  @SerializedName("can_give_friend")
  private boolean canGiveFriend;

  /**
   * 用户点击进入会员卡时推送事件.
   * 填写true为用户点击进入会员卡时推送事件，默认为false。详情见 进入会员卡事件推送
   */
  @SerializedName("need_push_on_view")
  private boolean needPushOnView;

  /**
   * 微信小程序开放功能 小程序&卡券打通部分新增8个字段 https://mp.weixin.qq.com/cgi-bin/announce?action=getannouncement&key=1490190158&version=1&lang=zh_CN&platform=2
   * 自定义使用入口跳转小程序的user_name，格式为原始id+@app
   */
  @SerializedName("custom_app_brand_user_name")
  private String customAppBrandUserName;
  /**
   * 自定义使用入口小程序页面地址
   */
  @SerializedName("custom_app_brand_pass")
  private String customAppBrandPass;
  /**
   * 小程序的user_name
   */
  @SerializedName("center_app_brand_user_name")
  private String centerAppBrandUserName;
  /**
   * 自定义居中使用入口小程序页面地址
   */
  @SerializedName("center_app_brand_pass")
  private String centerAppBrandPass;
  /**
   * 小程序的user_name
   */
  @SerializedName("promotion_app_brand_user_name")
  private String promotionAppBrandUserName;
  /**
   * 自定义营销入口小程序页面地址
   */
  @SerializedName("promotion_app_brand_pass")
  private String promotionAppBrandPass;

  /**
   * 小程序的user_name,
   */
  @SerializedName("activate_app_brand_user_name")
  private String activateAppBrandUserName;
  /**
   * 激活小程序页面地址
   */
  @SerializedName("activate_app_brand_pass")
  private String activateAppBrandPass;

  /**
   *
   * https://developers.weixin.qq.com/doc/offiaccount/Cards_and_Offer/Managing_Coupons_Vouchers_and_Cards.html#2
   * “CARD_STATUS_NOT_VERIFY”,待审核 ；
   * “CARD_STATUS_VERIFY_FAIL”,审核失败；
   * “CARD_STATUS_VERIFY_OK”，通过审核；
   * “CARD_STATUS_DELETE”，卡券被商户删除；
   * “CARD_STATUS_DISPATCH”，在公众平台投放过的卡券 ；
   */
  private String status;

  @Override
  public String toString() {
    return WxMpGsonBuilder.create().toJson(this);
  }
}
