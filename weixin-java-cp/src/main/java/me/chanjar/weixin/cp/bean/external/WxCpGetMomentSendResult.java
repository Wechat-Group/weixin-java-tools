package me.chanjar.weixin.cp.bean.external;

import com.google.gson.annotations.SerializedName;
import java.util.List;
import lombok.Data;
import me.chanjar.weixin.cp.bean.WxCpBaseResp;
import me.chanjar.weixin.cp.bean.external.moment.CustomerItem;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

/**
 * 企业发表内容到客户的朋友圈 获取客户朋友圈发表后的可见客户列表
 *
 * @author leiin
 * @date 2021-10-29
 */
@Data
public class WxCpGetMomentSendResult extends WxCpBaseResp {
  @SerializedName("next_cursor")
  private String nextCursor;
  @SerializedName("customer_list")
  private List<CustomerItem> customerList;

  public static WxCpGetMomentSendResult fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpGetMomentSendResult.class);
  }

  public String toJson() {
    return WxCpGsonBuilder.create().toJson(this);
  }
}
