package me.chanjar.weixin.cp.bean.external.interceptrule;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import me.chanjar.weixin.cp.bean.WxCpBaseResp;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.io.Serializable;

/**
 * 新建敏感词规则负返回结果
 *
 * @author didi
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class WxCpInterceptRuleAddResult extends WxCpBaseResp implements Serializable {
  private static final long serialVersionUID = 8540187819417742703L;

  @SerializedName("rule_id")
  private String ruleId;

  /**
   * From json wx cp intercept rule result resp.
   *
   * @param json the json
   * @return the wx cp intercept rule result resp
   */
  public static WxCpInterceptRuleAddResult fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpInterceptRuleAddResult.class);
  }

  public String toJson() {
    return WxCpGsonBuilder.create().toJson(this);
  }

}
