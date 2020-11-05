package com.github.binarywang.wxpay.bean.payscore;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 后付费项目.
 *
 * @author doger.wang
 * @date 2020-05-19
 */
@Data
@NoArgsConstructor
public class PostPayment implements Serializable {
  private static final long serialVersionUID = 2007722927556382895L;
  /**
   * name : 就餐费用服务费
   * amount : 4000
   * description : 就餐人均100元服务费：100/小时
   * count : 1
   */
  @SerializedName("name")
  private String name;
  @SerializedName("amount")
  private int amount;
  @SerializedName("description")
  private String description;
  @SerializedName("count")
  private Integer count;
}
