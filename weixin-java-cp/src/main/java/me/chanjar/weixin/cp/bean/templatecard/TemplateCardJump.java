package me.chanjar.weixin.cp.bean.templatecard;

import com.google.gson.JsonObject;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

/**
 * 跳转指引样式的列表，该字段可为空数组，但有数据的话需确认对应字段是否必填，列表长度不超过3
 *
 * @author yzts
 * @date 2021/9/22
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TemplateCardJump implements Serializable {
  private static final long serialVersionUID = 4440089247405968074L;

  /**
   * 跳转链接类型，0或不填代表不是链接，1 代表跳转url，2 代表跳转小程序
   */
  private Integer type;
  /**
   * 跳转链接样式的文案内容，建议不超过18个字
   */
  private String title;
  /**
   * 跳转链接的url，jump_list.type是1时必填
   */
  private String url;
  /**
   * 跳转链接的小程序的appid，必须是与当前应用关联的小程序，jump_list.type是2时必填
   */
  private String appid;
  /**
   * 跳转链接的小程序的pagepath，jump_list.type是2时选填
   */
  private String pagepath;

  public JsonObject toJson() {
    JsonObject hContentJson = new JsonObject();

    if (null != this.getType()) {
      hContentJson.addProperty("type", this.getType());
    }
    hContentJson.addProperty("title", this.getTitle());

    if (StringUtils.isNotBlank(this.getUrl())) {
      hContentJson.addProperty("url", this.getUrl());
    }
    if (StringUtils.isNotBlank(this.getAppid())) {
      hContentJson.addProperty("appid", this.getAppid());
    }
    if (StringUtils.isNotBlank(this.getPagepath())) {
      hContentJson.addProperty("pagepath", this.getPagepath());
    }
    return hContentJson;
  }

}
