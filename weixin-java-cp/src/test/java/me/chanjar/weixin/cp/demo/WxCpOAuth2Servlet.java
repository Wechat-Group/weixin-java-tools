package me.chanjar.weixin.cp.demo;

import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.cp.api.WxCpService;
import me.chanjar.weixin.cp.bean.WxCpOauth2UserInfo;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

public class WxCpOAuth2Servlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  protected WxCpService wxCpService;

  public WxCpOAuth2Servlet(WxCpService wxCpService) {
    this.wxCpService = wxCpService;
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

      WxCpOauth2UserInfo res = this.wxCpService.getOauth2Service().getUserInfo(code);
      response.getWriter().println("<h1>result</h1>");
      response.getWriter().println(res);
    } catch (WxErrorException e) {
      e.printStackTrace();
    }

    response.getWriter().flush();
    response.getWriter().close();

  }


}
