package cn.binarywang.wx.miniapp.bean.shop;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author liming1019
 * @date 2021/8/9
 */
@Data
public class WxMaShopCatGetDetail implements Serializable {
  private static final long serialVersionUID = -3404372682043466685L;

  /**
   * 类目ID
   */
  @SerializedName("third_cat_id")
  private Long thirdCatId;

  /**
   * 类目名称
   */
  @SerializedName("third_cat_name")
  private String thirdCatName;

  /**
   * 类目资质
   */
  @SerializedName("qualification")
  private String qualification;

  /**
   * 类目资质类型,0:不需要,1:必填,2:选填
   */
  @SerializedName("qualification_type")
  private Integer qualificationType;

  /**
   * 商品资质
   */
  @SerializedName("product_qualification")
  private String productQualification;

  /**
   * 商品资质类型,0:不需要,1:必填,2:选填
   */
  @SerializedName("product_qualification_type")
  private Integer productQualificationType;

  /**
   * 一级类目ID
   */
  @SerializedName("first_cat_id")
  private Long firstCatId;

  /**
   * 一级类目名称
   */
  @SerializedName("first_cat_name")
  private String firstCatName;

  /**
   * 二级类目ID
   */
  @SerializedName("second_cat_id")
  private Long secondCatId;

  /**
   * 二级类目名称
   */
  @SerializedName("second_cat_name")
  private String secondCatName;
}
