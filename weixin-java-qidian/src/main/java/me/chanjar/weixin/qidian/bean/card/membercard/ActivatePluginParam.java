package me.chanjar.weixin.qidian.bean.card.membercard;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

/**
 * @author yqx
 * @date 2018/9/19
 */
@Data
public class ActivatePluginParam {

  @SerializedName("encrypt_card_id")
  String encryptCardId;

  @SerializedName("outer_str")
  String outerStr;

  @SerializedName("biz")
  String biz;

}
