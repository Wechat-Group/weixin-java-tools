package me.chanjar.weixin.open.bean.minishop;

import lombok.Data;

import java.io.Serializable;

@Data
public class MinishopBrand implements Serializable {
  private static final long serialVersionUID = -112023091374421283L;

  private Integer firstCatId;

  private Integer secondCatId;

  private Integer thirdCatId;

  @Data
  public static class MinishopBrandInfo implements Serializable {
    private static final long serialVersionUID = 5727133059972671186L;

    private Integer brandId;
    private String brandName;
  }

  private MinishopBrandInfo brandInfo;
}
