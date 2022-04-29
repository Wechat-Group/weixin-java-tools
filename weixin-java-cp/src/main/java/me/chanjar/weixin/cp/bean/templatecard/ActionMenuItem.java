package me.chanjar.weixin.cp.bean.templatecard;

import com.google.gson.JsonObject;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 卡片右上角更多操作按钮点击后出现的操作列表，列表长度取值范围为 [1, 3]
 * @author xiaohe
 * @date 2022-03-06
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ActionMenuItem implements Serializable {
  private static final long serialVersionUID = 400885585614100693L;

  /**
   * 操作的描述文案
   */
  private String text;

  /**
   * 按钮key值，用户点击后，会产生回调事件将本参数作为EventKey返回，回调事件会带上该key值，最长支持1024字节，不可重复
   */
  private String key;

  public JsonObject toJson() {
    JsonObject btnObject = new JsonObject();
    btnObject.addProperty("text", this.getText());
    btnObject.addProperty("key", this.getKey());
    return btnObject;
  }

}
