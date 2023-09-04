package me.chanjar.weixin.cp.bean.external.interceptrule;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.common.bean.ToJson;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * 新增敏感词规则请求参数封装实体类
 *
 * @author didi  created on  2022-04-17
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WxCpInterceptRuleAddRequest implements Serializable, ToJson {
  private static final long serialVersionUID = 7161086545769110431L;

  @SerializedName("rule_name")
  private String ruleName;
  @SerializedName("rule_id")
  private String ruleId;
  @SerializedName("word_list")
  private List<String> wordList;
  @SerializedName("semantics_list")
  private List<Integer> semanticsList;
  @SerializedName("intercept_type")
  private int interceptType;
  @SerializedName("applicable_range")
  private ApplicableRange applicableRange;

  /**
   * From json wx cp intercept rule resp.
   *
   * @param json the json
   * @return the wx cp intercept rule resp
   */
  public static WxCpInterceptRuleAddRequest fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpInterceptRuleAddRequest.class);
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
