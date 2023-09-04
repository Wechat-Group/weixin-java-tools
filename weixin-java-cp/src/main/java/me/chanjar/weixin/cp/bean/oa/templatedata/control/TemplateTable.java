package me.chanjar.weixin.cp.bean.oa.templatedata.control;

import lombok.Data;
import me.chanjar.weixin.cp.bean.oa.templatedata.TemplateControls;

import java.io.Serializable;
import java.util.List;

/**
 * The type Template table.
 *
 * @author gyv12345 @163.com
 */
@Data
public class TemplateTable implements Serializable {
  private static final long serialVersionUID = -8181588935694605858L;

  private List<TemplateControls> children;

  private String[] statField;

}
