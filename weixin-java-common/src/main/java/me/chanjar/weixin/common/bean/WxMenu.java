package me.chanjar.weixin.common.bean;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.Charsets;

import me.chanjar.weixin.common.util.json.WxGsonBuilder;

/**
 * 企业号菜单
 * @author Daniel Qian
 *
 */
public class WxMenu implements Serializable {

  private List<WxMenuButton> buttons = new ArrayList<WxMenuButton>();

  private WxMenuRule matchRule;
  
  public List<WxMenuButton> getButtons() {
    return buttons;
  }

  public void setButtons(List<WxMenuButton> buttons) {
    this.buttons = buttons;
  }
  
  public WxMenuRule getMatchRule() {
    return matchRule;
  }
  
  public void setMatchRule(WxMenuRule matchRule) {
    this.matchRule = matchRule;
  }
  
  public String toJson() {
    return WxGsonBuilder.create().toJson(this);
  }

  /**
   * 要用 http://mp.weixin.qq.com/wiki/16/ff9b7b85220e1396ffa16794a9d95adc.html 格式来反序列化
   * 相比 http://mp.weixin.qq.com/wiki/13/43de8269be54a0a6f64413e4dfa94f39.html 的格式，外层多套了一个menu
   * @param json
   * @return
   */
  public static WxMenu fromJson(String json) {
    return WxGsonBuilder.create().fromJson(json, WxMenu.class);
  }

  /**
   * 要用 http://mp.weixin.qq.com/wiki/16/ff9b7b85220e1396ffa16794a9d95adc.html 格式来反序列化
   * 相比 http://mp.weixin.qq.com/wiki/13/43de8269be54a0a6f64413e4dfa94f39.html 的格式，外层多套了一个menu
   * @param is
   * @return
   */
  public static WxMenu fromJson(InputStream is) {
    return WxGsonBuilder.create().fromJson(new InputStreamReader(is, Charsets.UTF_8), WxMenu.class);
  }

  @Override
  public String toString() {
    return "WxMenu{" +
        "buttons=" + buttons +
        '}';
  }

  public static class WxMenuButton {

    private String type;
    private String name;
    private String key;
    private String url;
    
    private List<WxMenuButton> subButtons = new ArrayList<WxMenuButton>();

    public String getType() {
      return type;
    }

    public void setType(String type) {
      this.type = type;
    }

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public String getKey() {
      return key;
    }

    public void setKey(String key) {
      this.key = key;
    }

    public String getUrl() {
      return url;
    }

    public void setUrl(String url) {
      this.url = url;
    }

    public List<WxMenuButton> getSubButtons() {
      return subButtons;
    }

    public void setSubButtons(List<WxMenuButton> subButtons) {
      this.subButtons = subButtons;
    }

    @Override
    public String toString() {
      return "WxMenuButton{" +
          "type='" + type + '\'' +
          ", name='" + name + '\'' +
          ", key='" + key + '\'' +
          ", url='" + url + '\'' +
          ", subButtons=" + subButtons +
          '}';
    }
  }
  
  public static class WxMenuRule {
    private String groupId;
    private String sex;
    private String country;
    private String province;
    private String city;
    private String clientPlatformType;
    
    public String getGroupId() {
      return groupId;
    }
	
    public void setGroupId(String groupId) {
      this.groupId = groupId;
    }
	
    public String getSex() {
      return sex;
    }
	
    public void setSex(String sex) {
      this.sex = sex;
    }
	
    public String getCountry() {
      return country;
    }
	
    public void setCountry(String country) {
      this.country = country;
    }
	
    public String getProvince() {
      return province;
    }
	
    public void setProvince(String province) {
      this.province = province;
    }
	
    public String getCity() {
      return city;
    }
	
    public void setCity(String city) {
      this.city = city;
    }
	
    public String getClientPlatformType() {
      return clientPlatformType;
    }
	
    public void setClientPlatformType(String clientPlatformType) {
      this.clientPlatformType = clientPlatformType;
    }
	
    @Override
    public String toString() {
      return "matchrule:{" +
          "group_id='" + groupId + '\'' +
          ", sex='" + sex + '\'' +
          ", country" + country + '\'' +
          ", province" + province + '\'' +
          ", city" + city + '\'' +
          ", client_platform_type" + clientPlatformType + '\'' +
          "}";
    }
  }
	
}
