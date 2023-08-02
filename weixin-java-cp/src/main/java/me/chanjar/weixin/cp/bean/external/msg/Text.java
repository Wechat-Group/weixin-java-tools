package me.chanjar.weixin.cp.bean.external.msg;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 消息文本消息.
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a> created on  2020-08-16
 */
@Data
@Accessors(chain = true)
public class Text implements Serializable {
  private static final long serialVersionUID = 6608288753719551600L;
  private String content;
}
