package cn.binarywang.wx.miniapp.bean.shop.response;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import lombok.Data;

/**
 * @author leiin
 * created on  2022/6/18 2:53 下午
 */
@Data
public class WxMaShopSharerDataSummaryResponse extends WxMaShopBaseResponse implements Serializable {

  private static final long serialVersionUID = 3985829585979186778L;

  private Long gmv;
  @SerializedName("order_cnt")
  private Long orderCnt;
  @SerializedName("user_cnt")
  private Long userCnt;
}
