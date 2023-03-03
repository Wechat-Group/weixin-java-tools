package me.chanjar.weixin.cp.api.impl;

import com.google.gson.JsonObject;
import com.google.inject.Inject;
import me.chanjar.weixin.common.bean.WxAccessToken;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.cp.api.ApiTestModule;
import me.chanjar.weixin.cp.api.WxCpService;
import me.chanjar.weixin.cp.bean.corpgroup.*;
import me.chanjar.weixin.cp.corpgroup.service.WxCpCgService;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import java.util.List;

import static me.chanjar.weixin.cp.constant.WxCpApiPathConsts.CorpGroup.CORP_GET_TOKEN;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

/**
 * @Project: WxJava
 * @Package: me.chanjar.weixin.cp.api.impl
 * @Description:
 * @Author: libo
 * @Email: 422423229@qq.com
 * @Date: 28/2/2023 7:06 PM
 */
@Guice(modules = ApiTestModule.class)
public class WxCpCorpGroupServiceImplTest {
  @Inject
  private WxCpService wxService;

  @Test
  public void testListAppShareInfo() throws WxErrorException {
    Integer agentId = wxService.getWxCpConfigStorage().getAgentId();
    Integer businessType = 0;
    String corpId = null;
    Integer limit = null;
    String cursor = null;
    List<WxCpCorpGroupCorp> resp = wxService.getCorpGroupService().listAppShareInfo(agentId, businessType, corpId, limit, cursor);
    assertNotNull(resp);
  }
}
