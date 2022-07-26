package me.chanjar.weixin.cp.bean.license;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.io.Serializable;

/**
 * 详细的订单信息
 * 文档地址：https://developer.work.weixin.qq.com/document/path/95648
 * @author Totoro
 * @date 2022/6/27 11:38
 */
@Data
public class WxCpTpLicenseOrder implements Serializable {

  private static final long serialVersionUID = -4094302825442292644L;

  @SerializedName("order_id")
  private String orderId;

  @SerializedName("order_type")
  private Integer orderType;

  @SerializedName("order_status")
  private Integer orderStatus;

  @SerializedName("corpid")
  private String corpId;

  @SerializedName("price")
  private Long price;

  @SerializedName("account_count")
  private WxCpTpLicenseAccountCount accountCount;

  @SerializedName("account_duration")
  private WxCpTpLicenseAccountDuration accountDuration;

  @SerializedName("create_time")
  private Long createTime;

  @SerializedName("pay_time")
  private Long payTime;


  public String toJson() {
    return WxCpGsonBuilder.create().toJson(this);
  }

}
