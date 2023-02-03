package me.chanjar.weixin.cp.bean.oa.meeting;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import me.chanjar.weixin.cp.bean.WxCpBaseResp;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.io.Serializable;

/**
 * 为标签添加或移除用户结果对象类.
 *
 * @author <a href="https://github.com/wangmeng3486">wangmeng3486</a> created on  2023-01-31
 */
@Data
public class WxCpUserMeetingIdResult extends WxCpBaseResp implements Serializable {
  private static final long serialVersionUID = -4993287594652231097L;

  @Override
  public String toString() {
    return WxCpGsonBuilder.create().toJson(this);
  }

  /**
   * From json wx cp tag add or remove users result.
   *
   * @param json the json
   * @return the wx cp tag add or remove users result
   */
  public static WxCpUserMeetingIdResult fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpUserMeetingIdResult.class);
  }


  @SerializedName("meetingid_list")
  private String[] meetingIdList;

  @SerializedName("next_cursor")
  private String nextCursor;
}
