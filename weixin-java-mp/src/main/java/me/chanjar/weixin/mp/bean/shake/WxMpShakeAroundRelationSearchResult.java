package me.chanjar.weixin.mp.bean.shake;

import lombok.Data;
import me.chanjar.weixin.mp.util.json.WxMpGsonBuilder;

import java.io.Serializable;
import java.util.List;

@Data
public class WxMpShakeAroundRelationSearchResult implements Serializable {
  private Integer errcode;
  private String errmsg;
  public static WxMpShakeAroundRelationSearchResult fromJson(String json) {
    return WxMpGsonBuilder.INSTANCE.create().fromJson(json, WxMpShakeAroundRelationSearchResult.class);
  }
  @Data
  public static class WxMpShakeAcoundRelationSearch implements Serializable{
    private List<WxMpDeviceIdentifier> relations;
    private Integer total_count;
  }
}
