package me.chanjar.weixin.open.bean.result;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 获取小程序已经设置的类目结果类.
 *
 * @author Hipple
 * @since 2019/1/26 18:27
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class WxFastMaBeenSetCategoryResult extends WxOpenResult {
  private static final long serialVersionUID = -7662344448437700644L;

  /**
   * 一个更改周期内可以设置类目的次数
   */
  @SerializedName("limit")
  private int limit;
  /**
   * 本更改周期内还可以设置类目的次数
   */
  @SerializedName("quota")
  private int quota;
  /**
   * 最多可以设置的类目数量
   */
  @SerializedName("category_limit")
  private int categoryLimit;
  /**
   * 类目
   */
  @SerializedName("categories")
  private List<CategoriesBean> categories;

  @Data
  public static class CategoriesBean {
    /**
     * 一级类目ID
     */
    @SerializedName("first")
    private int first;
    /**
     * 一级类目名称
     */
    @SerializedName("first_name")
    private String firstName;
    /**
     * 二级类目ID
     */
    @SerializedName("second")
    private int second;
    /**
     * 二级类目名称
     */
    @SerializedName("second_name")
    private String secondName;
    /**
     * 审核状态（1审核中 2审核不通过 3审核通过）
     */
    @SerializedName("audit_status")
    private int auditStatus;
    /**
     * 审核不通过原因
     */
    @SerializedName("audit_reason")
    private String auditReason;
  }
}
