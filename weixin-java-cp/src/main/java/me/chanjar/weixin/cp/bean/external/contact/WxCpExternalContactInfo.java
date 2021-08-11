package me.chanjar.weixin.cp.bean.external.contact;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * <pre>
 * 外部联系人详情
 * Created by Binary Wang on 2018/9/16.
 * 参考文档：https://work.weixin.qq.com/api/doc#13878
 * </pre>
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
@Data
public class WxCpExternalContactInfo implements Serializable {
  private static final long serialVersionUID = 4311777322534499260L;

  @SerializedName("external_contact")
  private ExternalContact externalContact;

  @SerializedName("follow_user")
  private List<FollowedUser> followedUsers;

  @SerializedName("next_cursor")
  private String nextCursor;

  public static WxCpExternalContactInfo fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpExternalContactInfo.class);
  }

}
