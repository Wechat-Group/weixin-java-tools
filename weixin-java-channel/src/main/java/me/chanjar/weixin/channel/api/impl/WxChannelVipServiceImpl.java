package me.chanjar.weixin.channel.api.impl;

import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.channel.api.WxChannelVipService;
import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;
import me.chanjar.weixin.channel.bean.vip.*;
import me.chanjar.weixin.channel.util.ResponseUtils;
import me.chanjar.weixin.common.error.WxErrorException;

import static me.chanjar.weixin.channel.constant.WxChannelApiUrlConstants.Vip.*;

/**
 * 视频号小店 会员功能接口
 *
 * @author <a href="https://github.com/asushiye">aushiye</a>
 * @link <a href="https://developers.weixin.qq.com/doc/channels/API/vip/access_guide.html">会员功能接口文档</a>
 */

@Slf4j
public class WxChannelVipServiceImpl implements WxChannelVipService {
  private BaseWxChannelServiceImpl vipHttpService;

  public WxChannelVipServiceImpl(BaseWxChannelServiceImpl vipHttpService) {
    this.vipHttpService = vipHttpService;
  }

  @Override
  public VipInfoResponse getVipInfo(String openId, Boolean needPhoneNumber) throws WxErrorException {
    VipInfoParam param = new VipInfoParam(openId, needPhoneNumber);
    String respJson = vipHttpService.post(VIP_USER_INFO_URL, param);
    return ResponseUtils.decode(respJson, VipInfoResponse.class);
  }

  @Override
  public VipListResponse getVipList(Boolean needPhoneNumber, Integer pageNum, Integer PageSize) throws WxErrorException {
    VipListParam param = new VipListParam(needPhoneNumber, pageNum, PageSize);
    String respJson = vipHttpService.post(VIP_USER_LIST_URL, param);
    return ResponseUtils.decode(respJson, VipListResponse.class);
  }

  @Override
  public VipScoreResponse getVipScore(String openId) throws WxErrorException {
    VipParam param = new VipParam(openId);
    String respJson = vipHttpService.post(VIP_SCORE_URL, param);
    return ResponseUtils.decode(respJson, VipScoreResponse.class);
  }

  @Override
  public WxChannelBaseResponse increaseVipScore(String openId, String score, String remark, String requestId) throws WxErrorException {
    VipScoreParam param = new VipScoreParam(openId, score, remark, requestId);
    String respJson = vipHttpService.post(SCORE_INCREASE_URL, param);
    return ResponseUtils.decode(respJson, WxChannelBaseResponse.class);
  }

  @Override
  public WxChannelBaseResponse decreaseVipScore(String openId, String score, String remark, String requestId) throws WxErrorException {
    VipScoreParam param = new VipScoreParam(openId, score, remark, requestId);
    String respJson = vipHttpService.post(SCORE_DECREASE_URL, param);
    return ResponseUtils.decode(respJson, WxChannelBaseResponse.class);
  }

  @Override
  public WxChannelBaseResponse updateVipGrade(String openId, Integer score) throws WxErrorException {
    VipGradeParam param = new VipGradeParam(openId, score);
    String respJson = vipHttpService.post(GRADE_UPDATE_URL, param);
    return ResponseUtils.decode(respJson, WxChannelBaseResponse.class);
  }
}
