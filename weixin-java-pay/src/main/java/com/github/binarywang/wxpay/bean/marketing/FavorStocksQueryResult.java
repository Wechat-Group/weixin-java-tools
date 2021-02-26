package com.github.binarywang.wxpay.bean.marketing;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.common.util.json.WxGsonBuilder;

import java.util.List;

/**
 * 条件查询代金券批次列表结果对象
 *
 * @author thinsstar
 */
@NoArgsConstructor
@Data
public class FavorStocksQueryResult {

  public static FavorStocksQueryResult fromJson(String json) {
    return WxGsonBuilder.create().fromJson(json, FavorStocksQueryResult.class);
  }

  /**
   * 批次总数
   * <p>
   * 经过条件筛选，查询到的批次总数量。
   * 示例值：10
   */
  @SerializedName("total_count")
  private Integer totalCount;

  /**
   * 批次详情
   * <p>
   * 批次详情
   */
  @SerializedName("data")
  private List<FavorStocksGetResult> data;

  /**
   * 分页大小
   * <p>
   * 分页大小，最大10。
   * 示例值：8
   */
  @SerializedName("limit")
  private Integer limit;

  /**
   * 分页页码
   * <p>
   * 页码从0开始，默认第0页。
   * 示例值：1
   */
  @SerializedName("offset")
  private Integer offset;
}
