package cn.binarywang.wx.miniapp.bean.product;

import cn.binarywang.wx.miniapp.bean.shop.response.WxMaShopBaseResponse;
import java.util.List;
import lombok.Data;

/**
 * @author leiin
 * @date 2022/7/13 20:00
 */
@Data
public class WxMinishopSkuListResponse extends WxMaShopBaseResponse {
  private List<WxMinishopSku> skus;
}
