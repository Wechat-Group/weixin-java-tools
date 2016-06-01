package me.chanjar.weixin.mp.bean.result;

import java.io.Serializable;

/**
 * <pre>
 * 查询订单支付状态返回的结果
 * 
 * 查询订单(详见http://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_2)
 *
 * </pre>
 *
 * @author ukid
 */
public class WxMpPayResult implements Serializable {
  /**
   * 
   */
  private static final long serialVersionUID = -570934170727777190L;
  
  private String return_code;
  private String return_msg;
  private String appid;
  private String mch_id;
  private String nonce_str;
  private String sign;
  private String result_code;
  private String err_code;
  private String err_code_des;
  private String trade_state;
  private String trade_state_desc;
  private String device_info;
  private String openid;
  private String is_subscribe;
  private String trade_type;
  private String bank_type;
  private String total_fee;
  private String coupon_fee;
  private String fee_type;
  private String transaction_id;
  private String out_trade_no;
  private String attach;
  private String time_end;

  public String getReturn_code() {
    return return_code;
  }

  public String getReturn_msg() {
    return return_msg;
  }

  public String getAppid() {
    return appid;
  }

  public String getMch_id() {
    return mch_id;
  }

  public String getNonce_str() {
    return nonce_str;
  }

  public String getSign() {
    return sign;
  }

  public String getResult_code() {
    return result_code;
  }

  public String getErr_code() {
    return err_code;
  }

  public String getErr_code_des() {
    return err_code_des;
  }

  public String getTrade_state() {
    return trade_state;
  }

  public String getDevice_info() {
    return device_info;
  }

  public String getOpenid() {
    return openid;
  }

  public String getIs_subscribe() {
    return is_subscribe;
  }

  public String getTrade_type() {
    return trade_type;
  }

  public String getBank_type() {
    return bank_type;
  }

  public String getTotal_fee() {
    return total_fee;
  }

  public String getCoupon_fee() {
    return coupon_fee;
  }

  public String getFee_type() {
    return fee_type;
  }

  public String getTransaction_id() {
    return transaction_id;
  }

  public String getOut_trade_no() {
    return out_trade_no;
  }

  public String getAttach() {
    return attach;
  }

  public String getTime_end() {
    return time_end;
  }

  public void setReturn_code(String return_code) {
    this.return_code = return_code;
  }

  public void setReturn_msg(String return_msg) {
    this.return_msg = return_msg;
  }

  public void setAppid(String appid) {
    this.appid = appid;
  }

  public void setMch_id(String mch_id) {
    this.mch_id = mch_id;
  }

  public void setNonce_str(String nonce_str) {
    this.nonce_str = nonce_str;
  }

  public void setSign(String sign) {
    this.sign = sign;
  }

  public void setResult_code(String result_code) {
    this.result_code = result_code;
  }

  public void setErr_code(String err_code) {
    this.err_code = err_code;
  }

  public void setErr_code_des(String err_code_des) {
    this.err_code_des = err_code_des;
  }

  public void setTrade_state(String trade_state) {
    this.trade_state = trade_state;
  }

  public void setDevice_info(String device_info) {
    this.device_info = device_info;
  }

  public void setOpenid(String openid) {
    this.openid = openid;
  }

  public void setIs_subscribe(String is_subscribe) {
    this.is_subscribe = is_subscribe;
  }

  public void setTrade_type(String trade_type) {
    this.trade_type = trade_type;
  }

  public void setBank_type(String bank_type) {
    this.bank_type = bank_type;
  }

  public void setTotal_fee(String total_fee) {
    this.total_fee = total_fee;
  }

  public void setCoupon_fee(String coupon_fee) {
    this.coupon_fee = coupon_fee;
  }

  public void setFee_type(String fee_type) {
    this.fee_type = fee_type;
  }

  public void setTransaction_id(String transaction_id) {
    this.transaction_id = transaction_id;
  }

  public void setOut_trade_no(String out_trade_no) {
    this.out_trade_no = out_trade_no;
  }

  public void setAttach(String attach) {
    this.attach = attach;
  }

  public void setTime_end(String time_end) {
    this.time_end = time_end;
  }

  public String getTrade_state_desc() {
    return trade_state_desc;
  }

  public void setTrade_state_desc(String trade_state_desc) {
    this.trade_state_desc = trade_state_desc;
  }

  @Override
  public String toString() {
    return "WxMpPayResult{" +
        "return_code=" + return_code +
        ", return_msg='" + return_msg + '\'' +
        ", appid='" + appid + '\'' +
        ", mch_id='" + mch_id + '\'' +
        ", nonce_str='" + nonce_str + '\'' +
        ", sign='" + sign + '\'' +
        ", result_code='" + result_code + '\'' +
        ", err_code='" + err_code + '\'' +
        ", err_code_des='" + err_code_des + '\'' +
        ", trade_state=" + trade_state +
        ", trade_state_desc=" + trade_state_desc +
        ", device_info='" + device_info + '\'' +
        ", openid='" + openid + '\'' +
        ", is_subscribe='" + is_subscribe + '\'' +
        ", trade_type='" + trade_type + '\'' +
        ", bank_type='" + bank_type + '\'' +
        ", total_fee='" + total_fee + '\'' +
        ", coupon_fee='" + coupon_fee + '\'' +
        ", fee_type='" + fee_type + '\'' +
        ", transaction_id='" + transaction_id + '\'' +
        ", out_trade_no='" + out_trade_no + '\'' +
        ", attach='" + attach + '\'' +
        ", time_end='" + time_end + '\'' +
        '}';
  }
}
