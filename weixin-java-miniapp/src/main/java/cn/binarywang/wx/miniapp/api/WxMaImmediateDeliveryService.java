package cn.binarywang.wx.miniapp.api;

import cn.binarywang.wx.miniapp.bean.delivery.AbnormalConfirmRequest;
import cn.binarywang.wx.miniapp.bean.delivery.AbnormalConfirmResponse;
import cn.binarywang.wx.miniapp.bean.delivery.AddOrderRequest;
import cn.binarywang.wx.miniapp.bean.delivery.AddOrderResponse;
import cn.binarywang.wx.miniapp.bean.delivery.BindAccountResponse;
import cn.binarywang.wx.miniapp.bean.delivery.CancelOrderRequest;
import cn.binarywang.wx.miniapp.bean.delivery.CancelOrderResponse;
import cn.binarywang.wx.miniapp.bean.delivery.GetOrderRequest;
import cn.binarywang.wx.miniapp.bean.delivery.GetOrderResponse;
import cn.binarywang.wx.miniapp.bean.delivery.MockUpdateOrderRequest;
import cn.binarywang.wx.miniapp.bean.delivery.MockUpdateOrderResponse;
import me.chanjar.weixin.common.error.WxErrorException;

/**
 * 微信小程序即时配送服务.
 * <pre>
 *     文档地址：https://developers.weixin.qq.com/miniprogram/dev/platform-capabilities/industry/immediate-delivery/overview.html
 * </pre>
 *
 * @author Luo
 * @version 1.0
 * @date 2021-10-13 16:40
 */
public interface WxMaImmediateDeliveryService {

    /**
     * 拉取已绑定账号.
     * <pre>
     * 文档地址：https://developers.weixin.qq.com/miniprogram/dev/api-backend/open-api/immediate-delivery/by-business/immediateDelivery.getBindAccount.html
     * </pre>
     *
     * @return 响应
     * @throws WxErrorException 异常
     */
    BindAccountResponse getBindAccount() throws WxErrorException;

    /**
     * 下配送单接口.
     * <pre>
     * 文档地址：https://developers.weixin.qq.com/miniprogram/dev/api-backend/open-api/immediate-delivery/by-business/immediateDelivery.addOrder.html
     * </pre>
     *
     * @param request request
     * @return 响应
     * @throws WxErrorException 异常
     */
    AddOrderResponse addOrder(AddOrderRequest request) throws WxErrorException;

    /**
     * 拉取配送单信息.
     * <pre>
     * 商家可使用本接口查询某一配送单的配送状态，便于商家掌握配送情况。
     * 文档地址：https://developers.weixin.qq.com/miniprogram/dev/api-backend/open-api/immediate-delivery/by-business/immediateDelivery.getOrder.html
     * </pre>
     *
     * @param request request
     * @return 响应
     * @throws WxErrorException 异常
     */
    GetOrderResponse getOrder(GetOrderRequest request) throws WxErrorException;

    /**
     * 取消配送单接口.
     * <pre>
     * 文档地址：https://developers.weixin.qq.com/miniprogram/dev/api-backend/open-api/immediate-delivery/by-business/immediateDelivery.cancelOrder.html
     * </pre>
     *
     * @param request request
     * @return 响应
     * @throws WxErrorException 异常
     */
    CancelOrderResponse cancelOrder(CancelOrderRequest request) throws WxErrorException;

    /**
     * 异常件退回商家商家确认收货接口.
     * <pre>
     * 文档地址：https://developers.weixin.qq.com/miniprogram/dev/api-backend/open-api/immediate-delivery/by-business/immediateDelivery.abnormalConfirm.html
     * </pre>
     *
     * @param request request
     * @return 响应
     * @throws WxErrorException 异常
     */
    AbnormalConfirmResponse abnormalConfirm(AbnormalConfirmRequest request) throws WxErrorException;

    /**
     * 模拟配送公司更新配送单状态, 该接口只用于沙盒环境，即订单并没有真实流转到运力方.
     * <pre>
     * 文档地址：https://developers.weixin.qq.com/miniprogram/dev/api-backend/open-api/immediate-delivery/by-business/immediateDelivery.mockUpdateOrder.html
     * </pre>
     *
     * @param request request
     * @return 响应
     * @throws WxErrorException 异常
     */
    MockUpdateOrderResponse mockUpdateOrder(MockUpdateOrderRequest request) throws WxErrorException;

}
