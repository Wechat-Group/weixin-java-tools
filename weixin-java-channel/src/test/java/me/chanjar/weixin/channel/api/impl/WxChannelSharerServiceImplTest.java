package me.chanjar.weixin.channel.api.impl;

import static org.testng.Assert.*;

import com.google.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import me.chanjar.weixin.channel.api.WxChannelService;
import me.chanjar.weixin.channel.api.WxChannelSharerService;
import me.chanjar.weixin.channel.bean.sharer.SharerBindResponse;
import me.chanjar.weixin.channel.bean.sharer.SharerInfoResponse;
import me.chanjar.weixin.channel.bean.sharer.SharerOrderParam;
import me.chanjar.weixin.channel.bean.sharer.SharerOrderResponse;
import me.chanjar.weixin.channel.bean.sharer.SharerSearchResponse;
import me.chanjar.weixin.channel.bean.sharer.SharerUnbindResponse;
import me.chanjar.weixin.channel.test.ApiTestModule;
import me.chanjar.weixin.common.error.WxErrorException;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

/**
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Guice(modules = ApiTestModule.class)
public class WxChannelSharerServiceImplTest {

  @Inject
  private WxChannelService channelService;

  @Test
  public void testBindSharer() throws WxErrorException {
    WxChannelSharerService sharerService = channelService.getSharerService();
    String username = "";
    SharerBindResponse response = sharerService.bindSharer(username);
    assertNotNull(response);
    assertTrue(response.isSuccess());
  }

  @Test
  public void testSearchSharer() throws WxErrorException {
    WxChannelSharerService sharerService = channelService.getSharerService();
    String openid = "";
    String username = "";
    SharerSearchResponse response = sharerService.searchSharer(openid, username);
    assertNotNull(response);
    assertTrue(response.isSuccess());
  }

  @Test
  public void testListSharer() throws WxErrorException {
    WxChannelSharerService sharerService = channelService.getSharerService();
    Integer page = 1;
    Integer pageSize = 10;
    Integer sharerType = 1;
    SharerInfoResponse response = sharerService.listSharer(page, pageSize, sharerType);
    assertNotNull(response);
    assertTrue(response.isSuccess());
  }

  @Test
  public void testListSharerOrder() throws WxErrorException {
    WxChannelSharerService sharerService = channelService.getSharerService();
    SharerOrderParam param = new SharerOrderParam();
    SharerOrderResponse response = sharerService.listSharerOrder(param);
    assertNotNull(response);
    assertTrue(response.isSuccess());
  }

  @Test
  public void testUnbindSharer() throws WxErrorException {
    WxChannelSharerService sharerService = channelService.getSharerService();
    List<String> openIds = new ArrayList<>(4);
    openIds.add("");
    SharerUnbindResponse response = sharerService.unbindSharer(openIds);
    assertNotNull(response);
    assertTrue(response.isSuccess());
  }
}
