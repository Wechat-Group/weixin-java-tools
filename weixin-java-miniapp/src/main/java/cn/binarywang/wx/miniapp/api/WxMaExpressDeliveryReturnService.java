package cn.binarywang.wx.miniapp.api;

import cn.binarywang.wx.miniapp.bean.express.request.WxMaExpressDeliveryReturnAddRequest;
import cn.binarywang.wx.miniapp.bean.express.result.WxMaExpressReturnInfoResult;
import me.chanjar.weixin.common.error.WxErrorException;

/**
 * 退货组件
 */
public interface WxMaExpressDeliveryReturnService {

    /**
     * 获取支持的快递公司列表
     */
    String ADD_DELIVERY_RETURN_URL = "https://api.weixin.qq.com/cgi-bin/express/delivery/return/add";
    String GET_DELIVERY_RETURN_URL = "https://api.weixin.qq.com/cgi-bin/express/delivery/return/get";
    String UNBIND_DELIVERY_RETURN_URL = "https://api.weixin.qq.com/cgi-bin/express/delivery/return/unbind";

    WxMaExpressReturnInfoResult addDeliveryReturn(WxMaExpressDeliveryReturnAddRequest wxMaExpressDeliveryReturnAddRequest) throws WxErrorException;
    WxMaExpressReturnInfoResult getDeliveryReturn(String returnId) throws WxErrorException;
    WxMaExpressReturnInfoResult unbindDeliveryReturn(String returnId) throws WxErrorException;
}
