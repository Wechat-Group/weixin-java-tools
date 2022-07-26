package me.chanjar.weixin.cp.bean.license.order;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import me.chanjar.weixin.cp.bean.WxCpBaseResp;
import me.chanjar.weixin.cp.bean.license.WxCpTpLicenseSimpleOrder;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.util.List;

/**
 * 获取订单列表详情
 * 文档地址：https://developer.work.weixin.qq.com/document/path/95647
 * @author Totoro
 * @date 2022/6/27 11:39
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class WxCpTpLicenseOrderListResp extends WxCpBaseResp {
  private static final long serialVersionUID = 1878909432164961275L;

  @SerializedName("next_cursor")
  private String nextCursor;

  @SerializedName("has_more")
  private Integer hasMore;

  @SerializedName("order_list")
  private List<WxCpTpLicenseSimpleOrder> orderList;


  public static WxCpTpLicenseOrderListResp fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpTpLicenseOrderListResp.class);
  }



}
