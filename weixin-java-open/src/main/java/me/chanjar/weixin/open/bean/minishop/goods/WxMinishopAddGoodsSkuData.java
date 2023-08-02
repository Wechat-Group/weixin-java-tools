package me.chanjar.weixin.open.bean.minishop.goods;

import lombok.Data;

import java.io.Serializable;

@Data
public class WxMinishopAddGoodsSkuData implements Serializable {
  private static final long serialVersionUID = -2596988603027040989L;
  private Long skuId;

  private String createTime;
}
