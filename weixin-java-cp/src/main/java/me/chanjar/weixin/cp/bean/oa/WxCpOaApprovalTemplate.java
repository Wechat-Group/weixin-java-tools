package me.chanjar.weixin.cp.bean.oa;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import me.chanjar.weixin.cp.bean.oa.templatedata.TemplateContent;
import me.chanjar.weixin.cp.bean.oa.templatedata.TemplateTitle;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * 新增/更新审批模板的请求对象
 *
 * @author yiyingcanfeng
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class WxCpOaApprovalTemplate implements Serializable {
  private static final long serialVersionUID = 8332120725354015143L;

  /**
   * 仅更新审批模版时需要提供
   */
  @SerializedName("template_id")
  private String templateId;

  @SerializedName("template_name")
  private List<TemplateTitle> templateName;

  @SerializedName("template_content")
  private TemplateContent templateContent;

  public static WxCpOaApprovalTemplate fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpOaApprovalTemplate.class);
  }

  public String toJson() {
    return WxCpGsonBuilder.create().toJson(this);
  }

}
