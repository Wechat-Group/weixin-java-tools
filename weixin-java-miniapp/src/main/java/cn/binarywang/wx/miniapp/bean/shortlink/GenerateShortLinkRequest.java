package cn.binarywang.wx.miniapp.bean.shortlink;

import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * <pre>
 * 获取小程序 Short Link参数对象
 * </pre>
 */
@Data
@Builder
public class GenerateShortLinkRequest implements Serializable {
  private static final long serialVersionUID = -7517804620683442832L;

  /**
   * 通过 Short Link 进入的小程序页面路径，必须是已经发布的小程序存在的页面，可携带 query，最大1024个字符
   * <pre>
   * 是否必填： 是
   * </pre>
   */
  @SerializedName("page_url")
  private String pageUrl;

  /**
   * 页面标题，不能包含违法信息，超过20字符会用... 截断代替
   * <pre>
   * 是否必填： 是
   * </pre>
   */
  @SerializedName("page_title")
  private String pageTitle;

  /**
   * 生成的 Short Link 类型，短期有效：false，永久有效：true
   * <pre>
   * 是否必填： 否
   * </pre>
   */
  @SerializedName("is_permanent")
  private Boolean isPermanent;
}
