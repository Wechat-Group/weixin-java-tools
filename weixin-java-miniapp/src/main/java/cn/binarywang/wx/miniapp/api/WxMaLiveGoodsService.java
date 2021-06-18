package cn.binarywang.wx.miniapp.api;

import cn.binarywang.wx.miniapp.bean.live.WxMaLiveGoodInfo;
import cn.binarywang.wx.miniapp.bean.live.WxMaLiveResult;
import me.chanjar.weixin.common.error.WxErrorException;

import java.util.List;

/**
 * <pre>
 * 直播间商品相关操作接口
 * 文档地址：https://developers.weixin.qq.com/miniprogram/dev/framework/liveplayer/commodity-api.html
 * Created by lipengjun on 2020/6/29.
 * </pre>
 *
 * @author <a href="https://github.com/lipengjun92">lipengjun (939961241@qq.com)</a>
 */
public interface WxMaLiveGoodsService {
  /**
   * 商品添加并提审
   * <pre>
   * 调用此接口上传并提审需要直播的商品信息，审核通过后商品录入【小程序直播】商品库
   * 注意：开发者必须保存【商品ID】与【审核单ID】，如果丢失，则无法调用其他相关接口
   * 调用额度：500次/一天
   * http请求方式：POST https://api.weixin.qq.com/wxaapi/broadcast/goods/add?access_token=ACCESS_TOKEN
   * </pre>
   *
   * @param goods 商品
   * @return 返回auditId、goodsId
   * @throws WxErrorException .
   */
  WxMaLiveResult addGoods(WxMaLiveGoodInfo goods) throws WxErrorException;

  /**
   * 撤回审核
   * <pre>
   * 调用此接口，可撤回直播商品的提审申请，消耗的提审次数不返还
   * 调用额度：500次/一天
   * http请求方式：POST https://api.weixin.qq.com/wxaapi/broadcast/goods/resetaudit?access_token=ACCESS_TOKEN
   * <pre>
   * @param auditId 审核单ID
   * @param goodsId 商品ID
   * @return 撤回审核是否成功
   * @throws WxErrorException .
   */
  boolean resetAudit(Integer auditId, Integer goodsId) throws WxErrorException;

  /**
   * 重新提交审核
   * <pre>
   * 调用此接口，可撤回直播商品的提审申请，消耗的提审次数不返还
   * 调用额度：500次/一天（与接口'商品添加并提审'共用500次限制）
   * http请求方式：POST https://api.weixin.qq.com/wxaapi/broadcast/goods/audit?access_token=ACCESS_TOKEN
   * <pre>
   * @param goodsId 商品ID
   * @return 审核单ID
   * @throws WxErrorException .
   */
  String auditGoods(Integer goodsId) throws WxErrorException;

  /**
   * 删除商品
   * <pre>
   * 调用此接口，可删除【小程序直播】商品库中的商品，删除后直播间上架的该商品也将被同步删除，不可恢复；
   * 调用额度：1000次/一天
   * http请求方式：POST https://api.weixin.qq.com/wxaapi/broadcast/goods/delete?access_token=ACCESS_TOKEN
   * <pre>
   * @param goodsId 商品ID
   * @return 删除商品是否成功
   * @throws WxErrorException .
   */
  boolean deleteGoods(Integer goodsId) throws WxErrorException;

  /**
   * 更新商品
   * <pre>
   * 调用此接口可以更新商品信息，审核通过的商品仅允许更新价格类型与价格，审核中的商品不允许更新，未审核的商品允许更新所有字段， 只传入需要更新的字段。
   * 调用额度：1000次/一天
   * http请求方式：POST https://api.weixin.qq.com/wxaapi/broadcast/goods/update?access_token=ACCESS_TOKEN
   * </pre>
   *
   * @param goods 商品
   * @return 更新商品是否成功
   * @throws WxErrorException .
   */
  boolean updateGoods(WxMaLiveGoodInfo goods) throws WxErrorException;

  /**
   * 获取商品状态
   * <pre>
   * 调用此接口可获取商品的信息与审核状态
   * 调用额度：1000次/一天
   * http请求方式：POST https://api.weixin.qq.com/wxa/business/getgoodswarehouse?access_token=ACCESS_TOKEN
   * <pre>
   * @param goodsIds 商品ID集
   * @return 商品状态信息
   * @throws WxErrorException .
   */
  WxMaLiveResult getGoodsWareHouse(List<Integer> goodsIds) throws WxErrorException;

  /**
   * 获取商品列表
   * <pre>
   * 调用此接口可获取商品列表
   * 调用额度：10000次/一天
   * http请求方式：GET https://api.weixin.qq.com/wxaapi/broadcast/goods/getapproved?access_token=ACCESS_TOKEN
   * <pre>
   * @param offset 分页条数起点
   * @param limit  分页大小，默认30，不超过100
   * @param status 商品状态，0：未审核。1：审核中，2：审核通过，3：审核驳回
   * @return 商品列表
   * @throws WxErrorException .
   */
  WxMaLiveResult getApprovedGoods(Integer offset, Integer limit, Integer status) throws WxErrorException;
}
