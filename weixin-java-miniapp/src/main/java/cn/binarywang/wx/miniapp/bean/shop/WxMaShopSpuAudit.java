package cn.binarywang.wx.miniapp.bean.shop;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import lombok.Data;

/**
 * @author leiin
 * @date 2021/3/23
 * @description:
 */
@Data
public class WxMaShopSpuAudit implements Serializable {

  private static final long serialVersionUID = -3793445161382782265L;
  /**
   * 上一次审核时间, yyyy-MM-dd HH:mm:ss
   * <pre>
   * 是否必填：
   * </pre>
   */
  @SerializedName("audit_time")
  private String auditTime;

  /**
   * 拒绝理由
   * <pre>
   * 是否必填：
   * </pre>
   */
  @SerializedName("reject_reason")
  private String rejectReason;
}
