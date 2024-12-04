package cn.binarywang.wx.miniapp.api.impl;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.promoter.request.*;
import cn.binarywang.wx.miniapp.bean.promoter.response.*;
import com.google.inject.Inject;
import me.chanjar.weixin.common.error.WxErrorException;
import org.testng.annotations.Test;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

public class WxMaPromotionServiceTest {

  @Inject
  private WxMaService wxService;

  @Test
  public void testAddRole() throws WxErrorException {
    WxMaPromotionAddRoleRequest request = WxMaPromotionAddRoleRequest.builder()
      .name("推广员1号名字")
      .desc("推广员1号描述")
      .build();
    WxMaPromotionAddRoleResponse response = wxService.getWxMaPromotionService().addRole(request);
    assertThat(response).isNotNull();
  }

  @Test
  public void testGetRole() throws WxErrorException {
    WxMaPromotionGetRoleRequest request = WxMaPromotionGetRoleRequest.builder()
      .roleId(1L)
      .build();
    WxMaPromotionGetRoleResponse response = wxService.getWxMaPromotionService().getRole(request);
    assertThat(response).isNotNull();
  }

  @Test
  public void testUpdateRole() throws WxErrorException {
    WxMaPromoterUpdateRoleRequest request = WxMaPromoterUpdateRoleRequest.builder()
      .roleId(1L)
      .name("推广员1号名字")
      .desc("推广员1号描述")
      .build();
    WxMaPromotionUpdateRoleResponse response = wxService.getWxMaPromotionService().updateRole(request);
    assertThat(response).isNotNull();
  }

  @Test
  public void testAddPromoter() throws WxErrorException {
    WxMaPromotionAddPromoterRequest.Promoter promoter = WxMaPromotionAddPromoterRequest.Promoter.builder()
      .phone("15600000000")
      .openid("")
      .extraInfo("{}")
      .retailId("1")
      .roleId(1L)
      .name("15600000000")
      .build();

    WxMaPromotionAddPromoterRequest request = WxMaPromotionAddPromoterRequest.builder()
      .promoterList(Collections.singletonList(promoter))
      .build();
    WxMaPromotionAddPromoterResponse response = wxService.getWxMaPromotionService().addPromoter(request);
    assertThat(response).isNotNull();
  }

  @Test
  public void testGetPromoter() throws WxErrorException {
    WxMaPromotionGetPromoterRequest request = WxMaPromotionGetPromoterRequest.builder()
      .openid("")
      .roleId(1L)
      .retailId("")
      .beginTime(1715938250L)
      .endTime(1715938250L)
      .startId("")
      .needUnionid(null)
      .authStatus(null)
      .declStatus("1")
      .build();
    WxMaPromotionGetPromoterResponse response = wxService.getWxMaPromotionService().getPromoter(request);
    assertThat(response).isNotNull();
  }

  @Test
  public void testUpdatePromoter() throws WxErrorException {
    WxMaPromotionUpdatePromoterRequest request = WxMaPromotionUpdatePromoterRequest.builder()
      .id("")
      .roleId(1L)
      .retailId("")
      .extraInfo("{}")
      .name("15600000000")
      .phone("15600000000")
      .declStatus("1")
      .build();
    WxMaPromotionUpdatePromoterResponse response = wxService.getWxMaPromotionService().updatePromoter(request);
    assertThat(response).isNotNull();
  }

  @Test
  public void testGetInvitationMaterial() throws WxErrorException {
    WxMaPromotionGetInvitationMaterialRequest request = WxMaPromotionGetInvitationMaterialRequest.builder()
      .roleId(1L)
      .invitationType(0L)
      .build();
    WxMaPromotionGetInvitationMaterialResponse response = wxService.getWxMaPromotionService().getInvitationMaterial(request);
    assertThat(response).isNotNull();
  }

  @Test
  public void testSendMsg() throws WxErrorException {
    WxMaPromotionSendMsgRequest request = WxMaPromotionSendMsgRequest.builder()
      .msgType(1)
      .content("{}")
      .appid("")
      .path("")
      .listType(0L)
      .roleId(null)
      .retailId(null)
      .id(null)
      .build();
    WxMaPromotionSendMsgResponse response = wxService.getWxMaPromotionService().sendMsg(request);
    assertThat(response).isNotNull();
  }

  @Test
  public void testSingleSendMsg() throws WxErrorException {
    WxMaPromotionSingleSendMsgRequest request = WxMaPromotionSingleSendMsgRequest.builder()
      .msgType(1)
      .content("{}")
      .appid("")
      .path("")
      .openid("")
      .build();
    WxMaPromotionSingleSendMsgResponse response = wxService.getWxMaPromotionService().singleSendMsg(request);
    assertThat(response).isNotNull();
  }

  @Test
  public void testGetMsg() throws WxErrorException {
    WxMaPromotionGetMsgRequest request = WxMaPromotionGetMsgRequest.builder()
      .msgId("")
      .build();
    WxMaPromotionGetMsgResponse response = wxService.getWxMaPromotionService().getMsg(request);
    assertThat(response).isNotNull();
  }

  @Test
  public void testGetMsgClickData() throws WxErrorException {
    WxMaPromotionGetMsgClickDataRequest request = WxMaPromotionGetMsgClickDataRequest.builder()
      .sendDate("2024-05-17")
      .dimonsion(0L)
      .msgType(1)
      .msgId("")
      .beginSendTime(1715938250L)
      .endSendTime(1715938250L)
      .build();
    WxMaPromotionGetMsgClickDataResponse response = wxService.getWxMaPromotionService().getMsgClickData(request);
    assertThat(response).isNotNull();
  }

  @Test
  public void testGetShareMaterial() throws WxErrorException {
    WxMaPromotionGetShareMaterialRequest request = WxMaPromotionGetShareMaterialRequest.builder()
      .path("")
      .openid("")
      .extraInfo("{}")
      .title("")
      .shareType(0L)
      .envVersion("")
      .build();
    WxMaPromotionGetShareMaterialResponse response = wxService.getWxMaPromotionService().getShareMaterial(request);
    assertThat(response).isNotNull();
  }

  @Test
  public void testGetRelation() throws WxErrorException {
    WxMaPromotionGetRelationRequest request = WxMaPromotionGetRelationRequest.builder()
      .openid("")
      .beginTime(1715938250L)
      .endTime(1715938250L)
      .scene(0L)
      .path("")
      .startId("")
      .needUnionid(0L)
      .build();
    WxMaPromotionGetRelationResponse response = wxService.getWxMaPromotionService().getRelation(request);
    assertThat(response).isNotNull();
  }

  @Test
  public void testGetOrder() throws WxErrorException {
    WxMaPromotionGetOrderRequest request = WxMaPromotionGetOrderRequest.builder()
      .openid("")
      .mchId("")
      .tradeNo("")
      .outTradeNo("")
      .status(0L)
      .startId("")
      .needUnionid(0L)
      .date(1715938250L)
      .build();
    WxMaPromotionGetOrderResponse response = wxService.getWxMaPromotionService().getOrder(request);
    assertThat(response).isNotNull();
  }
}
