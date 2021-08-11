package cn.binarywang.wx.miniapp.bean.shop;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author liming1019
 * @date 2021/8/9
 */
@Data
public class WxMaShopAccountGetCategoryListItem implements Serializable {
  private static final long serialVersionUID = -6574489801942310752L;

  /**
   * 一级类目ID
   */
  @SerializedName("first_cat_id")
  private Long firstCatId;
  /**
   * 二级类目ID
   */
  @SerializedName("second_cat_id")
  private Long secondCatId;
  /**
   * 类目ID
   */
  @SerializedName("third_cat_id")
  private Long thirdCatId;
  /**
   * 一级类目名称
   */
  @SerializedName("first_cat_name")
  private String firstCatName;
  /**
   * 二级类目名称
   */
  @SerializedName("second_cat_name")
  private String secondCatName;

  /**
   * 类目名称
   */
  @SerializedName("third_cat_name")
  private String thirdCatName;
}

