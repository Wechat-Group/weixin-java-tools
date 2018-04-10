package me.chanjar.weixin.mp.bean.template;


import lombok.Data;
import me.chanjar.weixin.common.util.ToStringUtils;
import me.chanjar.weixin.mp.util.json.WxMpGsonBuilder;

import java.io.Serializable;

/**
 * @author miller
 */
@Data
public class WxMpTemplateIndustry implements Serializable {
  private static final long serialVersionUID = -7700398224795914722L;

  private Industry primaryIndustry;
  private Industry secondIndustry;

  public WxMpTemplateIndustry() {
  }

  public WxMpTemplateIndustry(Industry primaryIndustry, Industry secondIndustry) {
    this.primaryIndustry = primaryIndustry;
    this.secondIndustry = secondIndustry;
  }

  public static WxMpTemplateIndustry fromJson(String json) {
    return WxMpGsonBuilder.create().fromJson(json, WxMpTemplateIndustry.class);
  }

  @Override
  public String toString() {
    return ToStringUtils.toSimpleString(this);
  }

  public String toJson() {
    return WxMpGsonBuilder.create().toJson(this);
  }

  /**
   * @author miller
   *         官方文档中，创建和获取的数据结构不一样。所以采用冗余字段的方式，实现相应的接口
   */
  @Data
  public static class Industry implements Serializable {
    private static final long serialVersionUID = -1707184885588012142L;
    private String id;
    private String firstClass;
    private String secondClass;

    public Industry() {
    }

    public Industry(String id) {
      this.id = id;
    }

    public Industry(String id, String firstClass, String secondClass) {
      this.id = id;
      this.firstClass = firstClass;
      this.secondClass = secondClass;
    }

    @Override
    public String toString() {
      return ToStringUtils.toSimpleString(this);
    }

  }
}
