package me.chanjar.weixin.mp.bean.freepublish;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import me.chanjar.weixin.common.util.json.WxGsonBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * 发布能力-获取成功发布列表.
 *
 * @author dragon
 * @date 2021-10-22
 */
@Data
public class WxMpFreePublishList implements Serializable {
  private static final long serialVersionUID = 764054773431665250L;

  /**
   * 成功发布素材的总数
   */
  @SerializedName("total_count")
  private Integer totalCount;

  /**
   * 本次调用获取的素材的数量
   */
  @SerializedName("item_count")
  private Integer itemCount;

  /**
   * 所有成功发布列表
   */
  @SerializedName("item")
  private List<WxMpFreePublishItem> items;

  public static WxMpFreePublishList fromJson(String json) {
    return WxGsonBuilder.create().fromJson(json, WxMpFreePublishList.class);
  }
}
