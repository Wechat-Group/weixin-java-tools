package cn.binarywang.wx.miniapp.bean.express.result;

import cn.binarywang.wx.miniapp.json.WxMaGsonBuilder;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * <pre>
 * 运单信息返回结果对象
 * </pre>
 * @author <a href="https://github.com/mr-xiaoyu">xiaoyu</a>
 * @since 2019-11-26
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WxMaExpressReturnInfoResult extends WxMaExpressInfoResult implements Serializable {

  private static final long serialVersionUID = -9166603059965942285L;

  /**
   * 退货ID
   */
  @SerializedName("return_id")
  private String returnId;


  /**
   * 0 用户未填写退货信息, 1. 在线预约, 2. 自主填写
   */
  private String status;


  /**
   * 运单ID
   */
  @SerializedName("waybill_id")
  private String waybillId;

  /**
   * //0›   已下单待揽件 1› 已揽件 2›   运输中 3›   派件中 4›   已签收 5›   异常 6› 代签收 7›   揽收失败 8› 签收失败（拒收，超区） 11›  已取消 13›  退件中 14›  已退件 99 未知
   */
  @SerializedName("order_status")
  private String orderStatus;

  /**
   * //运力公司名称
   */
  @SerializedName("delivery_name")
  private String deliveryName;

  /**
   * //运力公司名称
   */
  @SerializedName("delivery_id")
  private String deliveryId;



  public static WxMaExpressReturnInfoResult fromJson(String json) {
    return WxMaGsonBuilder.create().fromJson(json, WxMaExpressReturnInfoResult.class);
  }

}
