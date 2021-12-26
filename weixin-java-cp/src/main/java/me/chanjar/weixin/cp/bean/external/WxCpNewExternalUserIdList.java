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
public class WxCpNewExternalUserIdList extends WxCpBaseResp {

  @SerializedName("items")
  private List<NewExternalUserIdInfo> items;

  @Getter
  @Setter
  public static class NewExternalUserIdInfo implements Serializable {
    private static final long serialVersionUID = 8846290993790709261L;

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

  public static WxCpNewExternalUserIdList fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpNewExternalUserIdList.class);
  }
}
