package me.chanjar.weixin.mp.bean.message;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import org.apache.commons.io.IOUtils;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.util.XmlUtils;
import me.chanjar.weixin.common.util.xml.XStreamCDataConverter;
import me.chanjar.weixin.mp.api.WxMpConfigStorage;
import me.chanjar.weixin.mp.util.crypto.WxMpCryptUtil;
import me.chanjar.weixin.mp.util.json.WxMpGsonBuilder;
import me.chanjar.weixin.mp.util.xml.XStreamTransformer;

/**
 * <pre>
 * 微信推送过来的消息，xml格式.
 * 部分未注释的字段的解释请查阅相关微信开发文档：
 * <a href="http://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421140453&token=&lang=zh_CN">接收普通消息</a>
 * <a href="http://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421140454&token=&lang=zh_CN">接收事件推送</a>
 * </pre>
 *
 * @author chanjarster
 */
@Data
@Slf4j
@XStreamAlias("xml")
public class WxMpXmlMessage implements Serializable {
  private static final long serialVersionUID = -3586245291677274914L;

  /**
   * 使用dom4j解析的存放所有xml属性和值的map.
   */
  private Map<String, Object> allFieldsMap;

  ///////////////////////
  // 以下都是微信推送过来的消息的xml的element所对应的属性
  ///////////////////////

  @XStreamAlias("ToUserName")
  @XStreamConverter(value = XStreamCDataConverter.class)
  private String toUser;

  @XStreamAlias("FromUserName")
  @XStreamConverter(value = XStreamCDataConverter.class)
  private String fromUser;

  @XStreamAlias("CreateTime")
  private Long createTime;

  @XStreamAlias("MsgType")
  @XStreamConverter(value = XStreamCDataConverter.class)
  private String msgType;

  @XStreamAlias("Content")
  @XStreamConverter(value = XStreamCDataConverter.class)
  private String content;

  @XStreamAlias("MenuId")
  private Long menuId;

  @XStreamAlias("MsgId")
  private Long msgId;

  @XStreamAlias("PicUrl")
  @XStreamConverter(value = XStreamCDataConverter.class)
  private String picUrl;

  @XStreamAlias("MediaId")
  @XStreamConverter(value = XStreamCDataConverter.class)
  private String mediaId;

  @XStreamAlias("Format")
  @XStreamConverter(value = XStreamCDataConverter.class)
  private String format;

  @XStreamAlias("ThumbMediaId")
  @XStreamConverter(value = XStreamCDataConverter.class)
  private String thumbMediaId;

  @XStreamAlias("Location_X")
  private Double locationX;

  @XStreamAlias("Location_Y")
  private Double locationY;

  @XStreamAlias("Scale")
  private Double scale;

  @XStreamAlias("Label")
  @XStreamConverter(value = XStreamCDataConverter.class)
  private String label;

  @XStreamAlias("Title")
  @XStreamConverter(value = XStreamCDataConverter.class)
  private String title;

  @XStreamAlias("Description")
  @XStreamConverter(value = XStreamCDataConverter.class)
  private String description;

  @XStreamAlias("Url")
  @XStreamConverter(value = XStreamCDataConverter.class)
  private String url;

  @XStreamAlias("Event")
  @XStreamConverter(value = XStreamCDataConverter.class)
  private String event;

  @XStreamAlias("EventKey")
  @XStreamConverter(value = XStreamCDataConverter.class)
  private String eventKey;

  @XStreamAlias("Ticket")
  @XStreamConverter(value = XStreamCDataConverter.class)
  private String ticket;

  @XStreamAlias("Latitude")
  private Double latitude;

  @XStreamAlias("Longitude")
  private Double longitude;

  @XStreamAlias("Precision")
  private Double precision;

  @XStreamAlias("Recognition")
  @XStreamConverter(value = XStreamCDataConverter.class)
  private String recognition;

  @XStreamAlias("UnionId")
  @XStreamConverter(value = XStreamCDataConverter.class)
  private String unionId;

