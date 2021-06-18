package cn.binarywang.wx.miniapp.bean.shop.response;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import lombok.Data;

/**
 * @author leiin
 * @date 2021/3/23
 * @description:
 */
@Data
public class WxMaShopBaseResponse implements Serializable {

  private static final long serialVersionUID = -4647161641538864187L;

  /**
   * 错误码
   * <pre>
   * 是否必填：
   * </pre>
   */
  @SerializedName("errcode")
  private Integer errcode;

  /**
   * 错误信息
   * <pre>
   * 是否必填：
   * </pre>
   */
  @SerializedName("errmsg")
  private String errmsg;
}
