package cn.binarywang.wx.miniapp.bean.urllink;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * <pre>
 * 获取小程序 URL Link参数对象
 * </pre>
 * @author <a href="https://github.com/mr-xiaoyu">xiaoyu</a>
 * @since 2021-06-11
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GenerateUrlLinkRequest implements Serializable {

  private static final long serialVersionUID = -2183685760797791910L;

  /**
   * 通过 URL Link 进入的小程序页面路径，必须是已经发布的小程序存在的页面，不可携带 query 。path 为空时会跳转小程序主页
   * <pre>
   * 是否必填： 是
   * </pre>
   */
  private String path;

  /**
   * 通过 URL Link 进入小程序时的query，最大1024个字符，只支持数字，大小写英文以及部分特殊字符：!#$&'()*+,/:;=?@-._~
   * <pre>
   * 是否必填： 是
   * </pre>
   */
  private String query;

  /**
   * 要打开的小程序版本。正式版为"release"，体验版为"trial"，开发版为"develop"，仅在微信外打开时生效。
   * <pre>
   * 是否必填： 否
   * </pre>
   */
  @SerializedName("env_version")
  private String envVersion = "release";

  /**
   * 生成的 URL Link 类型，到期失效：true，永久有效：false
   * <pre>
   * 默认值：false
   * 是否必填： 否
   * </pre>
   */
  @SerializedName("is_expire")
  private Boolean isExpire;

  /**
   * 小程序 URL Link 失效类型，失效时间：0，失效间隔天数：1
   * <pre>
   * 是否必填： 是
   * </pre>
   */
  @SerializedName("expire_type")
  private Integer expireType;

  /**
   * 到期失效的 URL Link 的失效时间，为 Unix 时间戳。生成的到期失效 URL Link 在该时间前有效。最长有效期为1年。expire_type 为 0 必填
   * <pre>
   * 是否必填： 是
   * </pre>
   */
  @SerializedName("expire_time")
  private Long expireTime;

  /**
   * 到期失效的URL Link的失效间隔天数。生成的到期失效URL Link在该间隔时间到达前有效。最长间隔天数为365天。expire_type 为 1 必填
   * <pre>
   * 是否必填： 是
   * </pre>
   */
  @SerializedName("expire_interval")
  private Integer expireInterval;

  /**
   * 云开发静态网站自定义 H5 配置参数，可配置中转的云开发 H5 页面。不填默认用官方 H5 页面
   * <pre>
   * 是否必填： 否
   * </pre>
   */
  @SerializedName("cloud_base")
  private CloudBase cloudBase;

}
