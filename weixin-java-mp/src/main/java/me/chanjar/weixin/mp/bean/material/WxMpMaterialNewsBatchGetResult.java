package me.chanjar.weixin.mp.bean.material;

import lombok.Data;
import me.chanjar.weixin.mp.util.json.WxMpGsonBuilder;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class WxMpMaterialNewsBatchGetResult implements Serializable {
  private static final long serialVersionUID = -1617952797921001666L;

  private int totalCount;
  private int itemCount;
  private List<WxMaterialNewsBatchGetNewsItem> items;

  @Override
  public String toString() {
    return WxMpGsonBuilder.create().toJson(this);
  }

  @Data
  public static class WxMaterialNewsBatchGetNewsItem implements Serializable {
    private static final long serialVersionUID = -5227864606579602345L;

    private String mediaId;
    private Date updateTime;
    private WxMpMaterialNews content;

    @Override
    public String toString() {
      return WxMpGsonBuilder.create().toJson(this);
    }
  }
}
