package me.chanjar.weixin.mp.bean.material;

import me.chanjar.weixin.common.util.ToStringUtils;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class WxMpMaterialNewsBatchGetResult implements Serializable {
  private static final long serialVersionUID = -1617952797921001666L;

  private int totalCount;
  private int itemCount;
  private List<WxMaterialNewsBatchGetNewsItem> items;

  public int getTotalCount() {
    return this.totalCount;
  }

  public void setTotalCount(int totalCount) {
    this.totalCount = totalCount;
  }

  public int getItemCount() {
    return this.itemCount;
  }

  public void setItemCount(int itemCount) {
    this.itemCount = itemCount;
  }

  public List<WxMaterialNewsBatchGetNewsItem> getItems() {
    return this.items;
  }

  public void setItems(List<WxMaterialNewsBatchGetNewsItem> items) {
    this.items = items;
  }

  @Override
  public String toString() {
    return ToStringUtils.toSimpleString(this);
  }

  public static class WxMaterialNewsBatchGetNewsItem {
    private String mediaId;
    private Date updateTime;
    private WxMpMaterialNews content;

    public String getMediaId() {
      return this.mediaId;
    }

    public void setMediaId(String mediaId) {
      this.mediaId = mediaId;
    }

    public Date getUpdateTime() {
      return this.updateTime;
    }

    public void setUpdateTime(Date updateTime) {
      this.updateTime = updateTime;
    }

    public WxMpMaterialNews getContent() {
      return this.content;
    }

    public void setContent(WxMpMaterialNews content) {
      this.content = content;
    }

    @Override
    public String toString() {
      return ToStringUtils.toSimpleString(this);
    }
  }
}
