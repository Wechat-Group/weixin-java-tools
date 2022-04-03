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
   *
   * 文档地址：https://developers.weixin.qq.com/miniprogram/dev/framework/liveplayer/commodity-api.html#1
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

  /**
   * 直播挂件设置全局key
   * <pre>
   * 若已设置此全局key，且添加商品时未指定goodsKey字段，则我们会使用此全局key作为该商品的goodsKey。 须注意的是，若全局key已设定，并添加了未指定goodsKey字段的商品之后，再重新设定不一样的全局key则会导致先前的映射失效。 为了避免映射失效，建议全局key只设定一次。
   * 注意：key必须为字符串数组
   * 调用额度：500次/一天
   * http请求方式：POST https://api.weixin.qq.com/wxaapi/broadcast/goods/setkey?access_token=
   * </pre>
   *
   * @param goodsKey 全局key
   * @return 设置是否成功
   * @throws WxErrorException .
   */
  boolean setKey(List<String> goodsKey) throws WxErrorException;

  /**
   * 查看当前设定的全局key
   * <pre>
   * 查看当前设定的全局key。
   * 调用额度：500次/一天
   * http请求方式：GET https://api.weixin.qq.com/wxaapi/broadcast/goods/getkey?access_token=
   * </pre>
   *
   * @return 全局key
   * @throws WxErrorException .
   */
  List<String> getKey() throws WxErrorException;
}
