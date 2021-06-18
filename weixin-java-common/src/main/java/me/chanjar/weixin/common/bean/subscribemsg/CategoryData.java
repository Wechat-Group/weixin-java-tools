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
public class CategoryData implements Serializable {
  private static final long serialVersionUID = -5935548352317679892L;

  private int id;
  private String name;
}
