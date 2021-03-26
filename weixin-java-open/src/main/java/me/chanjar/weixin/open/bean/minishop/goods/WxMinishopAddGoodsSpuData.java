package me.chanjar.weixin.open.bean.minishop.goods;

import lombok.Data;

import java.io.Serializable;

@Data
public class WxMinishopAddGoodsSpuData implements Serializable {
  private Long productId;

  private String outProductId;

  private String createTime;
}
