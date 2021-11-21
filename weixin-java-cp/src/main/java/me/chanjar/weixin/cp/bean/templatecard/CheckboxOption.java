package me.chanjar.weixin.cp.bean.templatecard;

import com.google.gson.JsonObject;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

/**
 * 按钮列表，该字段可为空数组，但有数据的话需确认对应字段是否必填，列表长度不超过6
 * @author yzts
 * @date 2021/9/22
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CheckboxOption  implements Serializable {
  private static final long serialVersionUID = 5405702239190050250L;

  /**
   * 选项id，用户提交选项后，会产生回调事件，回调事件会带上该id值表示该选项，最长支持128字节，不可重复
   * 必填
   */
  private String id;
  /**
   * 选项文案描述，建议不超过17个字.
   * 必填
   */
  private String text;
  /**
   * 该选项是否要默认选中
   * 必填
   */
  private Boolean is_checked;

  public JsonObject toJson() {
    JsonObject optionJson = new JsonObject();
    optionJson.addProperty("id", this.getId());
    optionJson.addProperty("text", this.getText());
    if(null != this.getIs_checked()) {
      optionJson.addProperty("is_checked", this.getIs_checked());
    }
    return optionJson;
  }
}
