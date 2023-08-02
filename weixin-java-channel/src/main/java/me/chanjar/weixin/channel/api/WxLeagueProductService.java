package me.chanjar.weixin.channel.api;

import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;
import me.chanjar.weixin.channel.bean.league.product.BatchAddParam;
import me.chanjar.weixin.channel.bean.league.product.BatchAddResponse;
import me.chanjar.weixin.channel.bean.league.product.ProductDetailParam;
import me.chanjar.weixin.channel.bean.league.product.ProductDetailResponse;
import me.chanjar.weixin.channel.bean.league.product.ProductListParam;
import me.chanjar.weixin.channel.bean.league.product.ProductListResponse;
import me.chanjar.weixin.channel.bean.league.product.ProductUpdateParam;
import me.chanjar.weixin.channel.bean.league.product.ProductUpdateResponse;
import me.chanjar.weixin.common.error.WxErrorException;

/**
 * 优选联盟 商品操作服务
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
public interface WxLeagueProductService {

    /**
     * 批量新增联盟商品
     *
     * @param param 参数
     * @return 结果
     */
    BatchAddResponse batchAddProduct(BatchAddParam param) throws WxErrorException;

    /**
     * 更新联盟商品信息
     *
     * @param param 参数
     * @return 结果
     */
    ProductUpdateResponse updateProduct(ProductUpdateParam param) throws WxErrorException;

    /**
     * 删除联盟商品
     *
     * @param type 1普通推广商品 2定向推广商品 3专属推广商品
     * @param productId 商品id type为普通推广商品时必填
     * @param infoId 特殊推广商品计划id type为特殊推广商品时必填
     * @return
     */
    WxChannelBaseResponse deleteProduct(Integer type, String productId, String infoId) throws WxErrorException;

    /**
     * 拉取联盟商品详情
     *
     * @param param 参数
     * @return 结果
     */
    ProductDetailResponse getProductDetail(ProductDetailParam param) throws WxErrorException;

    /**
     * 拉取联盟商品推广列表
     *
     * @param param 参数
     * @return 结果
     */
    ProductListResponse listProduct(ProductListParam param) throws WxErrorException;
}
