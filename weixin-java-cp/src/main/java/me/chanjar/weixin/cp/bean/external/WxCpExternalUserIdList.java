package me.chanjar.weixin.cp.bean.external;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;
import me.chanjar.weixin.cp.bean.WxCpBaseResp;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * 企业客户微信unionid的升级 - 企业客户external_userid列表
 *
 * @author Mr.Pan
 * @date 2021/11/18
 */
@Getter
@Setter
public class WxCpExternalUserIdList extends WxCpBaseResp {
  private static final long serialVersionUID = 3922210865083522513L;

  @SerializedName("external_userid_info")
  private List<ExternalUserIdInfo> externalUserIdInfo;

  @Getter
  @Setter
  public static class ExternalUserIdInfo implements Serializable {
    private static final long serialVersionUID = 8846290993790709261L;

    /**
     * 所属企业id
     */
    @SerializedName("corpid")
    private String corpId;

    /**
     * 外部联系人id
     */
    @SerializedName("external_userid")
    private String externalUserId;

    /**
     * 新外部联系人id
     */
    @SerializedName("new_external_userid")
    private String newExternalUserId;

  }

  public static WxCpExternalUserIdList fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpExternalUserIdList.class);
  }
}
