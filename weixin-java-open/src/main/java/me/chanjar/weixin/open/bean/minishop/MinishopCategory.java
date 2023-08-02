package me.chanjar.weixin.open.bean.minishop;

import lombok.Data;

import java.io.Serializable;

@Data
public class MinishopCategory implements Serializable {
  private static final long serialVersionUID = -4843799448671123177L;

  private Integer catId;

  private Integer fCatId;

  private String name;
}
