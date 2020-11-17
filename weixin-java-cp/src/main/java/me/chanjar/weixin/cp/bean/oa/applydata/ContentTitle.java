package me.chanjar.weixin.cp.bean.oa.applydata;

import lombok.Data;

import java.io.Serializable;

/**
 * @author element
 */
@Data
public class ContentTitle implements Serializable {

  private static final long serialVersionUID = -4501999157383517007L;

  private String text;
  private String lang;

}
