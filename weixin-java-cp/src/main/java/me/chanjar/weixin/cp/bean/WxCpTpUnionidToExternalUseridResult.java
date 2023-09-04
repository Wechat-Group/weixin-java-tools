package me.chanjar.weixin.cp.bean;

import com.google.gson.annotations.SerializedName;
import lombok.*;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;


@Getter
@Setter
public class WxCpTpUnionidToExternalUseridResult extends WxCpBaseResp {


  private static final long serialVersionUID = -6153589164415497369L;

  @SerializedName("external_userid")
  private String externalUserid;

  @SerializedName("pending_id")
  private String pendingId;


  public static WxCpTpUnionidToExternalUseridResult fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpTpUnionidToExternalUseridResult.class);
  }


}
