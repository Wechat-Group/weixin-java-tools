package cn.binarywang.wx.miniapp.bean.delivery;

import cn.binarywang.wx.miniapp.json.WxMaGsonBuilder;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 运单商品信息
 *
 * @author boris
 * @since 2022-04-01
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WaybillGoodsInfo implements Serializable {

  private static final long serialVersionUID = 5643624677715536605L;



  /**
   * 商品信息
   */
  @SerializedName("detail_list")
  private List<GoodsItem> goodsItemList;

  public static WaybillGoodsInfo fromJson(String json) {
    return WxMaGsonBuilder.create().fromJson(json, WaybillGoodsInfo.class);
  }

  @Data
  public static class GoodsItem {

    /**
     * 商品名称
     * <pre>
     * 是否必填： 是
     * </pre>
     */
    @SerializedName("goods_name")
    private String goodsName;

    /**
     * 商品图片URL
     * <pre>
     * 是否必填： 是
     * </pre>
     */
    @SerializedName("goods_img_url")
    private String goodsImgUrl;


  }
}
