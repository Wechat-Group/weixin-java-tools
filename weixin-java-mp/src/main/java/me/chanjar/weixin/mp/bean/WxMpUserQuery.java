package me.chanjar.weixin.mp.bean;

import com.google.gson.Gson;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 批量查询用户信息查询参数 <br>
 * Created by LiuJunGuang on 2016/8/31.
 *
 * @author LiuJunGuang
 */
public class WxMpUserQuery {
  private List<WxMpUserQueryParam> queryParamList = new ArrayList<>();

  public WxMpUserQuery() {
    super();
  }

  /**
   * 语言使用默认(zh_CN)
   *
   * @param openids
   */
  public WxMpUserQuery(List<String> openids) {
    super();
    add(openids);
  }

  /**
   * 添加OpenId列表，语言使用默认(zh_CN)
   *
   * @param openids
   * @return {@link WxMpUserQuery}
   */
  public WxMpUserQuery add(List<String> openids) {
    for (String openid : openids) {
      this.add(openid);
    }
    return this;
  }

  /**
   * 添加一个OpenId
   *
   * @param openid
   * @param lang   国家地区语言版本，zh_CN 简体，zh_TW 繁体，en 英语
   * @return {@link WxMpUserQuery}
   */
  public WxMpUserQuery add(String openid, String lang) {
    this.queryParamList.add(new WxMpUserQueryParam(openid, lang));
    return this;
  }

  /**
   * 添加一个OpenId到列表中，并返回本对象
   * <p>
   * <pre>
   * 该方法默认lang = zh_CN
   * </pre>
   *
   * @param openid
   * @return {@link WxMpUserQuery}
   */
  public WxMpUserQuery add(String openid) {
    this.queryParamList.add(new WxMpUserQueryParam(openid));
    return this;
  }

  /**
   * 删除指定的OpenId，语言使用默认(zh_CN)
   *
   * @param openid
   * @return {@link WxMpUserQuery}
   */
  public WxMpUserQuery remove(String openid) {
    this.queryParamList.remove(new WxMpUserQueryParam(openid));
    return this;
  }

  /**
   * 删除指定的OpenId
   *
   * @param openid
   * @param lang   国家地区语言版本，zh_CN 简体，zh_TW 繁体，en 英语
   * @return {@link WxMpUserQuery}
   */
  public WxMpUserQuery remove(String openid, String lang) {
    this.queryParamList.remove(new WxMpUserQueryParam(openid, lang));
    return this;
  }

  /**
   * 获取查询参数列表
   */
  public List<WxMpUserQueryParam> getQueryParamList() {
    return this.queryParamList;
  }

  public String toJsonString() {
    Map<String, Object> map = new HashMap<>();
    map.put("user_list", this.queryParamList);
    return new Gson().toJson(map);
  }

  // 查询参数封装
  public class WxMpUserQueryParam implements Serializable {
    private static final long serialVersionUID = -6863571795702385319L;
    private String openid;
    private String lang;

    public WxMpUserQueryParam(String openid, String lang) {
      super();
      this.openid = openid;
      this.lang = lang;
    }

    public WxMpUserQueryParam(String openid) {
      super();
      this.openid = openid;
      this.lang = "zh_CN";
    }

    public WxMpUserQueryParam() {
      super();
    }

    public String getOpenid() {
      return this.openid;
    }

    public void setOpenid(String openid) {
      this.openid = openid;
    }

    public String getLang() {
      return this.lang;
    }

    public void setLang(String lang) {
      this.lang = lang;
    }

    @Override
    public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + getOuterType().hashCode();
      result = prime * result + ((this.lang == null) ? 0 : this.lang.hashCode());
      result = prime * result + ((this.openid == null) ? 0 : this.openid.hashCode());
      return result;
    }

    @Override
    public boolean equals(Object obj) {
      if (this == obj)
        return true;
      if (obj == null)
        return false;
      if (getClass() != obj.getClass())
        return false;
      WxMpUserQueryParam other = (WxMpUserQueryParam) obj;
      if (!getOuterType().equals(other.getOuterType()))
        return false;
      if (this.lang == null) {
        if (other.lang != null)
          return false;
      } else if (!this.lang.equals(other.lang))
        return false;
      if (this.openid == null) {
        if (other.openid != null)
          return false;
      } else if (!this.openid.equals(other.openid))
        return false;
      return true;
    }

    private WxMpUserQuery getOuterType() {
      return WxMpUserQuery.this;
    }

  }

}
