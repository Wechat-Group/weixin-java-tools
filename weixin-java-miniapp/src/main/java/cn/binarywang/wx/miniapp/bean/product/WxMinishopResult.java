package cn.binarywang.wx.miniapp.bean.product;

import java.io.Serializable;
import lombok.Data;

@Data
public class WxMinishopResult<T> implements Serializable {
  private static final long serialVersionUID = 4323118714581265968L;
  private Integer errcode;

  private String errmsg;

  private T data;
}
