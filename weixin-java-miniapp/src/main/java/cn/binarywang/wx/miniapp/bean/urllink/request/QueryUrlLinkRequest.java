package cn.binarywang.wx.miniapp.bean.urllink.request;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <pre>
 * 查询小程序 URL Link参数对象
 * </pre>
 * @author <a href="https://github.com/imyzt">imyzt</a>
 * @since 2023-11-13
 */
@Data
@Builder
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class QueryUrlLinkRequest implements Serializable {

  /**
   * 小程序 url_link
   * <pre>
   * 是否必填： 是
   * </pre>
   */
  @SerializedName("url_link")
  private String urlLink;

}
