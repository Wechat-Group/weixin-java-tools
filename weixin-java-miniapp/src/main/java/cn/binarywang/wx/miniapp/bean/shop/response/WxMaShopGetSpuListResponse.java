package cn.binarywang.wx.miniapp.bean.shop.response;

import cn.binarywang.wx.miniapp.bean.shop.WxMaShopGetSpuResult;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.List;
import lombok.Data;

/**
 * @author leiin
 * @date 2021/3/23
 * @description:
 */
@Data
public class WxMaShopGetSpuListResponse extends WxMaShopBaseResponse implements Serializable {

  private static final long serialVersionUID = 1423766388278762123L;

  /**
   * 总数
   * <pre>
   * 是否必填：
   * </pre>
   */
  @SerializedName("total_num")
  private Integer totalNum;

  /**
   * spu信息
   * <pre>
   * 是否必填：
   * </pre>
   */
  @SerializedName("spus")
  private List<WxMaShopGetSpuResult> spus;
}
