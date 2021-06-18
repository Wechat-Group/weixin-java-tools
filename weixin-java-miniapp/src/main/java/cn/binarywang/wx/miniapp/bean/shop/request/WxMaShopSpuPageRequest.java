package cn.binarywang.wx.miniapp.bean.shop.request;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import lombok.Data;

/**
 * @author leiin
 * @date 2021/3/23
 * @description: spu分页参数
 */
@Data
public class WxMaShopSpuPageRequest implements Serializable {

  private static final long serialVersionUID = -4927300283039328661L;

  /**
   * 商品状态
   * <pre>
   * 是否必填：是
   * </pre>
   */
  @SerializedName("status")
  private Integer status;

  /**
   * 开始创建时间
   * <pre>
   * 是否必填：否
   * </pre>
   */
  @SerializedName("start_create_time")
  private String startCreateTime;

  /**
   * 结束创建时间
   * <pre>
   * 是否必填：否
   * </pre>
   */
  @SerializedName("end_create_time")
  private String endCreateTime;

  /**
   * 开始更新时间
   * <pre>
   * 是否必填：否
   * </pre>
   */
  @SerializedName("start_update_time")
  private String startUpdateTime;

  /**
   * 结束更新时间
   * <pre>
   * 是否必填：否
   * </pre>
   */
  @SerializedName("end_update_time")
  private String endUpdateTime;

  /**
   * 默认0:获取线上数据, 1:获取草稿数据
   * <pre>
   * 是否必填：否
   * </pre>
   */
  @SerializedName("need_edit_spu")
  private Integer needEditSpu;

  /**
   * 页号
   * <pre>
   * 是否必填：是
   * </pre>
   */
  @SerializedName("page")
  private Integer page;

  /**
   * 页面大小
   * <pre>
   * 是否必填：是
   * </pre>
   */
  @SerializedName("page_size")
  private Integer pageSize;

}
