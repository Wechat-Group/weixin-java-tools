package me.chanjar.weixin.mp.bean.datacube;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import lombok.Data;
import me.chanjar.weixin.common.util.json.GsonParser;
import me.chanjar.weixin.mp.util.json.WxMpGsonBuilder;

/**
 * <pre>
 * 用户增减数据接口的返回JSON数据包
 * 详情查看文档：<a href="http://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421141082&token=&lang=zh_CN">用户分析数据接口</a>
 * </pre>
 */
@Data
public class WxDataCubeUserSummary implements Serializable {
  private static final long serialVersionUID = -2336654489906694173L;



  private Date refDate;

  private Integer userSource;

  private Integer newUser;

  private Integer cancelUser;

  public static List<WxDataCubeUserSummary> fromJson(String json) {
    return WxMpGsonBuilder.create().fromJson(
      GsonParser.parse(json).get("list"),
      new TypeToken<List<WxDataCubeUserSummary>>() {
      }.getType());
  }

  @Override
  public String toString() {
    return WxMpGsonBuilder.create().toJson(this);
  }
}
