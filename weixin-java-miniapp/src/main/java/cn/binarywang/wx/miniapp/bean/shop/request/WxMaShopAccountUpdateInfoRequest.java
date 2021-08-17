package cn.binarywang.wx.miniapp.bean.shop.request;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author liming1019
 * @date 2021/8/9
 */
@Data
public class WxMaShopAccountUpdateInfoRequest implements Serializable {
  private static final long serialVersionUID = 5185978220275730559L;
  /**
   * 小程序path
   */
  @SerializedName("service_agent_path")
  private String serviceAgentPath;

  /**
   * 小程序path
   */
  @SerializedName("service_agent_phone")
  private String serviceAgentPhone;
}
