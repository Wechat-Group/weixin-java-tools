package me.chanjar.weixin.mp.demo;

import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.mp.config.WxMpConfigStorage;
import me.chanjar.weixin.mp.api.WxMpMessageHandler;
import me.chanjar.weixin.mp.api.WxMpMessageRouter;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceHttpClientImpl;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import java.io.IOException;
import java.io.InputStream;

public class WxMpDemoServer {

  private static WxMpConfigStorage wxMpConfigStorage;
  private static WxMpService wxMpService;
  private static WxMpMessageRouter wxMpMessageRouter;

  public static void main(String[] args) throws Exception {
    initWeixin();

    Server server = new Server(8080);

    ServletHandler servletHandler = new ServletHandler();
    server.setHandler(servletHandler);

    ServletHolder endpointServletHolder = new ServletHolder(new WxMpEndpointServlet(wxMpConfigStorage, wxMpService,
      wxMpMessageRouter));
    servletHandler.addServletWithMapping(endpointServletHolder, "/*");

    ServletHolder oauthServletHolder = new ServletHolder(new WxMpOAuth2Servlet(wxMpService));
    servletHandler.addServletWithMapping(oauthServletHolder, "/oauth2/*");

    server.start();
    server.join();
  }

  private static void initWeixin() {
    try (InputStream is1 = ClassLoader.getSystemResourceAsStream("test-config.xml")) {
      WxMpDemoInMemoryConfigStorage config = WxMpDemoInMemoryConfigStorage.fromXml(is1);

      wxMpConfigStorage = config;
      wxMpService = new WxMpServiceHttpClientImpl();
      wxMpService.setWxMpConfigStorage(config);

      WxMpMessageHandler logHandler = new DemoLogHandler();
      WxMpMessageHandler textHandler = new DemoTextHandler();
      WxMpMessageHandler imageHandler = new DemoImageHandler();
      WxMpMessageHandler oauth2handler = new DemoOAuth2Handler();
      DemoGuessNumberHandler guessNumberHandler = new DemoGuessNumberHandler();

      wxMpMessageRouter = new WxMpMessageRouter(wxMpService);
      wxMpMessageRouter.rule().handler(logHandler).next().rule()
        .msgType(WxConsts.XmlMsgType.TEXT).matcher(guessNumberHandler)
        .handler(guessNumberHandler).end().rule().async(false).content("哈哈")
        .handler(textHandler).end().rule().async(false).content("图片")
        .handler(imageHandler).end().rule().async(false).content("oauth")
        .handler(oauth2handler).end();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
