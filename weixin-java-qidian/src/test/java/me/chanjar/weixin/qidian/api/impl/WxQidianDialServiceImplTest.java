package me.chanjar.weixin.qidian.api.impl;

import java.util.List;
import java.util.Optional;

import com.google.inject.Inject;

import org.testng.Assert;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.qidian.api.WxQidianService;
import me.chanjar.weixin.qidian.api.test.ApiTestModule;
import me.chanjar.weixin.qidian.bean.call.GetSwitchBoardListResponse;
import me.chanjar.weixin.qidian.bean.dial.IVRDialRequest;
import me.chanjar.weixin.qidian.bean.dial.IVRDialResponse;
import me.chanjar.weixin.qidian.bean.dial.IVRListResponse;
import me.chanjar.weixin.qidian.bean.dial.Ivr;

@Test
@Guice(modules = ApiTestModule.class)
@Slf4j
public class WxQidianDialServiceImplTest {
  @Inject
  private WxQidianService wxService;

  @Test
  public void dial() throws WxErrorException {
    // ivr
    IVRListResponse iVRListResponse = this.wxService.getDialService().getIVRList();
    Assert.assertEquals(iVRListResponse.getErrcode(), new Integer(0));
    log.info("ivr size:" + iVRListResponse.getNode().size());
    Optional<Ivr> optional = iVRListResponse.getNode().stream().filter((o) -> o.getIvr_name().equals("自动接听需求测试"))
        .findFirst();
    Assert.assertTrue(optional.isPresent());
    Ivr ivr = optional.get();
    String ivr_id = ivr.getIvr_id();
    // ivr_id = "433";

    // switch
    GetSwitchBoardListResponse getSwitchBoardListResponse = this.wxService.getCallDataService().getSwitchBoardList();
    Assert.assertEquals(getSwitchBoardListResponse.getErrcode(), new Integer(0));
    log.info("switch size:" + getSwitchBoardListResponse.getData().switchBoards().size());
    List<String> switchBoards = getSwitchBoardListResponse.getData().switchBoards();

    // ivrdial
    IVRDialRequest ivrDial = new IVRDialRequest();
    ivrDial.setPhone_number("18434399105");
    // ivrDial.setPhone_number("13811768266");
    ivrDial.setIvr_id(ivr_id);
    ivrDial.setCorp_phone_list(switchBoards);
    IVRDialResponse ivrDialResponse = this.wxService.getDialService().ivrDial(ivrDial);
    Assert.assertEquals(ivrDialResponse.getCode(), new Integer(0));
    log.info(ivrDialResponse.getCallid());
  }
}
