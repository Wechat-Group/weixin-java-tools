package me.chanjar.weixin.cp.bean;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * <pre>
 * 企业号应用信息.
 * Created by huansinho on 2018/4/13.
 * </pre>
 *
 * @author <a href="https://github.com/huansinho">huansinho</a>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WxCpAgent implements Serializable {
  private static final long serialVersionUID = 5002894979081127234L;

  @SerializedName("errcode")
  private Integer errCode;

  @SerializedName("errmsg")
  private String errMsg;

  @SerializedName("agentid")
  private Integer agentId;

  @SerializedName("name")
  private String name;

  @SerializedName("square_logo_url")
  private String squareLogoUrl;

  @SerializedName("logo_mediaid")
  private String logoMediaId;

  @SerializedName("description")
  private String description;

  @SerializedName("allow_userinfos")
  private Users allowUserInfos;

  @SerializedName("allow_partys")
  private Parties allowParties;

  @SerializedName("allow_tags")
  private Tags allowTags;

  @SerializedName("close")
  private Integer close;

  @SerializedName("redirect_domain")
  private String redirectDomain;

  @SerializedName("report_location_flag")
  private Integer reportLocationFlag;

  @SerializedName("isreportenter")
  private Integer isReportEnter;

  @SerializedName("home_url")
  private String homeUrl;

  public static WxCpAgent fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpAgent.class);
  }

  public String toJson() {
    return WxCpGsonBuilder.create().toJson(this);
  }

  @Data
  public static class Users implements Serializable {
    @SerializedName("user")
    private List<User> users;
  }

  @Data
  public class User implements Serializable {
    @SerializedName("userid")
    private String userId;
  }

  @Data
  public class Parties {
    @SerializedName("partyid")
    private List<Integer> partyIds = null;
  }

  @Data
  public class Tags {
    @SerializedName("tagid")
    private List<Integer> tagIds = null;
  }

}
