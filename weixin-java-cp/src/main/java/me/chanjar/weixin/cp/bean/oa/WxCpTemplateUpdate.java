package me.chanjar.weixin.cp.bean.oa;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import me.chanjar.weixin.cp.bean.oa.templatedata.TemplateContent;
import me.chanjar.weixin.cp.bean.oa.templatedata.TemplateTitle;

import java.io.Serializable;
import java.util.List;

/**
 * 更新审批模板
 *
 * @author yiyingcanfeng
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class WxCpTemplateUpdate implements Serializable {
  private static final long serialVersionUID = 8332120725354015143L;

  @SerializedName("template_id")
  private String templateId;

  @SerializedName("template_name")
  private List<TemplateTitle> templateName;

  @SerializedName("template_content")
  private TemplateContent templateContent;

}
