package me.chanjar.weixin.cp.bean.oa.templatedata.control;

import lombok.Data;
import me.chanjar.weixin.cp.bean.oa.templatedata.TemplateVacationItem;

import java.io.Serializable;
import java.util.List;

/**
 * The type Template vacation.
 *
 * @author gyv12345 @163.com
 */
@Data
public class TemplateVacation implements Serializable {
  private static final long serialVersionUID = 3442297114957906890L;

  private List<TemplateVacationItem> item;

}
