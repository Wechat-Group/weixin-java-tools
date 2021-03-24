package me.chanjar.weixin.open.bean.minishop;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Data
public class MinishopBrand implements Serializable {
  private Integer firstCatId;

  private Integer secondCatId;

  private Integer thirdCatId;

  @Data
  @Getter
  @Setter
  public static class MinishopBrandInfo implements Serializable {
    private Integer brandId;
    private String brandName;
  }

  private MinishopBrandInfo brandInfo;
}
