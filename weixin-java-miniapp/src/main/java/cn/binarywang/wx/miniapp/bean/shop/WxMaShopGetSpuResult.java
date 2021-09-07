package cn.binarywang.wx.miniapp.bean.shop;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author leiin
 * @date 2021/3/23
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class WxMaShopGetSpuResult extends WxMaShopSpuInfo implements Serializable {
  private static final long serialVersionUID = -3859372286926181933L;
  /**
   * 商品审核信息
   * <pre>
   * 是否必填：
   * </pre>
   */
  @SerializedName("audit_info")
  private WxMaShopSpuAudit auditInfo;

  /**
   * 商品线上状态
   * <pre>
   * 是否必填：
   * </pre>
   */
  @SerializedName("status")
  private Integer status;

  /**
   * 商品草稿状态
   * <pre>
   * 是否必填：
   * </pre>
   */
  @SerializedName("edit_status")
  private Integer editStatus;
  /**
   * 创建时间
   * <pre>
   * 是否必填：
   * </pre>
   */
  @SerializedName("create_time")
  private String createTime;

  /**
   * 更新时间
   * <pre>
   * 是否必填：
   * </pre>
   */
  @SerializedName("update_time")
  private String updateTime;
}
