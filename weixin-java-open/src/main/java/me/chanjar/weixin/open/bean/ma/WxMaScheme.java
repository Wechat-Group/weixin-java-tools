package me.chanjar.weixin.open.bean.ma;

import lombok.Data;
import lombok.EqualsAndHashCode;
import me.chanjar.weixin.open.bean.result.WxOpenResult;

@Data
@EqualsAndHashCode(callSuper = true)
public class WxMaScheme extends WxOpenResult {
  private static final long serialVersionUID = 6099475183322341647L;

  private String openlink;
}
