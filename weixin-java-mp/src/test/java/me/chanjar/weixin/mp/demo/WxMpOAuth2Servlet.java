package me.chanjar.weixin.mp.demo;

import me.chanjar.weixin.common.bean.WxOAuth2UserInfo;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.common.bean.oauth2.WxOAuth2AccessToken;
import me.chanjar.weixin.mp.bean.result.WxMpUser;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class WxMpOAuth2Servlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  protected WxMpService wxMpService;

  public WxMpOAuth2Servlet(WxMpService wxMpService) {
    this.wxMpService = wxMpService;
  }

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
    throws IOException {

    response.setContentType("text/html;charset=utf-8");
    response.setStatus(HttpServletResponse.SC_OK);

    String code = request.getParameter("code");
    try {
      response.getWriter().println("<h1>code</h1>");
      response.getWriter().println(code);

      WxOAuth2AccessToken oAuth2AccessToken = this.wxMpService.getOAuth2Service().getAccessToken(code);
      response.getWriter().println("<h1>access token</h1>");
      response.getWriter().println(oAuth2AccessToken.toString());

      WxOAuth2UserInfo wxMpUser = this.wxMpService.getOAuth2Service().getUserInfo(oAuth2AccessToken, null);
      response.getWriter().println("<h1>user info</h1>");
      response.getWriter().println(wxMpUser.toString());

      oAuth2AccessToken = this.wxMpService.getOAuth2Service().refreshAccessToken(oAuth2AccessToken.getRefreshToken());
      response.getWriter().println("<h1>after refresh</h1>");
      response.getWriter().println(oAuth2AccessToken.toString());

    } catch (WxErrorException e) {
      e.printStackTrace();
    }

    response.getWriter().flush();
    response.getWriter().close();

  }

}
