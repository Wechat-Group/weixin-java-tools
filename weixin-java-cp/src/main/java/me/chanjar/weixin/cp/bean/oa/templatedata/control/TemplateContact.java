package me.chanjar.weixin.cp.bean.oa.templatedata.control;

import lombok.Data;

import java.io.Serializable;

/**
 * @author gyv12345@163.com
 */
@Data
public class TemplateContact implements Serializable {

  private static final long serialVersionUID = -7840088884653172851L;
  /**
   * 选择方式：single-单选；multi-多选
   */
  private String type;
  /**
   * 选择对象：user-成员；department-部门
   */
  private String mode;
}
