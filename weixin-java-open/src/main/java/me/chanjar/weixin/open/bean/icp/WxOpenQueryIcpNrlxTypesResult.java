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
 * @Description 小程序备案前置审批项类型定义
 * @createTime 2024/08/14 10:52
 */
@Getter
@Setter
@NoArgsConstructor
public class WxOpenQueryIcpNrlxTypesResult extends WxOpenResult {
  private static final long serialVersionUID = 4986067025882451072L;

  /**
   * 前置审批项类型列表
   */
  @SerializedName("items")
  private List<Item> items;

  @Getter
  @Setter
  @NoArgsConstructor
  public static class Item implements Serializable {

    private static final long serialVersionUID = 9069126796573950000L;

    /**
     * 前置审批项类型id
     */
    @SerializedName("type")
    private Integer type;

    /**
     * 名称
     */
    @SerializedName("name")
    private String name;
  }
}
