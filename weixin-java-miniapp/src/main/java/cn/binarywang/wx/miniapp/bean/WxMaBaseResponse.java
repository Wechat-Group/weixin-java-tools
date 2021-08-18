package cn.binarywang.wx.miniapp.bean;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author liming1019
 * @date 2021/8/17
 */
@Data
public class WxMaBaseResponse implements Serializable {
  private static final long serialVersionUID = 3932406255203539965L;
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
