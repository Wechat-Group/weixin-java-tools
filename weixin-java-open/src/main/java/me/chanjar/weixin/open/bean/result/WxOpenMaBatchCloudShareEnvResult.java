package me.chanjar.weixin.open.bean.result;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import me.chanjar.weixin.open.util.json.WxOpenGsonBuilder;

import java.util.List;

/**
 * 微信第三方平台-批量代云开发-绑定环境
 * @author caiqy
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class WxOpenMaBatchCloudShareEnvResult extends WxOpenResult{

  private static final long serialVersionUID = 5771596338639786886L;

  @SerializedName("err_list")
  private List<ShareEnvResultItem> errorList;

  @Data
  public static class ShareEnvResultItem {

    /**
     * 环境ID
     */
    @SerializedName("env")
    private String env;

    /**
     * appid
     */
    @SerializedName("appid")
    private String appid;

    /**
     * 错误信息
     */
    @SerializedName("errmsg")
    private String errMsg;
  }


  @Override
  public String toString() {
    return WxOpenGsonBuilder.create().toJson(this);
  }
}
