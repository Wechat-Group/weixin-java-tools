package me.chanjar.weixin.mp.bean.material;

import me.chanjar.weixin.common.util.ToStringUtils;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class WxMpMaterialFileBatchGetResult implements Serializable {
  private static final long serialVersionUID = -560388368297267884L;
  private int totalCount;
  private int itemCount;
  private List<WxMaterialFileBatchGetNewsItem> items;

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

  public List<WxMaterialFileBatchGetNewsItem> getItems() {
    return this.items;
  }

  public void setItems(List<WxMaterialFileBatchGetNewsItem> items) {
    this.items = items;
  }

  @Override
  public String toString() {
    return ToStringUtils.toSimpleString(this);
  }

  public static class WxMaterialFileBatchGetNewsItem {
    private String mediaId;
    private Date updateTime;
    private String name;
    private String url;

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

    public String getName() {
      return this.name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public String getUrl() {
      return this.url;
    }

    public void setUrl(String url) {
      this.url = url;
    }

    @Override
    public String toString() {
      return ToStringUtils.toSimpleString(this);
    }
  }
}
