package me.chanjar.weixin.cp.bean.external;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;
import me.chanjar.weixin.common.annotation.Required;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * 转接在职成员的客户给其他成员，请求对象
 *
 * @author pg
 * @date 2021年6月21日
 */
@Getter
@Setter
public class WxCpUserTransferCustomerReq implements Serializable {
  private static final long serialVersionUID = -309819538677411801L;
  /**
   * 原跟进成员的userid
   */
  @SerializedName("handover_userid")
  @Required
  private String handOverUserid;
  /**
   * 接替成员的userid
   */
  @SerializedName("takeover_userid")
  @Required
  private String takeOverUserid;
  /**
   * 客户的external_userid列表，每次最多分配100个客户
   */
  @SerializedName("external_userid")
  @Required
  private List<String> externalUserid;
  /**
   * 转移成功后发给客户的消息，最多200个字符，不填则使用默认文案
   */
  @SerializedName("transfer_success_msg")
  private String transferMsg;

  public String toJson() {
    return WxCpGsonBuilder.create().toJson(this);
  }
}
