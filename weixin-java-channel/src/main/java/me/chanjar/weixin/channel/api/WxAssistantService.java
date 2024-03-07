package me.chanjar.weixin.channel.api;

import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;
import me.chanjar.weixin.channel.bean.window.request.AddWindowProductRequest;
import me.chanjar.weixin.channel.bean.window.request.GetWindowProductListRequest;
import me.chanjar.weixin.channel.bean.window.request.WindowProductRequest;
import me.chanjar.weixin.channel.bean.window.response.GetWindowProductListResponse;
import me.chanjar.weixin.channel.bean.window.response.GetWindowProductResponse;
import me.chanjar.weixin.common.error.WxErrorException;

/**
 * 视频号助手 橱窗管理服务 <br/>
 * 关于橱窗商品ID的说明: <br/>
 * 不支持带货中心来源的商品，其余商品的橱窗商品ID与商品来源处的平台内部商品ID相同，对应关系如下 <br/>
 * <pre>
 * 商品来源	橱窗ID说明
 * 视频号小店	视频号小店商品的 product_id 字段
 * 交易组件	组件商品的 product_id 字段
 * </pre>
 *
 * @author <a href="https://github.com/imyzt">imyzt</a>
 */
public interface WxAssistantService {

  /**
   * <a href="https://developers.weixin.qq.com/doc/channels/API/windowproduct/add.html">上架商品到橱窗</a>
   * @param req 商品信息
   * @return 操作结果
   */
  WxChannelBaseResponse addWindowProduct(AddWindowProductRequest req) throws WxErrorException;

  /**
   * <a href="https://developers.weixin.qq.com/doc/channels/API/windowproduct/get.html">获取橱窗商品详情</a>
   *
   * @param req 商品信息
   * @return 橱窗商品详情
   */
  GetWindowProductResponse getWindowProduct(WindowProductRequest req) throws WxErrorException;

  /**
   * <a href="https://developers.weixin.qq.com/doc/channels/API/windowproduct/list_get.html">获取已添加到橱窗的商品列表</a>
   * 接口限制了 page_size × page_index ≤ 10000。命中限制时建议改用传last_buffer顺序翻页的请求方式
   * @param req 商品信息
   * @return 已添加到橱窗的商品列表
   */
  GetWindowProductListResponse getWindowProductList(GetWindowProductListRequest req) throws WxErrorException;

  /**
   * <a href="https://developers.weixin.qq.com/doc/channels/API/windowproduct/off.html">下架橱窗商品</a>
   * @param req 商品信息
   * @return 操作结果
   */
  WxChannelBaseResponse offWindowProduct(WindowProductRequest req) throws WxErrorException;

}