  ///////////////////////////////////////
  // 群发消息返回的结果
  ///////////////////////////////////////
  /**
   * 群发的结果.
   */
  @XStreamAlias("Status")
  @XStreamConverter(value = XStreamCDataConverter.class)
  private String status;
  /**
   * group_id下粉丝数；或者openid_list中的粉丝数.
   */
  @XStreamAlias("TotalCount")
  private Integer totalCount;
  /**
   * 过滤（过滤是指特定地区、性别的过滤、用户设置拒收的过滤，用户接收已超4条的过滤）后，准备发送的粉丝数.
   * 原则上，filterCount = sentCount + errorCount
   */
  @XStreamAlias("FilterCount")
  private Integer filterCount;
  /**
   * 发送成功的粉丝数.
   */
  @XStreamAlias("SentCount")
  private Integer sentCount;
  /**
   * 发送失败的粉丝数.
   */
  @XStreamAlias("ErrorCount")
  private Integer errorCount;

  ///////////////////////////////////////
  // 客服会话管理相关事件推送
  ///////////////////////////////////////
  /**
   * 创建或关闭客服会话时的客服帐号.
   */
  @XStreamAlias("KfAccount")
  private String kfAccount;
  /**
   * 转接客服会话时的转入客服帐号.
   */
  @XStreamAlias("ToKfAccount")
  private String toKfAccount;
  /**
   * 转接客服会话时的转出客服帐号.
   */
  @XStreamAlias("FromKfAccount")
  private String fromKfAccount;

  ///////////////////////////////////////
  // 卡券相关事件推送
  ///////////////////////////////////////

  @XStreamAlias("CardId")
  @XStreamConverter(value = XStreamCDataConverter.class)
  private String cardId;

  @XStreamAlias("FriendUserName")
  @XStreamConverter(value = XStreamCDataConverter.class)
  private String friendUserName;

  /**
   * 是否为转赠，1代表是，0代表否.
   */
  @XStreamAlias("IsGiveByFriend")
  private Integer isGiveByFriend;

  @XStreamAlias("UserCardCode")
  @XStreamConverter(value = XStreamCDataConverter.class)
  private String userCardCode;

  @XStreamAlias("OldUserCardCode")
  @XStreamConverter(value = XStreamCDataConverter.class)
  private String oldUserCardCode;

  @XStreamAlias("OuterId")
  private Integer outerId;

  /**
   * 用户删除会员卡后可重新找回，当用户本次操作为找回时，该值为1，否则为0.
   */
  @XStreamAlias("IsRestoreMemberCard")
  private String isRestoreMemberCard;

  /**
   * <pre>
   * 领取场景值，用于领取渠道数据统计。可在生成二维码接口及添加Addcard接口中自定义该字段的字符串值.
   * 核销卡券时：开发者发起核销时传入的自定义参数，用于进行核销渠道统计
   * 另外：
   * 官网文档中，微信卡券>>卡券事件推送>>2.7 进入会员卡事件推送 user_view_card
   * OuterStr：商户自定义二维码渠道参数，用于标识本次扫码打开会员卡来源来自于某个渠道值的二维码
   * </pre>
   */
  @XStreamAlias("OuterStr")
  private String outerStr;

  /**
   * 是否转赠退回，0代表不是，1代表是.
   */
  @XStreamAlias("IsReturnBack")
  private String isReturnBack;

  /**
   * 是否是群转赠，0代表不是，1代表是.
   */
  @XStreamAlias("IsChatRoom")
  private String isChatRoom;

  /**
   * 核销来源.
   * 支持开发者统计API核销（FROM_API）、公众平台核销（FROM_MP）、卡券商户助手核销（FROM_MOBILE_HELPER）（核销员微信号）
   */
  @XStreamAlias("ConsumeSource")
  private String consumeSource;

  /**
   * 门店名称.
   * 当前卡券核销的门店名称（只有通过自助核销和买单核销时才会出现该字段）
   */
  @XStreamAlias("LocationName")
  private String locationName;

  /**
   * 核销该卡券核销员的openid（只有通过卡券商户助手核销时才会出现）.
   */
  @XStreamAlias("StaffOpenId")
  private String staffOpenId;

  /**
   * 自助核销时，用户输入的验证码.
   */
  @XStreamAlias("VerifyCode")
  private String verifyCode;

  /**
   * 自助核销时，用户输入的备注金额.
   */
  @XStreamAlias("RemarkAmount")
  private String remarkAmount;

  /**
   * <pre>
   * 官网文档中，微信卡券>>卡券事件推送>>2.10 库存报警事件card_sku_remind
   * Detail：报警详细信息.
   * </pre>
   */
  @XStreamAlias("Detail")
  private String detail;

