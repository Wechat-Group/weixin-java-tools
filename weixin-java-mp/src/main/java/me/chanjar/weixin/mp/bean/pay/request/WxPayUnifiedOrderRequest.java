package me.chanjar.weixin.mp.bean.pay.request;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import me.chanjar.weixin.common.annotation.Required;

/**
 * <pre>
 * 统一下单请求参数对象
 * 参考文档：https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_1
 * 注释中各行每个字段描述对应如下：
 * <li>字段名
 * <li>变量名
 * <li>是否必填
 * <li>类型
 * <li>示例值
 * <li>描述
 * </pre>
 * Created by Binary Wang on 2016/9/25.
 * @author binarywang (https://github.com/binarywang)
 */
@XStreamAlias("xml")
public class WxPayUnifiedOrderRequest extends WxPayBaseRequest {

  /**
   * <pre>
   * 设备号
   * device_info
   * 否
   * String(32)
   * 013467007045764
   * 终端设备号(门店号或收银设备Id)，注意：PC网页或公众号内支付请传"WEB"
   * </pre>
   */
  @XStreamAlias("device_info")
  private String deviceInfo;

  /**
   * <pre>
   * 商品描述
   * body
   * 是
   * String(128)
   * 腾讯充值中心-QQ会员充值
   * 商品简单描述，该字段须严格按照规范传递，具体请见参数规定
   * </pre>
   */
  @Required
  @XStreamAlias("body")
  private String body;

  /**
   * <pre>
   * 商品详情
   * detail
   * 否
   * String(6000)
   *  {  "goods_detail":[
    {
    "goods_id":"iphone6s_16G",
    "wxpay_goods_id":"1001",
    "goods_name":"iPhone6s 16G",
    "goods_num":1,
    "price":528800,
    "goods_category":"123456",
    "body":"苹果手机"
    },
    {
    "goods_id":"iphone6s_32G",
    "wxpay_goods_id":"1002",
    "goods_name":"iPhone6s 32G",
    "quantity":1,
    "price":608800,
    "goods_category":"123789",
    "body":"苹果手机"
    }
    ]
    }
            商品详细列表，使用Json格式，传输签名前请务必使用CDATA标签将JSON文本串保护起来。
      goods_detail []：
      └ goods_id String 必填 32 商品的编号
      └ wxpay_goods_id String 可选 32 微信支付定义的统一商品编号
      └ goods_name String 必填 256 商品名称
      └ goods_num Int 必填 商品数量
      └ price Int 必填 商品单价，单位为分
      └ goods_category String 可选 32 商品类目Id
      └ body String 可选 1000 商品描述信息
   * </pre>
   */
  @XStreamAlias("detail")
  private String detail;

  /**
   * <pre>
   * 附加数据
   * attach
   * 否
   * String(127)
   * 深圳分店
   *  附加数据，在查询API和支付通知中原样返回，该字段主要用于商户携带订单的自定义数据
   * </pre>
   */
  @XStreamAlias("attach")
  private String attach;

  /**
   * <pre>
   * 商户订单号
   * out_trade_no
   * 是
   * String(32)
   * 20150806125346
   * 商户系统内部的订单号,32个字符内、可包含字母, 其他说明见商户订单号
   * </pre>
   */
  @Required
  @XStreamAlias("out_trade_no")
  private String outTradeNo;

  /**
   * <pre>
   * 货币类型
   * fee_type
   * 否
   * String(16)
   * CNY
   * 符合ISO 4217标准的三位字母代码，默认人民币：CNY，其他值列表详见货币类型
   * </pre>
   */
  @XStreamAlias("fee_type")
  private String feeType;

  /**
   * <pre>
   * 总金额
   * total_fee
   * 是
   * Int
   * 888
   * 订单总金额，单位为分，详见支付金额
   * </pre>
   */
  @Required
  @XStreamAlias("total_fee")
  private Integer totalFee;

  /**
   * <pre>
   * 终端IP
   * spbill_create_ip
   * 是
   * String(16)
   * 123.12.12.123
   * APP和网页支付提交用户端ip，Native支付填调用微信支付API的机器IP。
   * </pre>
   */
  @Required
  @XStreamAlias("spbill_create_ip")
  private String spbillCreateIp;

  /**
   * <pre>
   * 交易起始时间
   * time_start
   * 否
   * String(14)
   * 20091225091010
   * 订单生成时间，格式为yyyyMMddHHmmss，如2009年12月25日9点10分10秒表示为20091225091010。其他详见时间规则
   * </pre>
   */
  @XStreamAlias("time_start")
  private String timeStart;

  /**
   * <pre>
   * 交易结束时间
   * time_expire
   * 否
   * String(14)
   * 20091227091010
   * 订单失效时间，格式为yyyyMMddHHmmss，如2009年12月27日9点10分10秒表示为20091227091010。其他详见时间规则
   *   注意：最短失效时间间隔必须大于5分钟
   * </pre>
   */
  @XStreamAlias("time_expire")
  private String timeExpire;

