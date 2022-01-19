package cn.binarywang.wx.miniapp.bean.delivery;

import cn.binarywang.wx.miniapp.bean.delivery.base.WxMaDeliveryBaseResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 微信小程序即时配送 模拟配送公司更新配送单状态 响应参数.
 *
 * @author Luo
 * @version 1.0
 * @date 2021-10-14 10:49
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class MockUpdateOrderResponse extends WxMaDeliveryBaseResponse implements Serializable {

    private static final long serialVersionUID = 3773007367000633663L;


}
