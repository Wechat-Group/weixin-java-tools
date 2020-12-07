package cn.binarywang.wx.miniapp.bean.express;

import cn.binarywang.wx.miniapp.json.WxMaGsonBuilder;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * <pre>
 * 面单打印员对象
 * </pre>
 * @author <a href="https://github.com/mr-xiaoyu">xiaoyu</a>
 * @since 2019-11-26
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WxMaExpressPrinter implements Serializable {


  private static final long serialVersionUID = 7164449984700322531L;

  /**
   * 数量
   */
  private Integer count;

  /**
   * 打印员openid
   */
  private List<String> openid;

  /**
   * 打印员面单打印权限
   */
  @SerializedName("tagid_list")
  private List<String> tagidList;


  public static WxMaExpressPrinter fromJson(String json) {
    return WxMaGsonBuilder.create().fromJson(json, WxMaExpressPrinter.class);
  }
}
