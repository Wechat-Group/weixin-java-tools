package cn.binarywang.wx.miniapp.api.impl;

import cn.binarywang.wx.miniapp.api.WxMaSafetyRiskControlService;
import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.safety.request.WxMaUserSafetyRiskRankRequest;
import cn.binarywang.wx.miniapp.bean.safety.response.WxMaUserSafetyRiskRankResponse;
import cn.binarywang.wx.miniapp.constant.WxMaConstants;
import com.google.gson.JsonObject;
import lombok.RequiredArgsConstructor;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.enums.WxType;
import me.chanjar.weixin.common.error.WxError;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.json.GsonParser;

import static cn.binarywang.wx.miniapp.constant.WxMaApiUrlConstants.InstantDelivery.SafetyRiskControl.GET_USER_RISK_RANK;

/**
 * @author azouever
 */

@RequiredArgsConstructor
public class WxMaSafetyRiskControlServiceImpl implements WxMaSafetyRiskControlService {

  private final WxMaService service;

  @Override
  public WxMaUserSafetyRiskRankResponse getUserRiskRank(WxMaUserSafetyRiskRankRequest wxMaUserSafetyRiskRankRequest) throws WxErrorException {
    String responseContent = this.service.post(GET_USER_RISK_RANK, wxMaUserSafetyRiskRankRequest.toJson());
    JsonObject jsonObject = GsonParser.parse(responseContent);
    if (jsonObject.get(WxConsts.ERR_CODE).getAsInt() != 0) {
      throw new WxErrorException(WxError.fromJson(responseContent, WxType.MiniApp));
    }
    return WxMaUserSafetyRiskRankResponse.fromJson(responseContent);
  }
}
