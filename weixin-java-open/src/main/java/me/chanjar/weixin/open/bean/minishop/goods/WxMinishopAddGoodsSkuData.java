package me.chanjar.weixin.open.bean.minishop.goods;

import lombok.Data;

import java.io.Serializable;

@Data
public class WxMinishopAddGoodsSkuData implements Serializable {
  private Long skuId;

  private String createTime;
}
