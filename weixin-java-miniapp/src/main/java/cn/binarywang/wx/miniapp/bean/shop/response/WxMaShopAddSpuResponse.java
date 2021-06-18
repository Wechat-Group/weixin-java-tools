package cn.binarywang.wx.miniapp.bean.shop.response;

import cn.binarywang.wx.miniapp.bean.shop.WxMaShopAddSpuResult;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import lombok.Data;

/**
 * @author leiin
 * @date 2021/3/23
 * @description:
 */
@Data
public class WxMaShopAddSpuResponse extends WxMaShopBaseResponse implements Serializable {

  private static final long serialVersionUID = 4370719678135233135L;


  @SerializedName("data")
  private WxMaShopAddSpuResult data;
}
