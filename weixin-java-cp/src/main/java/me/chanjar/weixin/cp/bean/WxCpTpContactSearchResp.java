package me.chanjar.weixin.cp.bean;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * The type Wx cp tp contact search resp.
 *
 * @author uianz
 * @since 2020 /12/23 下午 02:55
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class WxCpTpContactSearchResp extends WxCpBaseResp {

  @SerializedName("is_last")
  private Boolean isLast;

  @SerializedName("query_result")
  private QueryResult queryResult;

  /**
   * The type Query result.
   */
  @Data
  public static class QueryResult implements Serializable {
    private static final long serialVersionUID = -4301684507150486556L;

    @SerializedName("user")
    private User user;
    @SerializedName("party")
    private Party party;

    /**
     * The type User.
     */
    @Data
    public static class User implements Serializable {
      private static final long serialVersionUID = -4301684507150486556L;
      @SerializedName("userid")
      private List<String> userid;
      @SerializedName("open_userid")
      private List<String> openUserId;
    }

    /**
     * The type Party.
     */
    @Data
    public static class Party implements Serializable {
      private static final long serialVersionUID = -4301684507150486556L;

      @SerializedName("department_id")
      private List<Integer> departmentId;
    }

  }

  /**
   * From json wx cp tp contact search resp.
   *
   * @param json the json
   * @return the wx cp tp contact search resp
   */
  public static WxCpTpContactSearchResp fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpTpContactSearchResp.class);
  }

}
