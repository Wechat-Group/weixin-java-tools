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
 * @Description 省市区的区域信息
 * @createTime 2024/08/14 10:52
 */
@Getter
@Setter
@NoArgsConstructor
public class WxOpenQueryIcpDistrictCodeResult extends WxOpenResult {
  private static final long serialVersionUID = -5087976503275542589L;

  @SerializedName("items")
  private List<Item> items;

  @Getter
  @Setter
  @NoArgsConstructor
  public static class Item implements Serializable {

    private static final long serialVersionUID = -8598323103982035055L;

    /**
     * 区域类型: 1 -- 省份，2 -- 市，3 -- 区
     */
    @SerializedName("type")
    private Integer type;

    /**
     * 区域代码
     */
    @SerializedName("code")
    private Integer code;

    /**
     * 下级区域信息列表
     */
    @SerializedName("name")
    private String name;

    /**
     * 下级区域信息列表
     */
    @SerializedName("sub_list")
    private List<Item> subList;
  }
}
