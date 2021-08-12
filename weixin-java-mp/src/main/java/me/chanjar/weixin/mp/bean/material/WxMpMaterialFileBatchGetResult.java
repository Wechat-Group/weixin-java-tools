package me.chanjar.weixin.mp.bean.material;

import lombok.Data;
import me.chanjar.weixin.mp.util.json.WxMpGsonBuilder;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author codepiano
 */
@Data
public class WxMpMaterialFileBatchGetResult implements Serializable {
  private static final long serialVersionUID = -560388368297267884L;

  private int totalCount;
  private int itemCount;
  private List<WxMaterialFileBatchGetNewsItem> items;

  @Override
  public String toString() {
    return WxMpGsonBuilder.create().toJson(this);
  }

  @Data
  public static class WxMaterialFileBatchGetNewsItem implements Serializable {
    private static final long serialVersionUID = -8300080343204117459L;

    private String mediaId;
    private Date updateTime;
    private String name;
    private String url;

    @Override
    public String toString() {
      return WxMpGsonBuilder.create().toJson(this);
    }
  }
}
