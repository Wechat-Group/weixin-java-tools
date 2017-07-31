package me.chanjar.weixin.mp.bean.datacube;

import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import me.chanjar.weixin.common.util.ToStringUtils;
import me.chanjar.weixin.mp.util.json.WxMpGsonBuilder;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * <pre>
 * 用户增减数据接口的返回JSON数据包
 * 详情查看文档：<a href="http://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421141082&token=&lang=zh_CN">用户分析数据接口</a>
 * </pre>
 */
public class WxDataCubeUserSummary implements Serializable {
  private static final long serialVersionUID = -2336654489906694173L;

  private static final JsonParser JSON_PARSER = new JsonParser();

  private Date refDate;

  private Integer userSource;

  private Integer newUser;

  private Integer cancelUser;

  public static List<WxDataCubeUserSummary> fromJson(String json) {
    return WxMpGsonBuilder.INSTANCE.create().fromJson(
      JSON_PARSER.parse(json).getAsJsonObject().get("list"),
      new TypeToken<List<WxDataCubeUserSummary>>() {
      }.getType());
  }

  public Date getRefDate() {
    return this.refDate;
  }

  public void setRefDate(Date refDate) {
    this.refDate = refDate;
  }

  public Integer getUserSource() {
    return this.userSource;
  }

  public void setUserSource(Integer userSource) {
    this.userSource = userSource;
  }

  public Integer getNewUser() {
    return this.newUser;
  }

  public void setNewUser(Integer newUser) {
    this.newUser = newUser;
  }

  public Integer getCancelUser() {
    return this.cancelUser;
  }

  public void setCancelUser(Integer cancelUser) {
    this.cancelUser = cancelUser;
  }

  @Override
  public String toString() {
    return ToStringUtils.toSimpleString(this);
  }
}
