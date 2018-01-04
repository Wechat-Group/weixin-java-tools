package me.chanjar.weixin.cp.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 微信用户信息.
 *
 * @author Daniel Qian
 */
@Data
public class WxCpUser implements Serializable {
  public enum Gender {
    /**
     * 男
     */
    MALE("男", "1"),
    /**
     * 女
     */
    FEMALE("女", "2");

    private String genderName;
    private String code;

    Gender(String genderName, String code) {
      this.genderName = genderName;
      this.code = code;
    }

    public String getGenderName() {
      return this.genderName;
    }

    public String getCode() {
      return this.code;
    }

    public static Gender fromCode(String code) {
      if ("1".equals(code)) {
        return Gender.MALE;
      }
      if ("2".equals(code)) {
        return Gender.FEMALE;
      }

      return null;
    }
  }

  private static final long serialVersionUID = -5696099236344075582L;
  private String userId;
  private String name;
  private Integer[] departIds;
  private String position;
  private String mobile;
  private Gender gender;
  private String email;
  private String avatar;
  private Integer status;
  private Integer enable;
  private Integer isLeader;
  private final List<Attr> extAttrs = new ArrayList<>();
  private Integer hideMobile;
  private String englishName;
  private String telephone;

  public void addExtAttr(String name, String value) {
    this.extAttrs.add(new Attr(name, value));
  }

  public static WxCpUser fromJson(String json) {
    return WxCpGsonBuilder.INSTANCE.create().fromJson(json, WxCpUser.class);
  }

  public String toJson() {
    return WxCpGsonBuilder.INSTANCE.create().toJson(this);
  }

  @Data
  @AllArgsConstructor
  public static class Attr {
    private String name;
    private String value;
  }

}
