package cn.binarywang.wx.miniapp.bean;

import cn.binarywang.wx.miniapp.util.json.WxMaGsonBuilder;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 域名相关操作
 *
 * @author <a href="https://github.com/charmingoh">Charming</a>
 * @since 2018-04-27 15:45
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WxMaDomainAction implements Serializable {
  private static final long serialVersionUID = -2898601966852935708L;
  /**
   * add添加, delete删除, set覆盖, get获取。当参数是get时不需要填四个域名字段
   */
  private String action;
  /**
   * request合法域名，当action参数是get时不需要此字段。
   */
  @SerializedName("requestdomain")
  private List<String> requestDomain;
  /**
   * socket合法域名，当action参数是get时不需要此字段。
   */
  @SerializedName("wsrequestdomain")
  private List<String> wsRequestDomain;
  /**
   * uploadFile合法域名，当action参数是get时不需要此字段。
   */
  @SerializedName("uploaddomain")
  private List<String> uploadDomain;
  /**
   * downloadFile合法域名，当action参数是get时不需要此字段。
   */
  @SerializedName("downloaddomain")
  private List<String> downloadDomain;
  /**
   * 小程序业务域名，当action参数是get时不需要此字段。
   */
  @SerializedName("webviewdomain")
  private List<String> webViewDomain;

  public String toJson() {
    return WxMaGsonBuilder.create().toJson(this);
  }

  public static WxMaDomainAction fromJson(String json) {
    return WxMaGsonBuilder.create().fromJson(json, WxMaDomainAction.class);
  }
}
