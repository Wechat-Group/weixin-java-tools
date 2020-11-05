package me.chanjar.weixin.cp.bean.external.contact;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;
import me.chanjar.weixin.cp.bean.WxCpBaseResp;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * <pre>
 * 批量获取客户详情
 * 参考文档：https://work.weixin.qq.com/api/doc/90000/90135/92994
 * </pre>
 *
 * @author <a href="https://github.com/alucardxh">alucardxh</a>
 */
@Getter
@Setter
public class WxCpExternalContactBatchInfo extends WxCpBaseResp implements Serializable {
  private static final long serialVersionUID = -5166048319463473186L;

  @SerializedName("external_contact_list")
  private List<ExternalContactInfo> externalContactList;

  @SerializedName("next_cursor")
  private String nextCursor;

  @Getter
  @Setter
  public static class ExternalContactInfo implements Serializable {
    private static final long serialVersionUID = 4723983768235723206L;

    @SerializedName("external_contact")
    private ExternalContact externalContact;

    @SerializedName("follow_info")
    private FollowedUser followInfo;
  }


  public static WxCpExternalContactBatchInfo fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpExternalContactBatchInfo.class);
  }

}
