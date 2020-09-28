package me.chanjar.weixin.cp.demo;

import me.chanjar.weixin.cp.config.WxCpConfigStorage;
import me.chanjar.weixin.cp.message.WxCpMessageRouter;
import me.chanjar.weixin.cp.api.WxCpService;
import me.chanjar.weixin.cp.bean.message.WxCpXmlMessage;
import me.chanjar.weixin.cp.bean.message.WxCpXmlOutMessage;
import me.chanjar.weixin.cp.util.crypto.WxCpCryptUtil;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Daniel Qian
 */
public class WxCpEndpointServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;
  protected WxCpConfigStorage wxCpConfigStorage;
  protected WxCpService wxCpService;
  protected WxCpMessageRouter wxCpMessageRouter;

  public WxCpEndpointServlet(WxCpConfigStorage wxCpConfigStorage, WxCpService wxCpService,
                             WxCpMessageRouter wxCpMessageRouter) {
    this.wxCpConfigStorage = wxCpConfigStorage;
    this.wxCpService = wxCpService;
    this.wxCpMessageRouter = wxCpMessageRouter;
  }

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
    throws IOException {

    response.setContentType("text/html;charset=utf-8");
    response.setStatus(HttpServletResponse.SC_OK);

    String msgSignature = request.getParameter("msg_signature");
    String nonce = request.getParameter("nonce");
    String timestamp = request.getParameter("timestamp");
    String echostr = request.getParameter("echostr");

    if (StringUtils.isNotBlank(echostr)) {
      if (!this.wxCpService.checkSignature(msgSignature, timestamp, nonce, echostr)) {
        // 消息签名不正确，说明不是公众平台发过来的消息
        response.getWriter().println("非法请求");
        return;
      }
      WxCpCryptUtil cryptUtil = new WxCpCryptUtil(this.wxCpConfigStorage);
      String plainText = cryptUtil.decrypt(echostr);
      // 说明是一个仅仅用来验证的请求，回显echostr
      response.getWriter().println(plainText);
      return;
    }

    WxCpXmlMessage inMessage = WxCpXmlMessage
      .fromEncryptedXml(request.getInputStream(), this.wxCpConfigStorage, timestamp, nonce, msgSignature);
    WxCpXmlOutMessage outMessage = this.wxCpMessageRouter.route(inMessage);
    if (outMessage != null) {
      response.getWriter().write(outMessage.toEncryptedXml(this.wxCpConfigStorage));
    }

  }

}
