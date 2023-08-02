package cn.binarywang.wx.miniapp.bean.shop.response;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.List;
import lombok.Data;

/**
 * @author leiin
 * created on  2022/6/18 3:00 下午
 */
@Data
public class WxMaShopSharerUnbindResponse extends WxMaShopBaseResponse implements Serializable {

  private static final long serialVersionUID = -9015680115600175408L;

  @SerializedName("success_list")
  private List<String> successList;

  @SerializedName("fail_list")
  private List<String> failList;

  @SerializedName("refuse_list")
  private List<String> refuseList;
}
