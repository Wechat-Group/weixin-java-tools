package cn.binarywang.wx.miniapp.bean.product;

import java.io.Serializable;
import lombok.Data;

@Data
public class WxMinishopAddGoodsSpuData implements Serializable {
  private static final long serialVersionUID = 2023708625713948192L;
  private Long productId;

  private String outProductId;

  private String createTime;

  private String updateTime;
}
