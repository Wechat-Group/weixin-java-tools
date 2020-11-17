package me.chanjar.weixin.mp.bean.card;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author yqx
 * @date 2018/11/07
 */
@Data
public class CardUpdateResult implements Serializable {
  private static final long serialVersionUID = 6049989267790615497L;

  @SerializedName("errcode")
  private int errCode;

  @SerializedName("errmsg")
  private String errMsg;

  /**
   * 此次更新是否需要提审，true为需要，false为不需要。
   */
  @SerializedName("send_check")
  private boolean sendCheck;

}
