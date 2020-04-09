package me.chanjar.weixin.cp.bean.oa.templatedata;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author gyv123@163.com
 */
@Data
public class TemplateOptions implements Serializable {

  private static final long serialVersionUID = -7883792668568772078L;

  private String key;

  private List<TemplateTitle> value;
}
