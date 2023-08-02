package me.chanjar.weixin.channel.api;

import me.chanjar.weixin.channel.bean.league.supplier.CommissionOrderListParam;
import me.chanjar.weixin.channel.bean.league.supplier.CommissionOrderListResponse;
import me.chanjar.weixin.channel.bean.league.supplier.CommissionOrderResponse;
import me.chanjar.weixin.channel.bean.league.supplier.CoopProductListResponse;
import me.chanjar.weixin.channel.bean.league.supplier.CoopProductResponse;
import me.chanjar.weixin.channel.bean.league.supplier.FlowListParam;
import me.chanjar.weixin.channel.bean.league.supplier.ShopDetailResponse;
import me.chanjar.weixin.channel.bean.league.supplier.ShopListResponse;
import me.chanjar.weixin.channel.bean.league.supplier.SupplierBalanceResponse;
import me.chanjar.weixin.channel.bean.league.supplier.SupplierFlowDetailResponse;
import me.chanjar.weixin.channel.bean.league.supplier.SupplierFlowListResponse;
import me.chanjar.weixin.common.error.WxErrorException;

/**
 * 优选联盟 团长数据服务
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
public interface WxLeagueSupplierService {

    /**
     * 获取团长账户余额
     *
     * @return 余额
     */
    SupplierBalanceResponse getBalanceInfo() throws WxErrorException;

    /**
     * 获取资金流水详情
     *
     * @param flowId 流水ID
     * @return 流水详情
     */
    SupplierFlowDetailResponse getFlowDetail(String flowId) throws WxErrorException;

    /**
     * 获取团长资金流水列表
     *
     * @param param 查询参数
     * @return 流水列表
     */
    SupplierFlowListResponse getFlowList(FlowListParam param) throws WxErrorException;

    /**
     * 获取合作商品详情
     *
     * @param productId 商品ID
     * @param appId     团长商品 所属小店appid
     * @return 商品详情
     */
    CoopProductResponse getProductDetail(String productId, String appId) throws WxErrorException;

    /**
     * 获取合作商品列表
     *
     * @param appid    团长商品 所属小店appid
     * @param pageSize 单页商品数（不超过30）
     * @param nextKey  由上次请求返回，顺序翻页时需要传入, 会从上次返回的结果往后翻一页
     * @return 商品列表
     */
    CoopProductListResponse getProductList(String appid, Integer pageSize, String nextKey) throws WxErrorException;

    /**
     * 获取佣金单详情
     *
     * @param orderId 订单号，可从获取佣金单列表中获得
     * @param skuId   商品skuId
     * @return 订单详情
     */
    CommissionOrderResponse getCommissionOrder(String orderId, String skuId) throws WxErrorException;

    /**
     * 获取佣金单列表
     *
     * @param param 查询参数
     * @return 佣金单列表
     */
    CommissionOrderListResponse getCommissionOrderList(CommissionOrderListParam param) throws WxErrorException;

    /**
     * 获取合作小店详情
     *
     * @param appid 小店appid
     * @return 小店详情
     */
    ShopDetailResponse getShopDetail(String appid) throws WxErrorException;

    /**
     * 获取合作小店列表
     *
     * @param pageSize 单页小店数（不超过30）
     * @param nextKey  由上次请求返回，顺序翻页时需要传入, 会从上次返回的结果往后翻一页
     * @return 小店列表
     */
    ShopListResponse getShopList(Integer pageSize, String nextKey) throws WxErrorException;
}
