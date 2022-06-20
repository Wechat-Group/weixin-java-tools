package cn.binarywang.wx.miniapp.bean.product;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import lombok.Data;

/**
 * 店铺的商品分类
 */
@Data
public class MinishopShopCat implements Serializable {
  private static final long serialVersionUID = 4179473856929659641L;

  @SerializedName("cat_id")
  private Integer shopCatId;

  private String shopCatName;

  private Integer fShopCatId;

  @SerializedName("level")
  private Integer catLevel;
}
