package cn.binarywang.wx.miniapp.bean.shop.response;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.List;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @author leiin
 * created on  2022/6/18 2:51 下午
 */
@Data
public class WxMaShopSharerBindResponse extends WxMaShopBaseResponse implements Serializable {

  private static final long serialVersionUID = 5648529892711033276L;

  @SerializedName("success_list")
  private List<String> successList;

  @SerializedName("fail_list")
  private List<String> failList;

  @SerializedName("refuse_list")
  private List<String> refuseList;

  @SerializedName("result_list")
  private List<ResultListItem> resultList;

  @Getter
  @Setter
  public static class ResultListItem {
    private String openid;
    @SerializedName("result_code")
    private Integer resultCode;
    @SerializedName("reason_code")
    private Integer reasonCode;
  }
}
