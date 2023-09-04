package me.chanjar.weixin.open.api;

import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.open.bean.minishopgoods.AddMinishopGoodsSPU;
import me.chanjar.weixin.open.bean.minishopgoods.GoodsCatList;
import me.chanjar.weixin.open.bean.minishopgoods.ParentCatId;
import me.chanjar.weixin.open.bean.result.WxOpenResult;

/**
 *  微信小商城 商品
 *  @author xiaojintao
 */
public interface WxOpenMinishopGoodsService {
  /**
   * 获取类目详情 接入商品前必须接口
   */
  String getMinishopGoodsCatUrl = "https://api.weixin.qq.com/product/category/get";
  /**
   * SPU接口(修改需要重新上架商品)  添加商品  POST
   */
  String addMinishopGoodsSPUUrl = "https://api.weixin.qq.com/product/spu/add";
  /**
   * SPU接口(修改需要重新上架商品)  删除商品 POST
   */
  String delMinishopGoodsSPUUrl = "https://api.weixin.qq.com/product/spu/del";
  /**
   * SPU接口(修改需要重新上架商品)  获取商品 POST
   */
  String getMinishopGoodsSPUUrl = "https://api.weixin.qq.com/product/spu/get";
  /**
   * SPU接口(修改需要重新上架商品)  获取商品列表 POST
   */
  String getListMinishopGoodsSPUURL = "https://api.weixin.qq.com/product/spu/get_list";
  /**
   * SPU接口(修改需要重新上架商品)  搜索商品 POST
   */
  String searchMinishopGoodsSPUURL = "https://api.weixin.qq.com/product/spu/search";
  /**
   * SPU接口(修改需要重新上架商品)  更新商品 POST
   */
  String updateMinishopGoodsSPUUrl = "https://api.weixin.qq.com/product/spu/update";
  /**
   * SPU接口(修改需要重新上架商品)  上架商品 POST
   */
  String listingMinishopGoodsSPUUrl = "https://api.weixin.qq.com/product/spu/listing";
  /**
   * SPU接口(修改需要重新上架商品)  下架商品 POST
   */
  String delistingMinishopGoodsSPUUrl = "https://api.weixin.qq.com/product/spu/delisting";

  /**
    * SKU接口(修改后需重新上架商品) 添加SKU POST
   */
  String addMinishopGoodsSKUUrl = "https://api.weixin.qq.com/product/sku/add";
  /**
   * SKU接口(修改后需重新上架商品) 批量添加SKU POST
   */
  String batchAddMinishopGoodsSKUUrl = "https://api.weixin.qq.com/product/sku/batch_add";
  /**
   * SKU接口(修改后需重新上架商品) 批量添加SKU POST
   */
  String delMinishopGoodsSKUUrl = "https://api.weixin.qq.com/product/sku/del";
  /**
   * SKU接口(修改后需重新上架商品) 获取SKU信息 POST
   */
  String getMinishopGoodsSKUUrl = "https://api.weixin.qq.com/product/sku/get";
  /**
   * SKU接口(修改后需重新上架商品) 批量获取SKU信息 POST
   */
  String getListMinishopGoodsSKUUrl = "https://api.weixin.qq.com/product/sku/get_list";
  /**
   * SKU接口(修改后需重新上架商品) 批量获取SKU信息 POST
   */
  String updateMinishopGoodsSKUUrl = "https://api.weixin.qq.com/product/sku/update";
  /**
   * SKU接口(修改后需重新上架商品) 更新SKU价格 POST
   */
  String updatePriceMinishopGoodsSKUUrl = "https://api.weixin.qq.com/product/sku/update_price";
  /**
   * SKU接口(修改后需重新上架商品) 更新库存 POST
   */
  String updateStockMinishopGoodsSKUUrl = "https://api.weixin.qq.com/product/stock/update";
  /**
   * SKU接口(修改后需重新上架商品) 获取库存 POST
   */
  String getStockMinishopGoodsSKUUrl = "https://api.weixin.qq.com/product/stock/get";







  /**
   * 获取 商品类目
   */
  GoodsCatList getMinishopGoodsCat(ParentCatId fCatId) throws WxErrorException;

  /**
   *  新增商品SPU
   * @param dto
   * @return
   */
  WxOpenResult addMinishopGoodsSPU(AddMinishopGoodsSPU dto) throws WxErrorException;















}
