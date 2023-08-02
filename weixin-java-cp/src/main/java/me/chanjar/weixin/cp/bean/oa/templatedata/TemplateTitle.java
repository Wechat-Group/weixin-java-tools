package me.chanjar.weixin.cp.bean.oa.templatedata;

import lombok.Data;

import java.io.Serializable;

/**
 * The type Template title.
 *
 * @author gyv12345 @163.com
 */
@Data
public class TemplateTitle implements Serializable {
  private static final long serialVersionUID = -3229779834737051398L;

  private String text;

  private String lang;
}
