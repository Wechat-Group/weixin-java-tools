package me.chanjar.weixin.open.bean.icp;

import java.io.Serializable;
import java.util.List;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author xzh
 * @Description 单位性质定义
 * @createTime 2024/08/14 10:52
 */
@Getter
@Setter
@NoArgsConstructor
public class WxOpenQueryIcpSubjectTypeResult implements Serializable {

  private static final long serialVersionUID = -2090825609788654435L;

  /**
   * 单位性质列表
   */
  private List<Item> items;

  @Getter
  @Setter
  @NoArgsConstructor
  public static class Item implements Serializable {

    private static final long serialVersionUID = -1284830856404207970L;

    /**
     * 单位性质类型id
     */
    @SerializedName("type")
    private Integer type;

    /**
     * 名称
     */
    @SerializedName("name")
    private String name;

    /**
     * 	备注
     */
    @SerializedName("remark")
    private String remark;
  }
}