  /**
   * <pre>
   * 商品标记
   * goods_tag
   * 否
   * String(32)
   * WXG
   * 商品标记，代金券或立减优惠功能的参数，说明详见代金券或立减优惠
   * </pre>
   */
  @XStreamAlias("goods_tag")
  private String goodsTag;

  /**
   * <pre>
   * 通知地址
   * notify_url
   * 是
   * String(256)
   * http://www.weixin.qq.com/wxpay/pay.php
   * 接收微信支付异步通知回调地址，通知url必须为直接可访问的url，不能携带参数。
   * </pre>
   */
  @Required
  @XStreamAlias("notify_url")
  private String notifyURL;

  /**
   * <pre>
   * 交易类型
   * trade_type
   * 是
   * String(16)
   * JSAPI
   * 取值如下：JSAPI，NATIVE，APP，详细说明见参数规定:
   * JSAPI--公众号支付、NATIVE--原生扫码支付、APP--app支付，统一下单接口trade_type的传参可参考这里
   * </pre>
   */
  @Required
  @XStreamAlias("trade_type")
  private String tradeType;

  /**
   * <pre>
   * 商品Id
   * product_id
   * 否
   * String(32)
   * 12235413214070356458058
   * trade_type=NATIVE，此参数必传。此id为二维码中包含的商品Id，商户自行定义。
   * </pre>
   */
  @XStreamAlias("product_id")
  private String productId;

  /**
   * <pre>
   * 指定支付方式
   * limit_pay
   * 否
   * String(32)
   * no_credit no_credit--指定不能使用信用卡支付
   * </pre>
   */
  @XStreamAlias("limit_pay")
  private String limitPay;

  /**
   * <pre>
   * 用户标识
   * openid
   * 否
   * String(128)
   * oUpF8uMuAJO_M2pxb1Q9zNjWeS6o
   * trade_type=JSAPI，此参数必传，用户在商户appid下的唯一标识。
   * openid如何获取，可参考【获取openid】。
   * 企业号请使用【企业号OAuth2.0接口】获取企业号内成员userid，再调用【企业号userid转openid接口】进行转换
   * </pre>
   */
  @XStreamAlias("openid")
  private String openid;


  public String getDeviceInfo() {
    return this.deviceInfo;
  }

  public void setDeviceInfo(String deviceInfo) {
    this.deviceInfo = deviceInfo;
  }

  public String getBody() {
    return this.body;
  }

  public void setBody(String body) {
    this.body = body;
  }

  public String getDetail() {
    return this.detail;
  }

  public void setDetail(String detail) {
    this.detail = detail;
  }

  public String getAttach() {
    return this.attach;
  }

  public void setAttach(String attach) {
    this.attach = attach;
  }

  public String getOutTradeNo() {
    return this.outTradeNo;
  }

  public void setOutTradeNo(String outTradeNo) {
    this.outTradeNo = outTradeNo;
  }

  public String getFeeType() {
    return this.feeType;
  }

  public void setFeeType(String feeType) {
    this.feeType = feeType;
  }

  public Integer getTotalFee() {
    return this.totalFee;
  }

  public void setTotalFee(Integer totalFee) {
    this.totalFee = totalFee;
  }

  public String getSpbillCreateIp() {
    return this.spbillCreateIp;
  }

  public void setSpbillCreateIp(String spbillCreateIp) {
    this.spbillCreateIp = spbillCreateIp;
  }

  public String getTimeStart() {
    return this.timeStart;
  }

  public void setTimeStart(String timeStart) {
    this.timeStart = timeStart;
  }

  public String getTimeExpire() {
    return this.timeExpire;
  }

  public void setTimeExpire(String timeExpire) {
    this.timeExpire = timeExpire;
  }

  public String getGoodsTag() {
    return this.goodsTag;
  }

  public void setGoodsTag(String goodsTag) {
    this.goodsTag = goodsTag;
  }

  public String getNotifyURL() {
    return this.notifyURL;
  }

  /**
   * 如果配置中已经设置，可以不设置值
   * @param notifyURL
   */
  public void setNotifyURL(String notifyURL) {
    this.notifyURL = notifyURL;
  }

  public String getTradeType() {
    return this.tradeType;
  }

  /**
   * 如果配置中已经设置，可以不设置值
   * @param tradeType 交易类型
   */
  public void setTradeType(String tradeType) {
    this.tradeType = tradeType;
  }

  public String getProductId() {
    return this.productId;
  }

  public void setProductId(String productId) {
    this.productId = productId;
  }

  public String getLimitPay() {
    return this.limitPay;
  }

  public void setLimitPay(String limitPay) {
    this.limitPay = limitPay;
  }

  public String getOpenid() {
    return this.openid;
  }

  public void setOpenid(String openid) {
    this.openid = openid;
  }

  public static WxUnifiedOrderRequestBuilder builder() {
    return new WxUnifiedOrderRequestBuilder();
  }

