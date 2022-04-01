package me.chanjar.weixin.cp.bean.external;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import me.chanjar.weixin.cp.bean.WxCpBaseResp;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.util.List;

/**
 * 企业发表内容到客户的朋友圈 获取客户朋友圈的互动数据
 *
 * @author leiin
 * @date 2021-10-29
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class WxCpGetMomentComments extends WxCpBaseResp {
  private static final long serialVersionUID = -9056664072546234965L;

  @SerializedName("comment_list")
  private List<CommentLikeItem> commentList;
  @SerializedName("like_list")
  private List<CommentLikeItem> likeList;

  @Getter
  @Setter
  public static class CommentLikeItem {
    @SerializedName("external_userid")
    private String externalUserId;
    @SerializedName("userid")
    private String userid;
    @SerializedName("create_time")
    private Long createTime;
  }

  public static WxCpGetMomentComments fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpGetMomentComments.class);
  }

  public String toJson() {
    return WxCpGsonBuilder.create().toJson(this);
  }
}
