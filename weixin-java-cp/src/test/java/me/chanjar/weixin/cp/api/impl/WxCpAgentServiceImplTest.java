package me.chanjar.weixin.cp.api.impl;

import me.chanjar.weixin.cp.api.WxCpAgentService;
import me.chanjar.weixin.cp.api.WxCpService;
import me.chanjar.weixin.cp.bean.WxCpAgent;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


/**
 * <pre>
 *  管理企业号应用-测试
 *  Created by huansinho on 2018/4/13.
 * </pre>
 *
 * @author <a href="https://github.com/huansinho">huansinho</a>
 */
public class WxCpAgentServiceImplTest {

  protected WxCpService wxService = mock(WxCpService.class);

  @Test
  public void testGet() throws Exception {
    String returnJson = "{\"errcode\": 0,\"errmsg\": \"ok\",\"agentid\": 9,\"name\": \"测试应用\",\"square_logo_url\": \"http://wx.qlogo.cn/mmhead/alksjf;lasdjf;lasjfuodiuj3rj2o34j/0\",\"description\": \"这是一个企业号应用\",\"allow_userinfos\": {\"user\": [{\"userid\": \"0009854\"}, {\"userid\": \"1723\"}, {\"userid\": \"5625\"}]},\"allow_partys\": {\"partyid\": [42762742]},\"allow_tags\": {\"tagid\": [23, 22, 35, 19, 32, 125, 133, 46, 150, 38, 183, 9, 7]},\"close\": 0,\"redirect_domain\": \"weixin.com.cn\",\"report_location_flag\": 0,\"isreportenter\": 0,\"home_url\": \"\"}";
    when(wxService.get("https://qyapi.weixin.qq.com/cgi-bin/agent/get?agentid=9", null)).thenReturn(returnJson);
    when(wxService.getAgentService()).thenReturn(new WxCpAgentServiceImpl(wxService));

    WxCpAgentService wxAgentService = this.wxService.getAgentService();
    WxCpAgent wxCpAgent = wxAgentService.get(9);

    Assert.assertEquals(9, wxCpAgent.getAgentId().intValue());

    Assert.assertEquals(new Integer[]{42762742}, wxCpAgent.getAllowParties().getPartyIds().toArray());

    Assert.assertEquals(new Integer[]{23, 22, 35, 19, 32, 125, 133, 46, 150, 38, 183, 9, 7}, wxCpAgent.getAllowTags().getTagIds().toArray());

  }

}
