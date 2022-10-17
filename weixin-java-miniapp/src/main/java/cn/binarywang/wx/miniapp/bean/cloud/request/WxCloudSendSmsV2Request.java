package cn.binarywang.wx.miniapp.bean.cloud.request;

import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 发送携带 URL Link 的短信请求
 *
 * @author liming1019
 * created on  2022-07-26
 */
@Data
@Builder
public class WxCloudSendSmsV2Request implements Serializable {
  private static final long serialVersionUID = 8917033507660980594L;

  @SerializedName("env")
  private String env;

  @SerializedName("url_link")
  private String urlLink;

  @SerializedName("template_id")
  private String templateId;

  @SerializedName("template_param_list")
  private List<String> templateParamList;

  @SerializedName("phone_number_list")
  private List<String> phoneNumberList;

  @SerializedName("use_short_name")
  private Boolean useShortName;

  @SerializedName("resource_appid")
  private String resourceAppid;
}
