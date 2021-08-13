package cn.binarywang.wx.miniapp.bean.shop.response;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author liming1019
 * @date 2021/8/12
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class WxMaShopAuditCategoryResponse extends WxMaShopBaseResponse implements Serializable {
  private static final long serialVersionUID = -1822188134865177738L;

  @SerializedName("audit_id")
  private String auditId;
}
