package cn.binarywang.wx.miniapp.bean.delivery;

import cn.binarywang.wx.miniapp.bean.WxMaBaseResponse;
import cn.binarywang.wx.miniapp.json.WxMaGsonBuilder;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <pre>
 * 查询运单接口 query_trace 响应参数
 *
 * 商户在调用完trace_waybill接口后，可以使用本接口查询到对应运单的详情信息
 * </pre>
 *
 * @author boris
 * @since 2022-04-01
 */
@Data
@Accessors(chain = true)
public class QueryWaybillTraceResponse extends WxMaBaseResponse implements Serializable {

  private static final long serialVersionUID = 3773007367000633663L;

  /**
   * 运单信息.
   */
  @SerializedName("waybill_info")
  private WaybillInfo waybillInfo;

  /**
   * 商品信息
   */
  @SerializedName("shop_info")
  private ShopInfo shopInfo;

  /**
   * 运力信息.
   */
  @SerializedName("delivery_info")
  private DeliveryInfo deliveryInfo;


  public static QueryWaybillTraceResponse fromJson(String json) {
    return WxMaGsonBuilder.create().fromJson(json, QueryWaybillTraceResponse.class);
  }

  /**
   * 运单信息.
   */
  @Data
  @Accessors(chain = true)
  public static class WaybillInfo implements Serializable {

    private static final long serialVersionUID = -3759074878713856529L;

    /**
     * 运单状态	释义
     * <pre>
     *
     * 0	运单不存在或者未揽收
     * 1	已揽件
     * 2	运输中
     * 3	派件中
     * 4	已签收
     * 5	异常
     * 6	代签收
     *
     * </pre>
     */
    @SerializedName("status")
    private Integer status;

    /**
     * 运单号.
     */
    @SerializedName("waybill_id")
    private String waybillId;
  }

  /**
   * 商品信息.
   */
  @Data
  @Accessors(chain = true)
  public static class ShopInfo implements Serializable {

    private static final long serialVersionUID = -3759074878713856529L;

    /**
     * 配送公司Id.
     */
    @SerializedName("goods_info")
    private WaybillGoodsInfo goodsInfo;


  }


  /**
   * 运力信息.
   */
  @Data
  @Accessors(chain = true)
  public static class DeliveryInfo implements Serializable {

    private static final long serialVersionUID = -3759074878713856529L;

    /**
     * 配送公司Id.
     */
    @SerializedName("delivery_id")
    private String deliveryId;

    /**
     * 运力公司名称.
     */
    @SerializedName("delivery_name")
    private String deliveryName;

  }
}
