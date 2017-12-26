/**
 *
 */
package me.chanjar.weixin.open.bean.result;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import me.chanjar.weixin.common.util.ToStringUtils;
import me.chanjar.weixin.mp.util.json.WxMpGsonBuilder;
import me.chanjar.weixin.open.util.json.WxOpenGsonBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * @author lfj
 */
@Data
public class WxOpenWxaSubmitAudit implements Serializable {

  private static final long serialVersionUID = 8660866083812768899L;

  @SerializedName("item_list")
  private List<AuditItem> itemList;

  @Data
  public class AuditItem implements Serializable {

    private static final long serialVersionUID = 1603146766456217944L;
    private String address;// 小程序的页面，可通过“获取小程序的第三方提交代码的页面配置”接口获得
    private String tag;// 小程序的标签，多个标签用空格分隔，标签不能多于10个，标签长度不超过20
    @SerializedName("first_class")
    private String firstClass;// 一级类目名称，可通过“获取授权小程序帐号的可选类目”接口获得
    @SerializedName("second_class")
    private String secondClass;// 二级类目(同上)
    @SerializedName("third_class")
    private String thirdClass;// 三级类目(同上)
    @SerializedName("first_id")
    private Integer firstId;// 一级类目的ID，可通过“获取授权小程序帐号的可选类目”接口获得
    @SerializedName("second_id")
    private Integer secondId;// 二级类目的ID(同上)
    @SerializedName("third_id")
    private Integer thirdId;// 三级类目的ID(同上)
    private String title;// 小程序页面的标题,标题长度不超过32

    @Override
    public String toString() {
      return WxMpGsonBuilder.INSTANCE.create().toJson(this);
    }

  }

  public static WxOpenWxaSubmitAudit fromJson(String json) {
    return WxOpenGsonBuilder.create().fromJson(json, WxOpenWxaSubmitAudit.class);
  }

  @Override
  public String toString() {
    return WxMpGsonBuilder.INSTANCE.create().toJson(this);
  }

  public String toJson() {
    return WxMpGsonBuilder.INSTANCE.create().toJson(this);
  }

}
