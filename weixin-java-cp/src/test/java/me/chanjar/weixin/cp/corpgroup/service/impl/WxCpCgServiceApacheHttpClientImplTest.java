package me.chanjar.weixin.cp.corpgroup.service.impl;

import com.google.inject.Inject;
import me.chanjar.weixin.common.bean.WxAccessToken;
import me.chanjar.weixin.cp.api.ApiTestModule;
import me.chanjar.weixin.cp.api.WxCpService;
import me.chanjar.weixin.cp.api.impl.WxCpServiceApacheHttpClientImpl;
import me.chanjar.weixin.cp.bean.WxCpAgent;
import me.chanjar.weixin.cp.bean.WxCpUser;
import me.chanjar.weixin.cp.bean.corpgroup.WxCpCorpGroupCorpGetTokenReq;
import me.chanjar.weixin.cp.config.WxCpCorpGroupConfigStorage;
import me.chanjar.weixin.cp.config.impl.WxCpCorpGroupDefaultConfigImpl;
import me.chanjar.weixin.cp.config.impl.WxCpCorpGroupRedissonConfigImpl;
import me.chanjar.weixin.cp.constant.WxCpApiPathConsts;
import me.chanjar.weixin.cp.corpgroup.service.WxCpCgService;

import org.mockito.Mockito;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import static org.testng.Assert.assertNotNull;

/**
 * @Project: WxJava
 * @Package: me.chanjar.weixin.cp.corpgroup.service.impl
 * @Description:
 * @Author: libo
 * @Email: 422423229@qq.com
 * @Date: 2/3/2023 4:00 PM
 */
@Guice(modules = ApiTestModule.class)
public class WxCpCgServiceApacheHttpClientImplTest {
  private final WxCpCgService cgService = Mockito.spy(new WxCpCgServiceApacheHttpClientImpl());
  WxCpCorpGroupConfigStorage wxCpCorpGroupConfigStorage;
  @Inject
  WxCpService wxCpService;

  @BeforeMethod
  public void setUp() {
    cgService.setWxCpCorpGroupConfigStorage(wxCpCorpGroupConfigStorage());
    cgService.setWxCpService(wxCpService);
  }

  public WxCpCorpGroupConfigStorage wxCpCorpGroupConfigStorage() {
    wxCpCorpGroupConfigStorage = new WxCpCorpGroupDefaultConfigImpl();
    wxCpCorpGroupConfigStorage.setCorpId(wxCpService.getWxCpConfigStorage().getCorpId());
    wxCpCorpGroupConfigStorage.setAgentId(wxCpService.getWxCpConfigStorage().getAgentId());
    return wxCpCorpGroupConfigStorage;
  }

  @Test
  public void testGetCorpAccessToken() throws Exception {
    //下游企业的corpId
    String corpId = "ww7a964c28b8187ec4";
    //下游企业的agentId
    int agentId = 1000016;
    int businessType = 0;
    String corpAccessToken = cgService.getCorpAccessToken(corpId, agentId, businessType);
    assertNotNull(corpAccessToken);
  }

  /**
   * 通讯录-读取成员
   *
   * @throws Exception
   */
  @Test
  public void testGetCorpUser() throws Exception {
    //下游企业的corpId
    String corpId = "";
    //下游企业的agentId
    int agentId = 0;
    int businessType = 0;
    //成员UserID
    String corpUserId = "";
    //通讯录读取成员
    WxCpCorpGroupCorpGetTokenReq wxCpCorpGroupCorpGetTokenReq = new WxCpCorpGroupCorpGetTokenReq();
    wxCpCorpGroupCorpGetTokenReq.setCorpId(corpId);
    wxCpCorpGroupCorpGetTokenReq.setAgentId(agentId);
    wxCpCorpGroupCorpGetTokenReq.setBusinessType(businessType);
    String result = cgService.get(wxCpService.getWxCpConfigStorage().getApiUrl(WxCpApiPathConsts.User.USER_GET + corpUserId), null, wxCpCorpGroupCorpGetTokenReq);
    assertNotNull(result);
    WxCpUser wxCpUser = WxCpUser.fromJson(result);
    assertNotNull(wxCpUser.getUserId());
  }

  /**
   * 应用管理-获取指定的应用详情
   *
   * @throws Exception
   */
  @Test
  public void testGetAgent() throws Exception {
    //下游企业的corpId
    String corpId = "";
    //下游企业的agentId
    int agentId = 0;
    int businessType = 0;

    WxCpCorpGroupCorpGetTokenReq wxCpCorpGroupCorpGetTokenReq = new WxCpCorpGroupCorpGetTokenReq();
    wxCpCorpGroupCorpGetTokenReq.setCorpId(corpId);
    wxCpCorpGroupCorpGetTokenReq.setAgentId(agentId);
    wxCpCorpGroupCorpGetTokenReq.setBusinessType(businessType);
    String result = cgService.get(wxCpService.getWxCpConfigStorage().getApiUrl(String.format(WxCpApiPathConsts.Agent.AGENT_GET, agentId)), null, wxCpCorpGroupCorpGetTokenReq);
    assertNotNull(result);
    WxCpAgent wxCpAgent = WxCpAgent.fromJson(result);
    assertNotNull(wxCpAgent.getAgentId());
  }
}
