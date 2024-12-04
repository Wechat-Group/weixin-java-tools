package cn.binarywang.wx.miniapp.bean.delivery;


import cn.binarywang.wx.miniapp.json.WxMaGsonBuilder;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <pre>
 * 更新物流信息接口 update_waybill_goods
 * </pre>
 *
 * @author zhongjun
 * @since 2024-03-14
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class UpdateWaybillGoodsRequest implements Serializable {

  private static final long serialVersionUID = -8817584588925001295L;



  /**
   * 查询id
   * <pre>
   * 是否必填： 是
   * </pre>
   */
  @SerializedName("waybill_token")
  private String waybillToken;

  /**
   * 商品信息
   * <pre>
   * 是否必填： 是
   * </pre>
   */
  @SerializedName("goods_info")
  private WaybillGoodsInfo goodsInfo;

  public String toJson() {
    return WxMaGsonBuilder.create().toJson(this);
  }
}
