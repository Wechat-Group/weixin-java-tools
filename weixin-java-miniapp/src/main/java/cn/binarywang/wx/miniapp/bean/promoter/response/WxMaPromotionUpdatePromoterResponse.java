package cn.binarywang.wx.miniapp.bean.promoter.response;

import cn.binarywang.wx.miniapp.bean.WxMaBaseResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author zhuangzibin
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class WxMaPromotionUpdatePromoterResponse extends WxMaBaseResponse implements Serializable {

  private static final long serialVersionUID = 4181066183104514177L;
}
