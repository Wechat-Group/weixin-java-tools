package com.github.binarywang.wxpay.bean.complaint;


import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 微信消费者投诉2.0
 * 查询投诉单列表返回的实体
 *
 * @author <a href="https://gitee.com/jeequan/jeepay">jmdhappy</a>
 * @date 2022-3-19
 */
@Data
public class ComplaintResult implements Serializable {

  private static final long serialVersionUID = -6201692411535927502L;

  /**
   * <pre>
   * 字段名：分页大小
   * 是否必填：是
   * 描述：设置该次请求返回的最大投诉条数，范围【1,50】
   * </pre>
   */
  @SerializedName("limit")
  private Integer limit;

  /**
   * <pre>
   * 字段名：分页开始位置
   * 是否必填：是
   * 描述：该次请求的分页开始位置，从0开始计数，例如offset=10，表示从第11条记录开始返回。
   * </pre>
   */
  @SerializedName("offset")
  private Integer offset;

  /**
   * <pre>
   * 字段名：投诉总条数
   * 是否必填：否
   * 描述：投诉总条数，当offset=0时返回
   * </pre>
   */
  @SerializedName("total_count")
  private Integer totalCount;

  /**
   * 用户投诉信息详情
   */
  @SerializedName("data")
  private List<ComplaintDetailResult> data;

}
