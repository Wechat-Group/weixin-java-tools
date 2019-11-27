package cn.binarywang.wx.miniapp.bean.express;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 包裹信息对象
 * @author <a href="https://github.com/mr-xiaoyu">xiaoyu</a>
 * @since 2019-11-26
 */
@Data
public class WxMaExpressOrderCargo implements Serializable {

  private static final long serialVersionUID = 6642536671375396150L;

  /**
   * 包裹数量
   * <pre>
   * 是否必填： 是
   * 描述： 需要和detail_list size保持一致
   * </pre>
   */
  private Integer count;
  /**
   * 包裹总重量
   * <pre>
   * 是否必填： 是
   * 描述： 单位是千克(kg)
   * </pre>
   */
  private Integer weight;

  /**
   * 包裹长度
   * <pre>
   * 是否必填： 是
   * 描述： 单位是厘米(cm)
   * </pre>
   */
  @SerializedName("space_x")
  private Integer spaceLength;

  /**
   * 包裹宽度
   * <pre>
   * 是否必填： 是
   * 描述： 单位是厘米(cm)
   * </pre>
   */
  @SerializedName("space_y")
  private Integer spaceWidth;

  /**
   * 包裹高度
   * <pre>
   * 是否必填： 是
   * 描述： 单位是厘米(cm)
   * </pre>
   */
  @SerializedName("space_z")
  private Integer spaceHeight;

  /**
   * 包裹中商品详情列表
   * <pre>
   * 是否必填： 是
   * </pre>
   */
  @SerializedName("detail_list")
  private List<WxMaExpressOrderCargoDetail> detailList;
}
