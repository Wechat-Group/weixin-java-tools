package me.chanjar.weixin.cp.bean.school.user;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import me.chanjar.weixin.cp.bean.WxCpBaseResp;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * 批量返回结果.
 *
 * @author Wang_Wong  created on  2022-07-01
 */
@Data
public class WxCpBatchResultList extends WxCpBaseResp implements Serializable {
  private static final long serialVersionUID = -5028321625140879571L;

  @SerializedName("result_list")
  private List<ResultList> resultList;

  /**
   * The type Result list.
   */
  @Setter
  @Getter
  public static class ResultList extends WxCpBaseResp {

    @SerializedName("parent_userid")
    private String parentUserId;

    @SerializedName("student_userid")
    private String studentUserId;

    /**
     * From json result list.
     *
     * @param json the json
     * @return the result list
     */
    public static ResultList fromJson(String json) {
      return WxCpGsonBuilder.create().fromJson(json, ResultList.class);
    }

    public String toJson() {
      return WxCpGsonBuilder.create().toJson(this);
    }

  }

  /**
   * From json wx cp batch result list.
   *
   * @param json the json
   * @return the wx cp batch result list
   */
  public static WxCpBatchResultList fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpBatchResultList.class);
  }

  public String toJson() {
    return WxCpGsonBuilder.create().toJson(this);
  }

}
