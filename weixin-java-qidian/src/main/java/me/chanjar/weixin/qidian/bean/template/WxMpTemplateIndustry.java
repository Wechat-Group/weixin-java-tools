package me.chanjar.weixin.qidian.bean.template;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import me.chanjar.weixin.qidian.util.json.WxMpGsonBuilder;

import java.io.Serializable;

/**
 * @author miller
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class WxMpTemplateIndustry implements Serializable {
  private static final long serialVersionUID = -7700398224795914722L;

  private WxMpTemplateIndustryEnum primaryIndustry;
  private WxMpTemplateIndustryEnum secondIndustry;

  public static WxMpTemplateIndustry fromJson(String json) {
    return WxMpGsonBuilder.create().fromJson(json, WxMpTemplateIndustry.class);
  }

  @Override
  public String toString() {
    return WxMpGsonBuilder.create().toJson(this);
  }

  public String toJson() {
    return WxMpGsonBuilder.create().toJson(this);
  }

  /**
   * 官方文档中，创建和获取的数据结构不一样。所以采用冗余字段的方式，实现相应的接口.
   */
  @Data
  @NoArgsConstructor
  @AllArgsConstructor
  @Accessors(chain = true)
  public static class Industry implements Serializable {
    private static final long serialVersionUID = -1707184885588012142L;
    private String id;
    private String firstClass;
    private String secondClass;

    public Industry(String id) {
      this.id = id;
    }

    @Override
    public String toString() {
      return WxMpGsonBuilder.create().toJson(this);
    }
  }
}
