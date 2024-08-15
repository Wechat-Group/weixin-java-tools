package me.chanjar.weixin.open.bean.icp;

import java.io.Serializable;
import java.util.List;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.chanjar.weixin.open.bean.result.WxOpenResult;


/**
 * @author xzh
 * @Description 小程序服务内容类型
 * @createTime 2024/08/14 10:52
 */
@Getter
@Setter
@NoArgsConstructor
public class WxOpenQueryIcpServiceContentTypesResult extends WxOpenResult {
  private static final long serialVersionUID = 2982244171428787389L;

  /**
   * 服务内容类型列表
   */
  private List<Item> items;

  @Getter
  @Setter
  @NoArgsConstructor
  public static class Item implements Serializable {

    private static final long serialVersionUID = 1432103347845426732L;

    /**
     * 服务内容类型id
     */
    @SerializedName("type")
    private Integer type;

    /**
     * 该服务内容类型的父类型id
     */
    @SerializedName("parent_type")
    private Integer parentType;

    /**
     * 名称
     */
    @SerializedName("name")
    private String name;

    /**
     * 备注
     */
    @SerializedName("remark")
    private String remark;
  }
}
