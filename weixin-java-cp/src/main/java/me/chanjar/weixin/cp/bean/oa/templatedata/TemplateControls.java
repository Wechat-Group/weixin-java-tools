package me.chanjar.weixin.cp.bean.oa.templatedata;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Administrator
 */
@Data
public class TemplateControls implements Serializable {

  private static final long serialVersionUID = -7496794407355510374L;

  private TemplateProperty property;

  private TemplateConfig config;
}
