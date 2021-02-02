package me.chanjar.weixin.open.bean.minishop;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class MinishopShopCatList implements Serializable {
  private Integer errcode;

  private String errmsg;

  private List<MinishopShopCat> shopCatList;
}
