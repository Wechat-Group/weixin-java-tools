package me.chanjar.weixin.cp.bean;

import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.io.Serializable;

/**
 * 微信部门
 *
 * @author Daniel Qian
 */
public class WxCpDepart implements Serializable {

  private static final long serialVersionUID = -5028321625140879571L;
  private Integer id;
  private String name;
  private Integer parentId;
  private Long order;

  public static WxCpDepart fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpDepart.class);
  }

  public Integer getId() {
    return this.id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getParentId() {
    return this.parentId;
  }

  public void setParentId(Integer parentId) {
    this.parentId = parentId;
  }

  public Long getOrder() {
    return this.order;
  }

  public void setOrder(Long order) {
    this.order = order;
  }

  public String toJson() {
    return WxCpGsonBuilder.create().toJson(this);
  }

  @Override
  public String toString() {
    return "WxCpDepart{" +
      "id=" + this.id +
      ", name='" + this.name + '\'' +
      ", parentId=" + this.parentId +
      ", order=" + this.order +
      '}';
  }
}
