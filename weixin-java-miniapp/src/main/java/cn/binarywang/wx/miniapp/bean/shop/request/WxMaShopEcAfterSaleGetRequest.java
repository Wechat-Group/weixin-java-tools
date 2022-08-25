package cn.binarywang.wx.miniapp.bean.shop.request;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author liming1019
 * created on  2022/8/25
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WxMaShopEcAfterSaleGetRequest implements Serializable {

  private static final long serialVersionUID = -1967384908983649001L;

  @SerializedName("aftersale_id")
  private Long aftersaleId;
  @SerializedName("out_aftersale_id")
  private String outAftersaleId;
}
