package me.chanjar.weixin.channel.api;

import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;
import me.chanjar.weixin.channel.bean.league.window.AuthInfoResponse;
import me.chanjar.weixin.channel.bean.league.window.AuthStatusResponse;
import me.chanjar.weixin.channel.bean.league.window.ProductSearchParam;
import me.chanjar.weixin.channel.bean.league.window.WindowProductListResponse;
import me.chanjar.weixin.channel.bean.league.window.WindowProductResponse;
import me.chanjar.weixin.common.error.WxErrorException;

/**
 * 优选联盟 团长合作达人管理服务
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
public interface WxLeagueWindowService {

    /**
     * 添加团长商品到橱窗
     *
     * @param appid        团长appid
     * @param openfinderid 视频号openfinderid
     * @param productId    团长商品ID
     * @return 结果
     */
    WxChannelBaseResponse addProduct(String appid,  String openfinderid, String productId) throws WxErrorException;

    /**
     * 查询橱窗上团长商品列表
     *
     * @param param 查询参数
     * @return 团长商品列表
     */
    WindowProductListResponse listProduct(ProductSearchParam param) throws WxErrorException;

    /**
     * 从橱窗移除团长商品
     *
     * @param appid        团长appid
     * @param openfinderid 视频号openfinderid
     * @param productId    团长商品ID
     * @return 结果
     */
    WxChannelBaseResponse removeProduct(String appid,  String openfinderid, String productId) throws WxErrorException;

    /**
     * 查询橱窗上团长商品详情
     *
     * @param appid        团长appid
     * @param openfinderid 视频号openfinderid
     * @param productId    团长商品ID
     * @return 结果
     */
    WindowProductResponse getProductDetail(String appid,  String openfinderid, String productId)
      throws WxErrorException;

    /**
     * 获取达人橱窗授权链接
     *
     * @param finderId 视频号finder_id
     * @return 授权链接
     */
    AuthInfoResponse getWindowAuthInfo(String finderId) throws WxErrorException;

    /**
     * 获取达人橱窗授权状态
     *
     * @param finderId 视频号finder_id
     * @return 授权链接
     */
    AuthStatusResponse getWindowAuthStatus(String finderId) throws WxErrorException;
}