  /**
   * <pre>
   * 官网文档中，微信卡券>>卡券事件推送>>2.9 会员卡内容更新事件 update_member_card
   * ModifyBonus：变动的积分值.
   * </pre>
   */
  @XStreamAlias("ModifyBonus")
  private String modifyBonus;

  /**
   * <pre>
   * 官网文档中，微信卡券>>卡券事件推送>>2.9 会员卡内容更新事件 update_member_card
   * ModifyBalance：变动的余额值.
   * </pre>
   */
  @XStreamAlias("ModifyBalance")
  private String modifyBalance;

  /**
   * <pre>
   * 官网文档中，微信卡券>>卡券事件推送>>2.6 买单事件推送 User_pay_from_pay_cell
   * TransId：微信支付交易订单号（只有使用买单功能核销的卡券才会出现）.
   * </pre>
   */
  @XStreamAlias("TransId")
  private String transId;

  /**
   * <pre>
   * 官网文档中，微信卡券>>卡券事件推送>>2.6 买单事件推送 User_pay_from_pay_cell
   * LocationId：门店ID，当前卡券核销的门店ID（只有通过卡券商户助手和买单核销时才会出现）
   * </pre>
   */
  @XStreamAlias("LocationId")
  private String locationId;

  /**
   * <pre>
   * 官网文档中，微信卡券>>卡券事件推送>>2.6 买单事件推送 User_pay_from_pay_cell
   * Fee：实付金额，单位为分
   * </pre>
   */
  @XStreamAlias("Fee")
  private String fee;

  /**
   * <pre>
   * 官网文档中，微信卡券>>卡券事件推送>>2.6 买单事件推送 User_pay_from_pay_cell
   * OriginalFee：应付金额，单位为分
   * </pre>
   */
  @XStreamAlias("OriginalFee")
  private String originalFee;

  @XStreamAlias("ScanCodeInfo")
  private ScanCodeInfo scanCodeInfo = new ScanCodeInfo();

  @XStreamAlias("SendPicsInfo")
  private SendPicsInfo sendPicsInfo = new SendPicsInfo();

  @XStreamAlias("SendLocationInfo")
  private SendLocationInfo sendLocationInfo = new SendLocationInfo();

  /**
   * 审核不通过原因
   */
  @XStreamAlias("RefuseReason")
  private String refuseReason;

  /**
   * 是否为朋友推荐，0代表否，1代表是
   */
  @XStreamAlias("IsRecommendByFriend")
  private String isRecommendByFriend;

  /**
   * 购买券点时，实际支付成功的时间
   */
  @XStreamAlias("PayFinishTime")
  private String payFinishTime;

  /**
   * 购买券点时，支付二维码的生成时间
   */
  @XStreamAlias("CreateOrderTime")
  private String createOrderTime;

  /**
   * 购买券点时，支付二维码的生成时间
   */
  @XStreamAlias("Desc")
  private String desc;

  /**
   * 剩余免费券点数量
   */
  @XStreamAlias("FreeCoinCount")
  private String freeCoinCount;

  /**
   * 剩余付费券点数量
   */
  @XStreamAlias("PayCoinCount")
  private String payCoinCount;

  /**
   * 本次变动的免费券点数量
   */
  @XStreamAlias("RefundFreeCoinCount")
  private String refundFreeCoinCount;

  /**
   * 本次变动的付费券点数量
   */
  @XStreamAlias("RefundPayCoinCount")
  private String refundPayCoinCount;

  /**
   * <pre>
   *    所要拉取的订单类型 ORDER_TYPE_SYS_ADD 平台赠送券点 ORDER_TYPE_WXPAY 充值券点 ORDER_TYPE_REFUND 库存未使用回退券点
   *    ORDER_TYPE_REDUCE 券点兑换库存 ORDER_TYPE_SYS_REDUCE 平台扣减
   * </pre>
   */
  @XStreamAlias("OrderType")
  private String orderType;

  /**
   * 系统备注，说明此次变动的缘由，如开通账户奖励、门店奖励、核销奖励以及充值、扣减。
   */
  @XStreamAlias("Memo")
  private String memo;

  /**
   * 所开发票的详情
   */
  @XStreamAlias("ReceiptInfo")
  private String receiptInfo;


