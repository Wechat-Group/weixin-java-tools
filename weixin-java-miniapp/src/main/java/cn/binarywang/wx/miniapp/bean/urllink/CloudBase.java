package cn.binarywang.wx.miniapp.bean.urllink;

import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Data;

/**
 * <pre>
 * 云开发静态网站自定义 H5 配置参数
 * </pre>
 * @author <a href="https://github.com/mr-xiaoyu">xiaoyu</a>
 * @since 2021-06-11
 */
@Data
@Builder
public class CloudBase {

  /**
   * 云开发环境
   * <pre>
   * 是否必填： 是
   * </pre>
   */
  private String env;

  /**
   * 静态网站自定义域名，不填则使用默认域名
   * <pre>
   * 是否必填： 否
   * </pre>
   */
  private String domain;

  /**
   * 云开发静态网站 H5 页面路径，不可携带 query
   * <pre>
   * 默认值：/
   * 是否必填： 否
   * </pre>
   */
  private String path;

  /**
   * 云开发静态网站 H5 页面 query 参数，最大 1024 个字符，只支持数字，大小写英文以及部分特殊字符：!#$&'()*+,/:;=?@-._~
   * <pre>
   * 是否必填： 否
   * </pre>
   */
  private String query;

  /**
   * 第三方批量代云开发时必填，表示创建该 env 的 appid （小程序/第三方平台）
   * <pre>
   * 是否必填： 否
   * </pre>
   */
  @SerializedName("resource_appid")
  private String resourceAppid;

}
