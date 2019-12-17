package cn.binarywang.wx.miniapp.bean.express;

import cn.binarywang.wx.miniapp.util.json.WxMaGsonBuilder;
import com.google.gson.JsonParser;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

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
public class WxMaExpressPrinter implements Serializable {

  private static final JsonParser JSON_PARSER = new JsonParser();
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
