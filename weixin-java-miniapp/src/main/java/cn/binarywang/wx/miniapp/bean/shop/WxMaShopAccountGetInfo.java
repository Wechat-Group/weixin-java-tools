package cn.binarywang.wx.miniapp.bean.shop;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author liming1019
 * @date 2021/8/9
 */
@Data
public class WxMaShopAccountGetInfo implements Serializable {
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

