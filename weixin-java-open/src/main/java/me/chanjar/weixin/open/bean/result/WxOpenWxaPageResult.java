/**
 *
 */
package me.chanjar.weixin.open.bean.result;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import me.chanjar.weixin.mp.util.json.WxMpGsonBuilder;
import me.chanjar.weixin.open.util.json.WxOpenGsonBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * @author lfj
 */
@Data
public class WxOpenWxaPageResult implements Serializable {

  private static final long serialVersionUID = 3198083458642817052L;

  @SerializedName("page_list")
  private List<String> pageList;// page_list 页面配置列表


  public static WxOpenWxaPageResult fromJson(String json) {
    return WxOpenGsonBuilder.create().fromJson(json, WxOpenWxaPageResult.class);
  }

  @Override
  public String toString() {
    return WxMpGsonBuilder.INSTANCE.create().toJson(this);
  }

}
