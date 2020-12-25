package me.chanjar.weixin.qidian.bean.shake;

import lombok.Data;
import me.chanjar.weixin.qidian.util.json.WxMpGsonBuilder;

import java.io.Serializable;
import java.util.List;

@Data
public class WxMpShakeAroundRelationSearchResult implements Serializable {
  private Integer errcode;
  private String errmsg;
  private WxMpShakeAcoundRelationSearch data;
  public static WxMpShakeAroundRelationSearchResult fromJson(String json) {
    return WxMpGsonBuilder.create().fromJson(json, WxMpShakeAroundRelationSearchResult.class);
  }
  @Data
  public static class WxMpShakeAcoundRelationSearch implements Serializable{
    private List<WxMpDeviceIdentifier> relations;
    private Integer total_count;
  }
}
