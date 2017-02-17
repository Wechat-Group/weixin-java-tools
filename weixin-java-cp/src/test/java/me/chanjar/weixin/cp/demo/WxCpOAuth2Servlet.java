package me.chanjar.weixin.cp.demo;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.cp.api.WxCpService;

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

      String[] res = this.wxCpService.oauth2getUserInfo(code);
      response.getWriter().println("<h1>result</h1>");
      response.getWriter().println(Arrays.toString(res));
    } catch (WxErrorException e) {
      e.printStackTrace();
    }

    response.getWriter().flush();
    response.getWriter().close();

  }


}
