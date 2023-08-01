package cn.binarywang.wx.miniapp.bean.scheme;

import cn.binarywang.wx.miniapp.json.WxMaGsonBuilder;
import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Data;

/**
 * @author : lyt
 * created on  : 2023-07-31
 */
@Data
@Builder(builderMethodName = "newBuilder")
public class WxMaGenerateNfcSchemeRequest {
  /**
   * 跳转到的目标小程序信息。
   * <pre>
   * 是否必填：否
   * </pre>
   */
  @SerializedName("jump_wxa")
  private JumpWxa jumpWxa;

  /**
   * scheme对应的设备model_id
   * <pre>
   * 是否必填：是
   * </pre>
   */
  @SerializedName("model_id")
  private String modelId;

  /**
   * scheme对应的设备sn，仅一机一码时填写
   * <pre>
   * 是否必填：否
   * </pre>
   */
  @SerializedName("sn")
  private String sn;

  @Data
  @Builder(builderMethodName = "newBuilder")
  public static class JumpWxa {
    /**
     * 通过scheme码进入的小程序页面路径，必须是已经发布的小程序存在的页面，不可携带query。path为空时会跳转小程序主页。
     * <pre>
     * 是否必填：是
     * </pre>
     */
    @SerializedName("path")
    private String path;

    /**
     * 通过scheme码进入小程序时的query，最大128个字符，只支持数字，大小写英文以及部分特殊字符：!#$&'()*+,/:;=?@-._~
     * 返回值
     * <pre>
     * 是否必填：是
     * </pre>
     */
    @SerializedName("query")
    private String query;

    /**
     * 要打开的小程序版本。正式版为"release"，体验版为"trial"，开发版为"develop"默认值：release
     */
    @SerializedName("env_version")
    private String envVersion = "release";
  }

  public String toJson() {
    return WxMaGsonBuilder.create().toJson(this);
  }

}
