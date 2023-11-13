package cn.binarywang.wx.miniapp.bean.urllink.response;

import cn.binarywang.wx.miniapp.bean.WxMaBaseResponse;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <pre>
 * 查询小程序 URL Link响应对象
 * </pre>
 * @author <a href="https://github.com/imyzt">imyzt</a>
 * @since 2023-11-13
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class QueryUrlLinkResponse extends WxMaBaseResponse implements Serializable {

  /**
   * 访问Link的用户openid，为空表示未被访问过
   */
  @SerializedName("visit_openid")
  private String visitOpenid;

  /**
   * url_link 配置
   */
  @SerializedName("url_link_info")
  private UrlLinkInfo urlLinkInfo;

  @Data
  @Builder
  public static class UrlLinkInfo {

    /**
     * 小程序 appid
     */
    private String appid;

    /**
     * 小程序页面路径
     */
    private String path;

    /**
     * 小程序页面query
     */
    private String query;

    /**
     * 创建时间，为 Unix 时间戳
     */
    @SerializedName("create_time")
    private Long createTime;

    /**
     * 到期失效时间，为 Unix 时间戳，0 表示永久生效
     */
    @SerializedName("expire_time")
    private Long expireTime;

    /**
     * 要打开的小程序版本。正式版为"release"，体验版为"trial"，开发版为"develop"
     */
    @SerializedName("env_version")
    private String envVersion;
  }

}
