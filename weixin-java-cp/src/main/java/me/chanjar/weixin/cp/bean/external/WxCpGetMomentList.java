package me.chanjar.weixin.cp.bean.external;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import me.chanjar.weixin.cp.bean.WxCpBaseResp;
import me.chanjar.weixin.cp.bean.external.moment.MomentInfo;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.util.List;

/**
 * 企业发表内容到客户的朋友圈 获取企业全部的发表列表
 *
 * @author leiin
 * @date 2021-10-29
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class WxCpGetMomentList extends WxCpBaseResp {
  private static final long serialVersionUID = 106159971765109008L;

  @SerializedName("next_cursor")
  private String nextCursor;
  @SerializedName("moment_list")
  private List<MomentInfo> momentList;

  public static WxCpGetMomentList fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpGetMomentList.class);
  }

  public String toJson() {
    return WxCpGsonBuilder.create().toJson(this);
  }
}
