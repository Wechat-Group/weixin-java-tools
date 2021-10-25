package me.chanjar.weixin.mp.bean.draft;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import me.chanjar.weixin.common.util.json.WxGsonBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * 草稿箱能力-获取草稿列表.
 *
 * @author dragon
 * @date 2021-10-22
 */
@Data
public class WxMpDraftList implements Serializable {
  private static final long serialVersionUID = 7216822694952035295L;

  /**
   * 草稿素材的总数
   */
  @SerializedName("total_count")
  private Integer totalCount;

  /**
   * 本次调用获取的素材的数量
   */
  @SerializedName("item_count")
  private Integer itemCount;

  /**
   * 所有草稿列表
   */
  @SerializedName("item")
  private List<WxMpDraftItem> items;

  public static WxMpDraftList fromJson(String json) {
    return WxGsonBuilder.create().fromJson(json, WxMpDraftList.class);
  }
}
