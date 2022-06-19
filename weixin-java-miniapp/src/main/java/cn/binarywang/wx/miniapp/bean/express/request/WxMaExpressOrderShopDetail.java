package cn.binarywang.wx.miniapp.bean.express.request;


import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


/**
 * 商品详情
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WxMaExpressOrderShopDetail implements Serializable {

  private static final long serialVersionUID = 5988620921216969796L;

  /**
   * 商品名称
   * <pre>
   * 是否必填： 否
   * 描述： 最多40汉字
   * </pre>
   */
  @SerializedName("goods_name")
  private String goodsName;

  /**
   * 商品图片url
   * <pre>
   * 是否必填： 否
   * </pre>
   */
  @SerializedName("goods_img_url")
  private String goodsImgUrl;

  /**
   * 商品详情描述
   * <pre>
   * 是否必填： 否
   * 描述： 最多40汉字
   * </pre>
   */
  @SerializedName("goods_desc")
  private String goodsDesc;

}
