package me.chanjar.weixin.cp.bean.external;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;
import me.chanjar.weixin.cp.bean.WxCpBaseResp;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * 转接在职成员的客户给其他成员，返回对象
 *
 * @author pg  created on  2021年6月21日
 */
@Getter
@Setter
public class WxCpUserTransferCustomerResp extends WxCpBaseResp {
  private static final long serialVersionUID = -8030598756503590089L;
  /**
   * 客户转移结果列表
   */
  private List<TransferCustomer> customer;

  /**
   * From json wx cp user transfer customer resp.
   *
   * @param json the json
   * @return the wx cp user transfer customer resp
   */
  public static WxCpUserTransferCustomerResp fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpUserTransferCustomerResp.class);
  }

  public String toJson() {
    return WxCpGsonBuilder.create().toJson(this);
  }

  /**
   * 转接客户结果实体
   */
  @Getter
  @Setter
  public static class TransferCustomer implements Serializable {
    private static final long serialVersionUID = 8720554208727083338L;
    /**
     * 客户的external_userid
     */
    @SerializedName("external_userid")
    private String externalUserid;
    /**
     * 对此客户进行分配的结果, 0表示成功发起接替,待24小时后自动接替,并不代表最终接替成功
     */
    private Integer errcode;

    /**
     * From json wx cp user transfer customer resp . transfer customer.
     *
     * @param json the json
     * @return the wx cp user transfer customer resp . transfer customer
     */
    public static WxCpUserTransferCustomerResp.TransferCustomer fromJson(String json) {
      return WxCpGsonBuilder.create().fromJson(json, WxCpUserTransferCustomerResp.TransferCustomer.class);
    }

    /**
     * To json string.
     *
     * @return the string
     */
    public String toJson() {
      return WxCpGsonBuilder.create().toJson(this);
    }
  }
}
