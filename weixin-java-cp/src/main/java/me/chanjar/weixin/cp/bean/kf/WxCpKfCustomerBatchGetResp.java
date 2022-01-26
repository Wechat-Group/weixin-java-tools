package me.chanjar.weixin.cp.bean.kf;

import com.google.gson.annotations.SerializedName;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.cp.bean.WxCpBaseResp;
import me.chanjar.weixin.cp.bean.external.contact.ExternalContact;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

/**
 * @author leiin
 * @date 2022/1/26 7:56 下午
 */
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Data
public class WxCpKfCustomerBatchGetResp extends WxCpBaseResp {

  private static final long serialVersionUID = -3697709507605389887L;

  @SerializedName("customer_list")
  private List<ExternalContact> customerList;

  @SerializedName("invalid_external_userid")
  private List<String> invalidExternalUserId;

  public static WxCpKfCustomerBatchGetResp fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpKfCustomerBatchGetResp.class);
  }
}
