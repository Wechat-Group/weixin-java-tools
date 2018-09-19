package cn.binarywang.wx.miniapp.demo;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaMessage;
import cn.binarywang.wx.miniapp.config.WxMaConfig;
import cn.binarywang.wx.miniapp.constant.WxMaConstants;
import cn.binarywang.wx.miniapp.message.WxMaMessageRouter;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

/**
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
public class WxMaPortalServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  private WxMaConfig wxMaConfig;
  private WxMaService wxMaService;
  private WxMaMessageRouter wxMaMessageRouter;

  WxMaPortalServlet(WxMaConfig wxMaConfig, WxMaService wxMaService,
                    WxMaMessageRouter wxMaMessageRouter) {
    this.wxMaConfig = wxMaConfig;
    this.wxMaService = wxMaService;
    this.wxMaMessageRouter = wxMaMessageRouter;
  }

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
    throws IOException {
    response.setContentType("text/html;charset=utf-8");
    response.setStatus(HttpServletResponse.SC_OK);

    String signature = request.getParameter("signature");
    String nonce = request.getParameter("nonce");
    String timestamp = request.getParameter("timestamp");

    if (!this.wxMaService.checkSignature(timestamp, nonce, signature)) {
      // 消息签名不正确，说明不是公众平台发过来的消息
      response.getWriter().println("非法请求");
      return;
    }

    String echoStr = request.getParameter("echostr");
    if (StringUtils.isNotBlank(echoStr)) {
      // 说明是一个仅仅用来验证的请求，回显echostr
      response.getWriter().println(echoStr);
      return;
    }

    String encryptType = request.getParameter("encrypt_type");
    final boolean isJson = Objects.equals(this.wxMaConfig.getMsgDataFormat(), WxMaConstants.MsgDataFormat.JSON);
    if (StringUtils.isBlank(encryptType)) {
      // 明文传输的消息
      WxMaMessage inMessage;
      if (isJson) {
        inMessage = WxMaMessage.fromJson(IOUtils.toString(request.getInputStream(), StandardCharsets.UTF_8));
      } else {//xml
        inMessage = WxMaMessage.fromXml(request.getInputStream());
      }

      this.wxMaMessageRouter.route(inMessage);
      response.getWriter().write("success");
      return;
    }

    if ("aes".equals(encryptType)) {
      // 是aes加密的消息
      String msgSignature = request.getParameter("msg_signature");
      WxMaMessage inMessage;
      if (isJson) {
        inMessage = WxMaMessage.fromEncryptedJson(request.getInputStream(), this.wxMaConfig);
      } else {//xml
        inMessage = WxMaMessage.fromEncryptedXml(request.getInputStream(), this.wxMaConfig, timestamp, nonce, msgSignature);
      }

      this.wxMaMessageRouter.route(inMessage);
      response.getWriter().write("success");
      return;
    }

    response.getWriter().println("不可识别的加密类型");
  }

}
