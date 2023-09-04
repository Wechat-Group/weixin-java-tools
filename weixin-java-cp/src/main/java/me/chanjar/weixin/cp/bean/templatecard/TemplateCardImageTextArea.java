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
 * @since 2022/11/4 12:12
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TemplateCardImageTextArea implements Serializable {
  private Integer type;
  private String url;
  private String title;
  private String desc;
  private String imageUrl;

  /**
   * To json json object.
   *
   * @return the json object
   */
  public JsonObject toJson() {
    JsonObject btnObject = new JsonObject();

    if (null != this.type) {
      btnObject.addProperty("type", this.type);
    }
    if (StringUtils.isNotBlank(this.url)) {
      btnObject.addProperty("url", this.url);
    }
    if (StringUtils.isNotBlank(this.title)) {
      btnObject.addProperty("title", this.title);
    }
    if (StringUtils.isNotBlank(this.desc)) {
      btnObject.addProperty("desc", this.desc);
    }
    if (StringUtils.isNotBlank(this.imageUrl)) {
      btnObject.addProperty("image_url", this.imageUrl);
    }
    return btnObject;
  }
}
