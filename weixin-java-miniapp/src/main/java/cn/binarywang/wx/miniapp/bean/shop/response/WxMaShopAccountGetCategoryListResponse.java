package cn.binarywang.wx.miniapp.bean.shop.response;

import cn.binarywang.wx.miniapp.bean.shop.WxMaShopAccountGetCategoryListItem;
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
public class WxMaShopAccountGetCategoryListResponse extends WxMaShopBaseResponse implements Serializable {
  private static final long serialVersionUID = -3182300077261435356L;

  @SerializedName("data")
  private List<WxMaShopAccountGetCategoryListItem> items;
}
