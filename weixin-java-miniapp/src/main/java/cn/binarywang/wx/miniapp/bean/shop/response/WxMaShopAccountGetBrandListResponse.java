package cn.binarywang.wx.miniapp.bean.shop.response;

import cn.binarywang.wx.miniapp.bean.shop.WxMaShopAccountGetBrandListItem;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

/**
 * @author liming1019
 * @date 2021/8/9
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class WxMaShopAccountGetBrandListResponse extends WxMaShopBaseResponse implements Serializable {
  private static final long serialVersionUID = -5196210913054514206L;

  @SerializedName("data")
  private List<WxMaShopAccountGetBrandListItem> items;
}
