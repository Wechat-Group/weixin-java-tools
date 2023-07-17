package me.chanjar.weixin.cp.bean.external.acquisition;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * 获取由获客链接添加的客户信息结果
 *
 * @author alien_zyl
 */
@Data
public class WxCpCustomerAcquisitionCustomerList {

  @SerializedName("customer_list")
  private List<Customer> customerList;

  /**
   * 分页游标，再下次请求时填写以获取之后分页的记录，如果已经没有更多的数据则返回空
   */
  @SerializedName("next_cursor")
  private String nextCursor;


  public static WxCpCustomerAcquisitionCustomerList fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpCustomerAcquisitionCustomerList.class);
  }

  public String toJson() {
    return WxCpGsonBuilder.create().toJson(this);
  }

  @Data
  public static class Customer implements Serializable {
    private static final long serialVersionUID = 4456053823277371278L;

    /**
     * 客户external_userid
     */
    @SerializedName("external_userid")
    private String externalUserid;

    /**
     * 通过获客链接添加此客户的跟进人userid
     */
    @SerializedName("userid")
    private String userid;

    /**
     * 会话状态，0-客户未发消息 1-客户已发送消息
     */
    @SerializedName("chat_status")
    private Integer chatStatus;

    /**
     * 用于区分客户具体是通过哪个获客链接进行添加，
     * 用户可在获客链接后拼接customer_channel=自定义字符串，字符串不超过64字节，超过会被截断。
     * 通过点击带有customer_channel参数的链接获取到的客户，调用获客信息接口或获取客户详情接口时，返回的state参数即为链接后拼接自定义字符串
     */
    @SerializedName("state")
    private String state;

  }

}
