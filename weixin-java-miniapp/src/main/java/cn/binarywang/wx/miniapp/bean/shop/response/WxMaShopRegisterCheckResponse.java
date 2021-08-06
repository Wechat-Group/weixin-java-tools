package cn.binarywang.wx.miniapp.bean.shop.response;

import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author liming1019
 * @date 2021/8/5
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class WxMaShopRegisterCheckResponse extends WxMaShopBaseResponse implements Serializable {

  private static final long serialVersionUID = 9061844525630614116L;

  @SerializedName("data")
  private JsonObject data;
}
