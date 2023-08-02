package me.chanjar.weixin.open.bean.minishop;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class MinishopCategories implements Serializable {
  private static final long serialVersionUID = 7273326128218540329L;

  private Integer errcode;

  private String errmsg;

  private List<MinishopCategory> catList;
}
