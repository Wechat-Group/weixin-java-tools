package cn.binarywang.wx.miniapp.bean.shop.response;

import cn.binarywang.wx.miniapp.bean.shop.WxMaShopCatGetDetail;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

/**
 * @author liming1019
 * @date 2021/8/9
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class WxMaShopCatGetResponse extends WxMaShopBaseResponse implements Serializable {
  private static final long serialVersionUID = -2565959470798387313L;

  @SerializedName("third_cat_list")
  private List<WxMaShopCatGetDetail> thirdCatList;
}
