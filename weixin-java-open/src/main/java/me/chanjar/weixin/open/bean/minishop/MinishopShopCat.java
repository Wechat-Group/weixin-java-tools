package me.chanjar.weixin.open.bean.minishop;

import lombok.Data;

import java.io.Serializable;

/**
 * 店铺的商品分类
 */
@Data
public class MinishopShopCat implements Serializable {
  private static final long serialVersionUID = 4179473856929659641L;

  private Integer shopCatId;

  private String shopCatName;

  private Integer fShopCatId;

  private Integer catLevel;
}
