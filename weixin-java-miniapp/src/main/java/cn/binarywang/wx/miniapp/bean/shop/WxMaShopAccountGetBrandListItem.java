package cn.binarywang.wx.miniapp.bean.shop;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author liming1019
 * @date 2021/8/9
 */
@Data
public class WxMaShopAccountGetBrandListItem implements Serializable {
  private static final long serialVersionUID = -8889271375365538573L;

  /**
   * 品牌ID
   */
  @SerializedName("brand_id")
  private Long brandId;

  /**
   * 品牌名称
   */
  @SerializedName("brand_wording")
  private String brandWording;
}

