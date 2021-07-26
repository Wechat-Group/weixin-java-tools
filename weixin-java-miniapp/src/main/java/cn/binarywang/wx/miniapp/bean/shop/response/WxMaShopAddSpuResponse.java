package cn.binarywang.wx.miniapp.bean.shop.response;

import cn.binarywang.wx.miniapp.bean.shop.WxMaShopAddSpuResult;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author leiin
 * @date 2021/3/23
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class WxMaShopAddSpuResponse extends WxMaShopBaseResponse implements Serializable {
  private static final long serialVersionUID = 4370719678135233135L;

  @SerializedName("data")
  private WxMaShopAddSpuResult data;
}
