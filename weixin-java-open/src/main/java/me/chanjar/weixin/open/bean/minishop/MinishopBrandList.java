package me.chanjar.weixin.open.bean.minishop;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class MinishopBrandList implements Serializable {
  private static final long serialVersionUID = -8508031421949817741L;

  private Integer errcode;

  private String errmsg;

  private List<MinishopBrand> brands;
}
