package me.chanjar.weixin.open.bean.minishop;

import lombok.Data;

import java.io.Serializable;

@Data
public class MinishopCategory implements Serializable {
  private Integer catId;

  private Integer fCatId;

  private String name;
}
