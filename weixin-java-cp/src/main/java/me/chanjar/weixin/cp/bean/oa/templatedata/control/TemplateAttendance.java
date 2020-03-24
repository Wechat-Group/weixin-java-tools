package me.chanjar.weixin.cp.bean.oa.templatedata.control;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import me.chanjar.weixin.cp.bean.oa.templatedata.TemplateDateRange;

import java.io.Serializable;
import java.util.Map;

/**
 * @author gyv12345@163.com
 */
@Data
public class TemplateAttendance implements Serializable {

  private static final long serialVersionUID = 5800412600894589065L;

  @SerializedName("date_range")
  private TemplateDateRange dateRange;

  /**
   * 假勤控件类型：1-请假，3-出差，4-外出，5-加班
   */
  private Integer type;
}
