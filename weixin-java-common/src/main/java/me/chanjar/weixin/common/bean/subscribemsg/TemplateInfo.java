package me.chanjar.weixin.common.bean.subscribemsg;

import lombok.Data;

import java.io.Serializable;

/**
 * .
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 * @date 2021-01-27
 */
@Data
public class TemplateInfo implements Serializable {
  private static final long serialVersionUID = 6971785763573992264L;

  private String priTmplId;
  private String title;
  private String content;
  private String example;
  private int type;
}
