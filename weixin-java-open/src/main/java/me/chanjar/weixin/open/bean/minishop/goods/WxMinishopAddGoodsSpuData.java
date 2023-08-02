package me.chanjar.weixin.open.bean.minishop.goods;

import lombok.Data;

import java.io.Serializable;

@Data
public class WxMinishopAddGoodsSpuData implements Serializable {
  private static final long serialVersionUID = 2023708625713948192L;
  private Long productId;

  private String outProductId;

  private String createTime;
}