  ///////////////////////////////////////
  // 门店审核事件推送
  ///////////////////////////////////////
  /**
   * 商户自己内部ID，即字段中的sid.
   */
  @XStreamAlias("UniqId")
  private String storeUniqId;

  /**
   * 微信的门店ID，微信内门店唯一标示ID.
   */
  @XStreamAlias("PoiId")
  private String poiId;

  /**
   * 审核结果，成功succ 或失败fail.
   *
   * 在商品审核结果推送时，verify_ok表示审核通过，verify_not_pass表示审核未通过。
   */
  @XStreamAlias("Result")
  private String result;

  /**
   * 成功的通知信息，或审核失败的驳回理由.
   */
  @XStreamAlias("msg")
  private String msg;

  ///////////////////////////////////////
  // 微信认证事件推送
  ///////////////////////////////////////
  /**
   * 资质认证成功/名称认证成功: 有效期 (整形)，指的是时间戳，将于该时间戳认证过期.
   * 年审通知: 有效期 (整形)，指的是时间戳，将于该时间戳认证过期，需尽快年审
   * 认证过期失效通知: 有效期 (整形)，指的是时间戳，表示已于该时间戳认证过期，需要重新发起微信认证
   */
  @XStreamAlias("ExpiredTime")
  private Long expiredTime;
  /**
   * 失败发生时间 (整形)，时间戳.
   */
  @XStreamAlias("FailTime")
  private Long failTime;
  /**
   * 认证失败的原因.
   */
  @XStreamAlias("FailReason")
  private String failReason;

  ///////////////////////////////////////
  // 微信小店 6.1订单付款通知
  ///////////////////////////////////////
  /**
   * 订单ID.
   */
  @XStreamAlias("OrderId")
  @XStreamConverter(value = XStreamCDataConverter.class)
  private String orderId;

  /**
   * 订单状态.
   */
  @XStreamAlias("OrderStatus")
  private String orderStatus;

  /**
   * 商品ID.
   */
  @XStreamAlias("ProductId")
  @XStreamConverter(value = XStreamCDataConverter.class)
  private String productId;

  /**
   * 商品SKU信息.
   */
  @XStreamAlias("SkuInfo")
  @XStreamConverter(value = XStreamCDataConverter.class)
  private String skuInfo;

  ///////////////////////////////////////
  // 微信硬件平台相关事件推送
  ///////////////////////////////////////
  /**
   * 设备类型.
   * 目前为"公众账号原始ID"
   */
  @XStreamAlias("DeviceType")
  @XStreamConverter(value = XStreamCDataConverter.class)
  private String deviceType;

  /**
   * 设备ID.
   * 第三方提供
   */
  @XStreamAlias("DeviceID")
  @XStreamConverter(value = XStreamCDataConverter.class)
  private String deviceId;
  
  /**
   * 微信客户端生成的session id，用于request和response对应，
   * 因此响应中该字段第三方需要原封不变的带回 
   */
  @XStreamAlias("SessionID")
  @XStreamConverter(value = XStreamCDataConverter.class)
  private String sessionId;

  /**
   * 微信用户账号的OpenID.
   */
  @XStreamAlias("OpenID")
  @XStreamConverter(value = XStreamCDataConverter.class)
  private String openId;

  @XStreamAlias("HardWare")
  private HardWare hardWare = new HardWare();

  /**
   * 请求类型.
   * 0：退订设备状态；
   * 1：心跳；（心跳的处理方式跟订阅一样）
   * 2：订阅设备状态
   */
  @XStreamAlias("OpType")
  private Integer opType;

  /**
   * 设备状态.
   * 0：未连接；1：已连接
   */
  @XStreamAlias("DeviceStatus")
  private Integer deviceStatus;

  ///////////////////////////////////////
  // 小程序 审核事件
  ///////////////////////////////////////
  /**
   * 审核成功时的时间（整形），时间戳
   */
  @XStreamAlias("SuccTime")
  private Long succTime;

  /**
   * 审核失败的原因
   */
  @XStreamAlias("Reason")
  private String reason;

  ///////////////////////////////////////
  // 扫一扫事件推送
  ///////////////////////////////////////
  /**
   * 商品编码标准
   */
  @XStreamAlias("KeyStandard")
  private String keyStandard;
  /**
   * 商品编码内容
   */
  @XStreamAlias("KeyStr")
  private String keyStr;

