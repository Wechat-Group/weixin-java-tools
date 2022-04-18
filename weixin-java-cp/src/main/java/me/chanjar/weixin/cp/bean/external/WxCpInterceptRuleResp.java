package me.chanjar.weixin.cp.bean.external;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.util.Date;
import java.util.List;

/**
 * 新增敏感词规则请求参数封装实体类
 *
 * @author didi
 * @date 2022-04-17
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WxCpInterceptRuleResp {

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

  @Data
  public static class ApplicableRange {
    @SerializedName("user_list")
    private List<String> userList;
    @SerializedName("department_list")
    private List<Integer> departmentList;
    public static ApplicableRange fromJson(String json) {
      return WxCpGsonBuilder.create().fromJson(json, ApplicableRange.class);
    }

    public String toJson() {
      return WxCpGsonBuilder.create().toJson(this);
    }
  }

  public static WxCpInterceptRuleResp fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpInterceptRuleResp.class);
  }

  public String toJson() {
    return WxCpGsonBuilder.create().toJson(this);
  }

}
