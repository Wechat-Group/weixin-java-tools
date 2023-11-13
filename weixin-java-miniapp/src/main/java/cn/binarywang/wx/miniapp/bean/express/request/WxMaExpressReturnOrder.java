package cn.binarywang.wx.miniapp.bean.express.request;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 退货商品对象
 * @author <a href="https://github.com/mr-xiaoyu">xiaoyu</a>
 * @since 2019-11-26
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WxMaExpressReturnOrder implements Serializable {
  private static final long serialVersionUID = -7798434835843377474L;

  /**
   * 商品名称
   * <pre>
   * 是否必填： 是
   * </pre>
   */
  @SerializedName("name")
  private String name;

  /**
   * 商品缩略图url
   * <pre>
   * 是否必填： 是
   * </pre>
   */
  @SerializedName("url")
  private String url;


}
