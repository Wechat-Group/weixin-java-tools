package me.chanjar.weixin.mp.bean.material;

import lombok.Data;
import me.chanjar.weixin.common.util.ToStringUtils;

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
    return ToStringUtils.toSimpleString(this);
  }

  @Data
  public static class WxMaterialNewsBatchGetNewsItem {
    private String mediaId;
    private Date updateTime;
    private WxMpMaterialNews content;

    @Override
    public String toString() {
      return ToStringUtils.toSimpleString(this);
    }
  }
}
