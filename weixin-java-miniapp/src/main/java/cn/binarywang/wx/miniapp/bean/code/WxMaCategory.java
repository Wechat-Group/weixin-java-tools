package cn.binarywang.wx.miniapp.bean.code;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 小程序帐号的可选类目，其中 address / tag / title 是提交审核会用到的
 *
 * @author <a href="https://github.com/charmingoh">Charming</a>
 * @since 2018-04-26 19:44
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WxMaCategory implements Serializable {
  private static final long serialVersionUID = -7663757440028175135L;
  /**
   * 一级类目名称
   */
  @SerializedName("first_class")
  private String firstClass;
  /**
   * 二级类目名称
   */
  @SerializedName("second_class")
  private String secondClass;
  /**
   * 三级类目名称
   */
  @SerializedName("third_class")
  private String thirdClass;
  /**
   * 一级类目的ID编号
   */
  @SerializedName("first_id")
  private Long firstId;
  /**
   * 二级类目的ID编号
   */
  @SerializedName("second_id")
  private Long secondId;
  /**
   * 三级类目的ID编号
   */
  @SerializedName("third_id")
  private Long thirdId;

  /**
   * 小程序的页面，可通过“获取小程序的第三方提交代码的页面配置”接口获得
   */
  private String address;
  /**
   * 小程序的标签，多个标签用空格分隔，标签不能多于10个，标签长度不超过20
   */
  private String tag;
  /**
   * 小程序页面的标题,标题长度不超过32
   */
  private String title;
}
