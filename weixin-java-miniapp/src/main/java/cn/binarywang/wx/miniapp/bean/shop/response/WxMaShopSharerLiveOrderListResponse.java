package cn.binarywang.wx.miniapp.bean.shop.response;

import cn.binarywang.wx.miniapp.bean.shop.WxMaShopOrderDetail;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.List;
import lombok.Data;

/**
 * @author leiin
 * @date 2022/6/18 2:56 下午
 */
@Data
public class WxMaShopSharerLiveOrderListResponse extends WxMaShopBaseResponse implements Serializable {

  private static final long serialVersionUID = -4190199778148290127L;

  private List<WxMaShopOrderDetail> orders;

  @SerializedName("total_num")
  private Integer totalNum;
}
