package me.chanjar.weixin.open.bean.result;

import lombok.Data;

import java.io.Serializable;

/**
 * @author <a href="https://github.com/007gzs">007</a>
 */
@Data
public class WxOpenAuthorizerOptionResult implements Serializable {
  private static final long serialVersionUID = 4477837353654658179L;

  String authorizerAppid;
  String optionName;
  String optionValue;
}
