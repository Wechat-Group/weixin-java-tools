package me.chanjar.weixin.cp.bean.external.msg;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 消息文本消息.
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 * @date 2020-08-16
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Text implements Serializable {
  private static final long serialVersionUID = 6608288753719551600L;

  @SerializedName("content")
  private String content;
}
