package com.github.binarywang.wxpay.bean.payscore;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @description 内容信息详情
 * createTime: 2023/9/19 16:39
 **/
@Data
@NoArgsConstructor
public class SyncDetail implements Serializable {

  private static final long serialVersionUID = 8173356554917822934L;
  @SerializedName("seq")
  private int seq;
  @SerializedName("paid_time")
  private String paidTime;
  @SerializedName("paid_amount")
  private Integer paidAmount;
}
