package cn.binarywang.wx.miniapp.api.impl;

import cn.binarywang.wx.miniapp.api.WxMaLiveGoodsService;
import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.live.WxMaLiveGoodInfo;
import cn.binarywang.wx.miniapp.bean.live.WxMaLiveResult;
import cn.binarywang.wx.miniapp.util.json.WxMaGsonBuilder;
import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableMap;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import lombok.AllArgsConstructor;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.json.GsonParser;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <pre>
 *  Created by lipengjun on 2020/6/29.
 * </pre>
 *
 * @author <a href="https://github.com/lipengjun92">lipengjun (939961241@qq.com)</a>
 */
@AllArgsConstructor
public class WxMaLiveGoodsServiceImpl implements WxMaLiveGoodsService {
  private final WxMaService wxMaService;

  @Override
  public WxMaLiveResult addGoods(WxMaLiveGoodInfo goods) throws WxErrorException {
    return WxMaLiveResult.fromJson(this.wxMaService.post(ADD_GOODS,
      WxMaGsonBuilder.create().toJson(ImmutableMap.of("goodsInfo", goods))));
  }

  @Override
  public boolean resetAudit(Integer auditId, Integer goodsId) throws WxErrorException {
    Map<String, Integer> map = new HashMap<>(4);
    map.put("auditId", auditId);
    map.put("goodsId", goodsId);
    this.wxMaService.post(RESET_AUDIT_GOODS, WxMaGsonBuilder.create().toJson(map));
    return true;
  }

  @Override
  public String auditGoods(Integer goodsId) throws WxErrorException {
    Map<String, Integer> map = new HashMap<>(2);
    map.put("goodsId", goodsId);
    String responseContent = this.wxMaService.post(AUDIT_GOODS, WxMaGsonBuilder.create().toJson(map));
    JsonObject jsonObject = GsonParser.parse(responseContent);
    return jsonObject.get("auditId").getAsString();
  }

  @Override
  public boolean deleteGoods(Integer goodsId) throws WxErrorException {
    Map<String, Integer> map = new HashMap<>(2);
    map.put("goodsId", goodsId);
    this.wxMaService.post(DELETE_GOODS, WxMaGsonBuilder.create().toJson(map));
    return true;
  }

  @Override
  public boolean updateGoods(WxMaLiveGoodInfo goods) throws WxErrorException {
    Map<String, Object> map = new HashMap<>(2);
    map.put("goodsInfo", goods);
    this.wxMaService.post(UPDATE_GOODS, WxMaGsonBuilder.create().toJson(map));
    return true;
  }

  @Override
  public WxMaLiveResult getGoodsWareHouse(List<Integer> goodsIds) throws WxErrorException {
    Map<String, Object> map = new HashMap<>(2);
    map.put("goods_ids", goodsIds);
    String responseContent = this.wxMaService.post(GET_GOODS_WARE_HOUSE, WxMaGsonBuilder.create().toJson(map));
    return WxMaLiveResult.fromJson(responseContent);
  }

  @Override
  public WxMaLiveResult getApprovedGoods(Integer offset, Integer limit, Integer status) throws WxErrorException {
    ImmutableMap<String, ? extends Serializable> params = ImmutableMap.of("status", status, "offset", offset, "limit", limit);
    String responseContent = wxMaService.get(GET_APPROVED_GOODS, Joiner.on("&").withKeyValueSeparator("=").join(params));
    JsonObject jsonObject = GsonParser.parse(responseContent);
    JsonArray goodsArr = jsonObject.getAsJsonArray("goods");
    if (goodsArr.size() > 0) {
      for (int i = 0; i < goodsArr.size(); i++) {
        // 接口返回key是驼峰
        JsonObject goods = (JsonObject) goodsArr.get(i);
        goods.addProperty("goods_id", goods.get("goodsId").getAsInt());
        goods.addProperty("cover_img_url", goods.get("coverImgUrl").getAsString());
        goods.addProperty("price_type", goods.get("priceType").getAsInt());
        goods.addProperty("third_party_tag", goods.get("thirdPartyTag").getAsInt());
        goods.addProperty("audit_status", status);
      }
    }
    return WxMaLiveResult.fromJson(jsonObject.toString());
  }

}
