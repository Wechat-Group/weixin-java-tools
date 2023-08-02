package me.chanjar.weixin.cp.bean.message;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import me.chanjar.weixin.cp.bean.WxCpBaseResp;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

/**
 * 发送「学校通知」返回实体
 * https://developer.work.weixin.qq.com/document/path/92321
 *
 * @author <a href="https://github.com/0katekate0">Wang_Wong</a> created on  2022-06-29
 */
@Data
public class WxCpSchoolContactMessageSendResult extends WxCpBaseResp {
  private static final long serialVersionUID = 3990693822996824333L;

  @SerializedName("invalid_parent_userid")
  private String[] invalidParentUserId;

  @SerializedName("invalid_student_userid")
  private String[] invalidStudentUserId;

  @SerializedName("invalid_party")
  private String[] invalidParty;

  public String toJson() {
    return WxCpGsonBuilder.create().toJson(this);
  }

  /**
   * From json wx cp school contact message send result.
   *
   * @param json the json
   * @return the wx cp school contact message send result
   */
  public static WxCpSchoolContactMessageSendResult fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpSchoolContactMessageSendResult.class);
  }

}
