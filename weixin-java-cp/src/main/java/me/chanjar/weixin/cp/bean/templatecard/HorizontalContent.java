package me.chanjar.weixin.cp.bean.templatecard;

import com.google.gson.JsonObject;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

/**
 * 二级标题+文本列表，该字段可为空数组，但有数据的话需确认对应字段是否必填，列表长度不超过6
 *
 * @author yzts
 * @date 2021/9/22
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HorizontalContent implements Serializable {

  private static final long serialVersionUID = -2209656515382964372L;

  /**
   * 链接类型，0或不填代表不是链接，1 代表跳转url，2 代表下载附件
   */
  private Integer type;
  /**
   * 二级标题，建议不超过5个字
   */
  private String keyname;
  /**
   * 二级文本，如果horizontal_content_list.type是2，该字段代表文件名称（要包含文件类型），建议不超过30个字
   */
  private String value;
  /**
   * 链接跳转的url，horizontal_content_list.type是1时必填
   */
  private String url;
  /**
   * 附件的media_id，horizontal_content_list.type是2时必填
   */
  private String media_id;

  public JsonObject toJson() {
    JsonObject hContentJson = new JsonObject();

    if (null != this.getType()) {
      hContentJson.addProperty("type", this.getType());
    }
    hContentJson.addProperty("keyname", this.getKeyname());

    if (StringUtils.isNotBlank(this.getValue())) {
      hContentJson.addProperty("value", this.getValue());
    }
    if (StringUtils.isNotBlank(this.getUrl())) {
      hContentJson.addProperty("url", this.getUrl());
    }
    if (StringUtils.isNotBlank(this.getMedia_id())) {
      hContentJson.addProperty("media_id", this.getMedia_id());
    }
    return hContentJson;
  }

}
