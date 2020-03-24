package me.chanjar.weixin.cp.bean.oa;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;


/**
 * 企业微信操作人
 *
 * @author element
 */
@Data
public class WxCpOperator implements Serializable {

  private static final long serialVersionUID = 5797144853574346736L;

  /**
   * 企业微信userid
   */
  @SerializedName("userid")
  private String userId;
}
