package com.github.binarywang.wxpay.bean.marketing;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 查询批次可以单品结果对象
 *
 * @author thinsstar
 */
@NoArgsConstructor
@Data
public class FavorStocksItemsGetResult implements Serializable {

  private static final long serialVersionUID = 1L;

  /**
   * 可用单品编码总数
   * <p>
   * 可用单品编码总数。
   * 示例值: 200
   */
  @SerializedName("total_count")
  private Integer totalCount;

  /**
   * 可用单品编码
   * <p>
   * 可用单品编码
   * 特殊规则：单个商品编码的字符长度为【1，128】,条目个数限制为【1，50】。
   * 示例值:1232001
   */
  @SerializedName("data")
  private List<String> data;

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

  /**
   * 批次号
   * <p>
   * 批次号
   * 示例值: 9865000
   */
  @SerializedName("stock_id")
  private String stockId;
}
