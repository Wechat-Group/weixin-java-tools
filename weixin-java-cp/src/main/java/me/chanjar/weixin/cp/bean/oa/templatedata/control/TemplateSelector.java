package me.chanjar.weixin.cp.bean.oa.templatedata.control;

import lombok.Data;
import me.chanjar.weixin.cp.bean.oa.templatedata.TemplateOptions;

import java.io.Serializable;
import java.util.List;

/**
 * The type Template selector.
 *
 * @author gyv12345 @163.com
 */
@Data
public class TemplateSelector implements Serializable {
  private static final long serialVersionUID = 4995408101489736881L;
  /**
   * single-单选；multi-多选
   */
  private String type;

  private List<TemplateOptions> options;
}
