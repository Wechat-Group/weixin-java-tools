package me.chanjar.weixin.cp.bean.templatecard;

import com.google.gson.JsonObject;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

/**
 * @author chenjie03
 * @version 1.0
 * @since 2022/11/4 11:57
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TemplateCardButtonSelectionOption implements Serializable {
  private String id;
  private String text;


  /**
   * To json json object.
   *
   * @return the json object
   */
  public JsonObject toJson() {
    JsonObject btnObject = new JsonObject();

    if (StringUtils.isNotBlank(this.id)) {
      btnObject.addProperty("id", this.id);
    }
    if (StringUtils.isNotBlank(this.text)) {
      btnObject.addProperty("text", this.text);
    }
    return btnObject;
  }
}
