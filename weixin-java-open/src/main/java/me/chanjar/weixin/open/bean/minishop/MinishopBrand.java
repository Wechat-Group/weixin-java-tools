package me.chanjar.weixin.open.bean.minishop;

import lombok.Data;

import java.io.Serializable;

@Data
public class MinishopBrand implements Serializable {
  private Integer firstCatId;

  private Integer secondCatId;

  private Integer thirdCatId;

  private Integer brandId;

  private String brandName;
}
