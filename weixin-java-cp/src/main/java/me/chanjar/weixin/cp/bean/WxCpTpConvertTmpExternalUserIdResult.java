package me.chanjar.weixin.cp.bean;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.util.List;

@Setter
@Getter
public class WxCpTpConvertTmpExternalUserIdResult extends WxCpBaseResp {


  @SerializedName("invalid_tmp_external_userid_list")
  private List<Results> results;

  @Getter
  @Setter
  public static class Results {

    @SerializedName("tmp_external_userid")
    private String tmpExternalUserId;

    @SerializedName("external_userid")
    private String externalUserId;

    @SerializedName("corpid")
    private String corpId;

    @SerializedName("userid")
    private String userId;
  }

  @SerializedName("invalid_tmp_external_userid_list")
  private List<String> invalidTmpExternalUserIdList;

  public static WxCpTpConvertTmpExternalUserIdResult fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpTpConvertTmpExternalUserIdResult.class);
  }
}
