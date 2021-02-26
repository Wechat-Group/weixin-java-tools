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
public class FavorCouponsQueryResult {

  public static FavorCouponsQueryResult fromJson(String json) {
    return WxGsonBuilder.create().fromJson(json, FavorCouponsQueryResult.class);
  }

  /**
   * 查询结果总数
   * <p>
   * 查询结果总数
   * 示例值：100
   */
  @SerializedName("total_count")
  private Integer totalCount;

  /**
   * 批次详情
   * <p>
   * 批次详情
   */
  @SerializedName("data")
  private List<FavorCouponsGetResult> data;

  /**
   * 分页大小
   * <p>
   * 分页大小
   * 示例值：10
   */
  @SerializedName("limit")
  private Integer limit;

  /**
   * 分页页码
   * <p>
   * 分页页码
   * 示例值：10
   */
  @SerializedName("offset")
  private Integer offset;
}
