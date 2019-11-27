package cn.binarywang.wx.miniapp.bean.express;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;

/**
 * 商品信息对象
 * @author <a href="https://github.com/mr-xiaoyu">xiaoyu</a>
 * @since 2019-11-26
 */
@Data
public class WxMaExpressOrderShop implements Serializable {

  private static final long serialVersionUID = 7256509453502211830L;

  /**
   * 商家小程序的路径
   * <pre>
   * 是否必填： 是
   * 描述： 建议为订单页面
   * </pre>
   */
  @SerializedName("wxa_path")
  private String wxaPath;

  /**
   * 商品缩略图url
   * <pre>
   * 是否必填： 是
   * </pre>
   */
  @SerializedName("img_url")
  private String imgUrl;

  /**
   * 商品名称
   * <pre>
   * 是否必填： 是
   * </pre>
   */
  @SerializedName("goods_name")
  private String goodsName;

  /**
   * 商品数量
   * <pre>
   * 是否必填： 是
   * </pre>
   */
  @SerializedName("goods_count")
  private Integer goodsCount;

}
