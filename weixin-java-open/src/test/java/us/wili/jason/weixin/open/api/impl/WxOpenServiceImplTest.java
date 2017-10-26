package us.wili.jason.weixin.open.api.impl;

import com.google.inject.Inject;
import org.testng.AssertJUnit;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;
import us.wili.jason.weixin.open.api.WxOpenService;
import us.wili.jason.weixin.open.api.test.ApiTestModule;
import us.wili.jason.weixin.open.api.test.TestConfigStorage;

import static org.testng.Assert.assertNotNull;

@Test
@Guice(modules = ApiTestModule.class)
public class WxOpenServiceImplTest {

  @Inject
  private WxOpenService wxOpenService;
  @Inject
  private TestConfigStorage testConfigStorage;

  @Test
  public void testGetComponentAccessTokenForceRefresh() throws Exception {
    String componentAccessToken = this.wxOpenService.getComponentAccessToken(true);
    AssertJUnit.assertNotNull(componentAccessToken);
    System.out.println(componentAccessToken);
  }

  @Test
  public void testGetComponentAccessToken() throws Exception {
    String componentAccessToken = this.wxOpenService.getComponentAccessToken();
    AssertJUnit.assertEquals(componentAccessToken, this.wxOpenService.getComponentAccessToken());
  }

  @Test
  public void testGetAuthorizerAccessTokenForceRefresh() throws Exception {
    String accessToken = this.wxOpenService.getAuthorizerAccessToken(testConfigStorage.getAuthorizerAppid());
    AssertJUnit.assertNotNull(accessToken);
    System.out.println(accessToken);
  }

  @Test
  public void testGetAuthorizerAccessToken() throws Exception {
    String accessToken = this.wxOpenService.getAuthorizerAccessToken(testConfigStorage.getAuthorizerAppid());
    AssertJUnit.assertEquals(accessToken, this.wxOpenService.getAuthorizerAccessToken(testConfigStorage.getAuthorizerAppid()));
  }
}
