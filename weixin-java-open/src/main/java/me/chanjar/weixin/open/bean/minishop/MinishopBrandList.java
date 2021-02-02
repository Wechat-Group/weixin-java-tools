package me.chanjar.weixin.open.bean.minishop;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class MinishopBrandList implements Serializable {
  private Integer errcode;

  private String errmsg;

  private List<MinishopBrand> brands;
}
