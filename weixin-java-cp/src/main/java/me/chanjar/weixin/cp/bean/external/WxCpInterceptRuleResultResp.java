package me.chanjar.weixin.cp.bean.external;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import me.chanjar.weixin.cp.bean.WxCpBaseResp;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.io.Serializable;

/**
 * 新建敏感词规则负返回结果
 * @author didi
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class WxCpInterceptRuleResultResp  extends WxCpBaseResp implements Serializable {

  @SerializedName("rule_id")
  private  String ruleId;

  public static WxCpInterceptRuleResultResp fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpInterceptRuleResultResp.class);
  }

  public String toJson() {
    return WxCpGsonBuilder.create().toJson(this);
  }

}
