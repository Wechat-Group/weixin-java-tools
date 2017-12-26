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
 * 授权小程序帐号的可选类目
 *
 * @author lfj
 */
@Data
public class WxOpenWxaCategoryResult implements Serializable {

  private static final long serialVersionUID = -5666887218954597840L;

  @SerializedName("category_list")
  private List<Category> categoryList;// 可填选的类目列表

  @Data
  public class Category implements Serializable {

    private static final long serialVersionUID = 1989990696039160413L;

    @SerializedName("first_class")
    private String firstClass;// 一级类目名称
    @SerializedName("second_class")
    private String secondClass;// 二级类目名称
    @SerializedName("third_class")
    private String thirdClass;// 三级类目名称
    @SerializedName("first_id")
    private Integer firstId;// 一级类目的ID编号
    @SerializedName("second_id")
    private Integer secondId;// 二级类目的ID编号
    @SerializedName("third_id")
    private Integer thirdId;// 三级类目的ID编号


    @Override
    public String toString() {
      return WxMpGsonBuilder.INSTANCE.create().toJson(this);
    }

  }

  public static WxOpenWxaCategoryResult fromJson(String json) {
    return WxOpenGsonBuilder.create().fromJson(json, WxOpenWxaCategoryResult.class);
  }

  @Override
  public String toString() {
    return WxMpGsonBuilder.INSTANCE.create().toJson(this);
  }

}
