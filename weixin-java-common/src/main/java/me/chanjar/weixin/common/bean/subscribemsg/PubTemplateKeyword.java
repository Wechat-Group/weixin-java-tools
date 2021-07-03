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
public class PubTemplateKeyword implements Serializable {
  private static final long serialVersionUID = -1100641668859815647L;

  private int kid;
  private String name;
  private String example;
  private String rule;
}
