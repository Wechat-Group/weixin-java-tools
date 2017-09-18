package me.chanjar.weixin.mp.bean.invoice;

import com.google.gson.annotations.SerializedName;
import me.chanjar.weixin.common.util.json.WxGsonBuilder;
import me.chanjar.weixin.mp.util.json.WxMpGsonBuilder;

import java.io.Serializable;

public class InvoiceScanTitleResult implements Serializable{

  @SerializedName("title_type")
  private String titleType;

  @SerializedName("title")
  private String title;

  @SerializedName("tax_no")
  private String taxNo;

  @SerializedName("addr")
  private String addr;

  @SerializedName("phone")
  private String phone;

  @SerializedName("bank_type")
  private String bankType;

  @SerializedName("bank_no")
  private String bankNo;

  @SerializedName("errmsg")
  private String errmsg;

  @SerializedName("errcode")
  private String errcode;

  public String toJson() {
    return WxGsonBuilder.create().toJson(this);
  }

  public static InvoiceScanTitleResult fromJson(String json) {
    return WxMpGsonBuilder.create().fromJson(json, InvoiceScanTitleResult.class);
  }

  public String getTitleType() {
    return titleType;
  }

  public void setTitleType(String titleType) {
    this.titleType = titleType;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getTaxNo() {
    return taxNo;
  }

  public void setTaxNo(String taxNo) {
    this.taxNo = taxNo;
  }

  public String getAddr() {
    return addr;
  }

  public void setAddr(String addr) {
    this.addr = addr;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getBankType() {
    return bankType;
  }

  public void setBankType(String bankType) {
    this.bankType = bankType;
  }

  public String getBankNo() {
    return bankNo;
  }

  public void setBankNo(String bankNo) {
    this.bankNo = bankNo;
  }
}