  public static class WxUnifiedOrderRequestBuilder {
    private String appid;
    private String mchId;
    private String deviceInfo;
    private String nonceStr;
    private String sign;
    private String body;
    private String detail;
    private String attach;
    private String outTradeNo;
    private String feeType;
    private Integer totalFee;
    private String spbillCreateIp;
    private String timeStart;
    private String timeExpire;
    private String goodsTag;
    private String notifyURL;
    private String tradeType;
    private String productId;
    private String limitPay;
    private String openid;

    public WxUnifiedOrderRequestBuilder appid(String appid) {
      this.appid = appid;
      return this;
    }

    public WxUnifiedOrderRequestBuilder mchId(String mchId) {
      this.mchId = mchId;
      return this;
    }

    public WxUnifiedOrderRequestBuilder deviceInfo(String deviceInfo) {
      this.deviceInfo = deviceInfo;
      return this;
    }

    public WxUnifiedOrderRequestBuilder nonceStr(String nonceStr) {
      this.nonceStr = nonceStr;
      return this;
    }

    public WxUnifiedOrderRequestBuilder sign(String sign) {
      this.sign = sign;
      return this;
    }

    public WxUnifiedOrderRequestBuilder body(String body) {
      this.body = body;
      return this;
    }

    public WxUnifiedOrderRequestBuilder detail(String detail) {
      this.detail = detail;
      return this;
    }

    public WxUnifiedOrderRequestBuilder attach(String attach) {
      this.attach = attach;
      return this;
    }

    public WxUnifiedOrderRequestBuilder outTradeNo(String outTradeNo) {
      this.outTradeNo = outTradeNo;
      return this;
    }

    public WxUnifiedOrderRequestBuilder feeType(String feeType) {
      this.feeType = feeType;
      return this;
    }

    public WxUnifiedOrderRequestBuilder totalFee(Integer totalFee) {
      this.totalFee = totalFee;
      return this;
    }

    public WxUnifiedOrderRequestBuilder spbillCreateIp(String spbillCreateIp) {
      this.spbillCreateIp = spbillCreateIp;
      return this;
    }

    public WxUnifiedOrderRequestBuilder timeStart(String timeStart) {
      this.timeStart = timeStart;
      return this;
    }

    public WxUnifiedOrderRequestBuilder timeExpire(String timeExpire) {
      this.timeExpire = timeExpire;
      return this;
    }

    public WxUnifiedOrderRequestBuilder goodsTag(String goodsTag) {
      this.goodsTag = goodsTag;
      return this;
    }

    public WxUnifiedOrderRequestBuilder notifyURL(String notifyURL) {
      this.notifyURL = notifyURL;
      return this;
    }

    public WxUnifiedOrderRequestBuilder tradeType(String tradeType) {
      this.tradeType = tradeType;
      return this;
    }

    public WxUnifiedOrderRequestBuilder productId(String productId) {
      this.productId = productId;
      return this;
    }

    public WxUnifiedOrderRequestBuilder limitPay(String limitPay) {
      this.limitPay = limitPay;
      return this;
    }

    public WxUnifiedOrderRequestBuilder openid(String openid) {
      this.openid = openid;
      return this;
    }

    public WxUnifiedOrderRequestBuilder from(WxPayUnifiedOrderRequest origin) {
      this.appid(origin.appid);
      this.mchId(origin.mchId);
      this.deviceInfo(origin.deviceInfo);
      this.nonceStr(origin.nonceStr);
      this.sign(origin.sign);
      this.body(origin.body);
      this.detail(origin.detail);
      this.attach(origin.attach);
      this.outTradeNo(origin.outTradeNo);
      this.feeType(origin.feeType);
      this.totalFee(origin.totalFee);
      this.spbillCreateIp(origin.spbillCreateIp);
      this.timeStart(origin.timeStart);
      this.timeExpire(origin.timeExpire);
      this.goodsTag(origin.goodsTag);
      this.notifyURL(origin.notifyURL);
      this.tradeType(origin.tradeType);
      this.productId(origin.productId);
      this.limitPay(origin.limitPay);
      this.openid(origin.openid);
      return this;
    }

    public WxPayUnifiedOrderRequest build() {
      WxPayUnifiedOrderRequest m = new WxPayUnifiedOrderRequest();
      m.appid = this.appid;
      m.mchId = this.mchId;
      m.deviceInfo = this.deviceInfo;
      m.nonceStr = this.nonceStr;
      m.sign = this.sign;
      m.body = this.body;
      m.detail = this.detail;
      m.attach = this.attach;
      m.outTradeNo = this.outTradeNo;
      m.feeType = this.feeType;
      m.totalFee = this.totalFee;
      m.spbillCreateIp = this.spbillCreateIp;
      m.timeStart = this.timeStart;
      m.timeExpire = this.timeExpire;
      m.goodsTag = this.goodsTag;
      m.notifyURL = this.notifyURL;
      m.tradeType = this.tradeType;
      m.productId = this.productId;
      m.limitPay = this.limitPay;
      m.openid = this.openid;
      return m;
    }
  }

}
