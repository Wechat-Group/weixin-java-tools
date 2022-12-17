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
 * 创建审批模板
 *
 * @author yiyingcanfeng
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class WxCpTemplateCreate implements Serializable {
  private static final long serialVersionUID = 4918111784546859769L;

  @SerializedName("template_name")
  private List<TemplateTitle> templateName;

  @SerializedName("template_content")
  private TemplateContent templateContent;

}
