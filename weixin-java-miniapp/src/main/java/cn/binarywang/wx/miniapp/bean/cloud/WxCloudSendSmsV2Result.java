package cn.binarywang.wx.miniapp.bean.cloud;

import cn.binarywang.wx.miniapp.bean.WxMaBaseResponse;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 发送携带 URL Link 的短信结果
 *
 * @author liming1019
 * created on  2022-07-26
 */
@Data
public class WxCloudSendSmsV2Result extends WxMaBaseResponse implements Serializable {
  private static final long serialVersionUID = 4273038291300329985L;

  @SerializedName("send_status_list")
  private List<SendStatus> sendStatusList;

  @NoArgsConstructor
  @Data
  public static class SendStatus implements Serializable {
    private static final long serialVersionUID = 5765836923681051366L;

    @SerializedName("serial_no")
    private String serialNo;

    @SerializedName("phone_number")
    private String phoneNumber;

    @SerializedName("code")
    private String code;

    @SerializedName("message")
    private String message;

    @SerializedName("iso_code")
    private String isoCode;
  }
}
