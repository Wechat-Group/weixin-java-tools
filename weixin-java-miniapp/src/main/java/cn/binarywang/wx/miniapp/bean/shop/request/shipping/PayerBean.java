package cn.binarywang.wx.miniapp.bean.shop.request.shipping;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author xzh
 * @Description
 * @createTime 2023/07/10 10:38
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PayerBean implements Serializable {

  private static final long serialVersionUID = 6628077253606871512L;
  /**
   * 必填
   * 用户标识，用户在小程序appid下的唯一标识。 下单前需获取到用户的Openid 示例值: oUpF8uMuAJO_M2pxb1Q9zNjWeS6o 字符字节限制: [1, 128]
   */
  @SerializedName("openid")
  private String openid;
}
