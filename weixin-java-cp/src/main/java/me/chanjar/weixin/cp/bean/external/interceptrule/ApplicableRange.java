package me.chanjar.weixin.cp.bean.external.interceptrule;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * The type Applicable range.
 *
 * @author didi
 */
@Data
public class ApplicableRange implements Serializable {
  private static final long serialVersionUID = -2473660177998792887L;

  @SerializedName("user_list")
  private List<String> userList;
  @SerializedName("department_list")
  private List<Integer> departmentList;

  /**
   * From json applicable range.
   *
   * @param json the json
   * @return the applicable range
   */
  public static ApplicableRange fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, ApplicableRange.class);
  }

  /**
   * To json string.
   *
   * @return the string
   */
  public String toJson() {
    return WxCpGsonBuilder.create().toJson(this);
  }
}
