package me.chanjar.weixin.cp.bean.templatecard;

import com.google.gson.JsonObject;
import kotlin.text.UStringsKt;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

/**
 * 卡片二级垂直内容，该字段可为空数组，但有数据的话需确认对应字段是否必填，列表长度不超过4
 * @author yzts
 * @date 2021/9/22
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VerticalContent implements Serializable {
  private static final long serialVersionUID = -1383852553854573558L;

  /**
   * 卡片二级标题，建议不超过38个字.必填字段
   */
  private String title;
  /**
   * 二级普通文本，建议不超过160个字
   */
  private String desc;

  public JsonObject toJson() {
    JsonObject vContentJson = new JsonObject();

    vContentJson.addProperty("title", this.getTitle());

    if (StringUtils.isNotBlank(this.getDesc())) {
      vContentJson.addProperty("desc", this.getDesc());
    }
    return vContentJson;
  }
}
