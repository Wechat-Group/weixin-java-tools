package cn.binarywang.wx.miniapp.bean.shop.request;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author leiin
 * created on  2022/6/28 11:39 上午
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WxMaShopUploadCerficatesRequest implements Serializable {
  @SerializedName("out_aftersale_id")
  private String outAftersaleId;
  @SerializedName("aftersale_id")
  private Long aftersaleId;
  @SerializedName("refund_desc")
  private String refundDesc;
  @SerializedName("certificates")
  private List<String> certificates;
}
