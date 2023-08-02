package me.chanjar.weixin.channel.api.impl;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import com.google.inject.Inject;
import me.chanjar.weixin.channel.api.WxChannelService;
import me.chanjar.weixin.channel.api.WxLeaguePromoterService;
import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;
import me.chanjar.weixin.channel.bean.league.promoter.PromoterInfoResponse;
import me.chanjar.weixin.channel.bean.league.promoter.PromoterListResponse;
import me.chanjar.weixin.channel.test.ApiTestModule;
import me.chanjar.weixin.common.error.WxErrorException;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

/**
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Guice(modules = ApiTestModule.class)
public class WxLeaguePromoterServiceImplTest {

  @Inject
  private WxChannelService channelService;

  @Test
  public void testAddPromoter() throws WxErrorException {
    WxLeaguePromoterService leaguePromoterService = channelService.getLeaguePromoterService();
    String finderId = "";
    WxChannelBaseResponse response = leaguePromoterService.addPromoter(finderId);
    assertNotNull(response);
    assertTrue(response.isSuccess());
  }

  @Test
  public void testUpdatePromoter() throws WxErrorException {
    WxLeaguePromoterService leaguePromoterService = channelService.getLeaguePromoterService();
    String finderId = "";
    int type = 1;
    WxChannelBaseResponse response = leaguePromoterService.updatePromoter(finderId, type);
    assertNotNull(response);
    assertTrue(response.isSuccess());
  }

  @Test
  public void testDeletePromoter() throws WxErrorException {
    WxLeaguePromoterService leaguePromoterService = channelService.getLeaguePromoterService();
    String finderId = "";
    WxChannelBaseResponse response = leaguePromoterService.deletePromoter(finderId);
    assertNotNull(response);
    assertTrue(response.isSuccess());
  }

  @Test
  public void testGetPromoterInfo() throws WxErrorException {
    WxLeaguePromoterService leaguePromoterService = channelService.getLeaguePromoterService();
    String finderId = "";
    PromoterInfoResponse response = leaguePromoterService.getPromoterInfo(finderId);
    assertNotNull(response);
    assertTrue(response.isSuccess());
  }

  @Test
  public void testListPromoter() throws WxErrorException {
    WxLeaguePromoterService leaguePromoterService = channelService.getLeaguePromoterService();
    Integer pageIndex = 1;
    Integer pageSize = 10;
    Integer status = null;
    PromoterListResponse response = leaguePromoterService.listPromoter(pageIndex, pageSize, status);
    assertNotNull(response);
    assertTrue(response.isSuccess());
  }
}
