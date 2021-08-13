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
public class WxMaShopAuditBrandResponse extends WxMaShopBaseResponse implements Serializable {
  private static final long serialVersionUID = -4643316662725276237L;

  @SerializedName("audit_id")
  private String auditId;
}