  /**
   * 用户在微信内设置的国家
   */
  @XStreamAlias("Country")
  private String country;

  /**
   * 用户在微信内设置的省份
   */
  @XStreamAlias("Province")
  private String province;

  /**
   * 用户在微信内设置的城市
   */
  @XStreamAlias("City")
  private String city;

  /**
   * 用户的性别，1为男性，2为女性，0代表未知
   */
  @XStreamAlias("Sex")
  private String sex;

  /**
   * 打开商品主页的场景，1为扫码，2为其他打开场景（如会话、收藏或朋友圈）
   */
  @XStreamAlias("Scene")
  private String scene;

  /**
   * 调用“获取商品二维码接口”时传入的extinfo，为标识参数
   */
  @XStreamAlias("ExtInfo")
  private String extInfo;

  /**
   * 用户的实时地理位置信息（目前只精确到省一级），可在国家统计局网站查到对应明细： http://www.stats.gov.cn/tjsj/tjbz/xzqhdm/201504/t20150415_712722.html
   */
  @XStreamAlias("RegionCode")
  private String regionCode;

  /**
   * 审核未通过的原因。
   */
  @XStreamAlias("ReasonMsg")
  private String reasonMsg;

  public static WxMpXmlMessage fromXml(String xml) {
    //修改微信变态的消息内容格式，方便解析
    xml = xml.replace("</PicList><PicList>", "");
    final WxMpXmlMessage xmlMessage = XStreamTransformer.fromXml(WxMpXmlMessage.class, xml);
    xmlMessage.setAllFieldsMap(XmlUtils.xml2Map(xml));
    return xmlMessage;
  }

  public static WxMpXmlMessage fromXml(InputStream is) {
    return XStreamTransformer.fromXml(WxMpXmlMessage.class, is);
  }

  /**
   * 从加密字符串转换.
   *
   * @param encryptedXml      密文
   * @param wxMpConfigStorage 配置存储器对象
   * @param timestamp         时间戳
   * @param nonce             随机串
   * @param msgSignature      签名串
   */
  public static WxMpXmlMessage fromEncryptedXml(String encryptedXml, WxMpConfigStorage wxMpConfigStorage,
                                                String timestamp, String nonce, String msgSignature) {
    WxMpCryptUtil cryptUtil = new WxMpCryptUtil(wxMpConfigStorage);
    String plainText = cryptUtil.decrypt(msgSignature, timestamp, nonce, encryptedXml);
    log.debug("解密后的原始xml消息内容：{}", plainText);
    return fromXml(plainText);
  }

  public static WxMpXmlMessage fromEncryptedXml(InputStream is, WxMpConfigStorage wxMpConfigStorage, String timestamp,
                                                String nonce, String msgSignature) {
    try {
      return fromEncryptedXml(IOUtils.toString(is, StandardCharsets.UTF_8), wxMpConfigStorage, timestamp, nonce, msgSignature);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * <pre>
   * 当接受用户消息时，可能会获得以下值：
   * {@link WxConsts.XmlMsgType#TEXT}
   * {@link WxConsts.XmlMsgType#IMAGE}
   * {@link WxConsts.XmlMsgType#VOICE}
   * {@link WxConsts.XmlMsgType#VIDEO}
   * {@link WxConsts.XmlMsgType#LOCATION}
   * {@link WxConsts.XmlMsgType#LINK}
   * {@link WxConsts.XmlMsgType#EVENT}
   * </pre>
   */
  public String getMsgType() {
    return this.msgType;
  }

  /**
   * <pre>
   * 当发送消息的时候使用：
   * {@link WxConsts.XmlMsgType#TEXT}
   * {@link WxConsts.XmlMsgType#IMAGE}
   * {@link WxConsts.XmlMsgType#VOICE}
   * {@link WxConsts.XmlMsgType#VIDEO}
   * {@link WxConsts.XmlMsgType#NEWS}
   * {@link WxConsts.XmlMsgType#MUSIC}
   * </pre>
   */
  public void setMsgType(String msgType) {
    this.msgType = msgType;
  }

  @Override
  public String toString() {
    return WxMpGsonBuilder.create().toJson(this);
  }

}
