package me.chanjar.weixin.cp.bean.external;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import me.chanjar.weixin.cp.bean.WxCpBaseResp;
import me.chanjar.weixin.cp.bean.external.moment.CustomerItem;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.util.List;

/**
 * 企业发表内容到客户的朋友圈 获取客户朋友圈发表时选择的可见范围
 *
 * @author leiin
 * @date 2021-10-29
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class WxCpGetMomentCustomerList extends WxCpBaseResp {
  private static final long serialVersionUID = -8792120670063917097L;

  @SerializedName("next_cursor")
  private String nextCursor;
  @SerializedName("customer_list")
  private List<CustomerItem> customerList;

  public static WxCpGetMomentCustomerList fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpGetMomentCustomerList.class);
  }

  public String toJson() {
    return WxCpGsonBuilder.create().toJson(this);
  }
}
