package me.chanjar.weixin.cp.bean;

import lombok.Data;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.io.Serializable;

/**
 * 企业微信的部门.
 *
 * @author Daniel Qian
 */
@Data
public class WxCpTpDepart implements Serializable {
  private static final long serialVersionUID = -5028321625140879571L;

  private Integer id;
  private String name;
  private String enName;
  private Integer parentid;
  private Integer order;

  /**
   * From json wx cp tp depart.
   *
   * @param json the json
   * @return the wx cp tp depart
   */
  public static WxCpTpDepart fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpTpDepart.class);
  }

  /**
   * To json string.
   *
   * @return the string
   */
  public String toJson() {
    return WxCpGsonBuilder.create().toJson(this);
  }

}
