package cn.binarywang.wx.miniapp.bean.shop.request;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author liming1019
 * @date 2021/8/6
 */
@Data
public class WxMaShopRegisterFinishAccessInfoRequest implements Serializable {
  private static final long serialVersionUID = 8679586799807671563L;
  /**
   * 6:完成spu接口，7:完成订单接口，8:完成物流接口，9:完成售后接口，10:测试完成，11:发版完成
   */
  @SerializedName("access_info_item")
  private Long accessInfoItem;
}

