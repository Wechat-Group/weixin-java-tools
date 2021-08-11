package cn.binarywang.wx.miniapp.bean.shop.response;

import cn.binarywang.wx.miniapp.bean.shop.WxMaShopAccountGetInfo;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author liming1019
 * @date 2021/8/9
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class WxMaShopAccountGetInfoResponse extends WxMaShopBaseResponse implements Serializable {
  private static final long serialVersionUID = -3954383181691898592L;

  @SerializedName("data")
  private WxMaShopAccountGetInfo data;
}
