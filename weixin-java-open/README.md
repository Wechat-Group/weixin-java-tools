消息机制未实现，下面为通知回调中设置的代码部分
```
@RestController
@RequestMapping("notify")
public class NotifyController extends WechatThridBaseController {
    @Autowired
    protected WxOpenServiceDemo wxOpenService;
    @RequestMapping("receive_ticket")
    public Object receiveTicket(@RequestBody(required = false) String requestBody, @RequestParam("timestamp") String timestamp,
                                @RequestParam("nonce") String nonce, @RequestParam("signature") String signature,
                                @RequestParam(name = "encrypt_type", required = false) String encType,
                                @RequestParam(name = "msg_signature", required = false) String msgSignature) {
        this.logger.info(
                "\n接收微信请求：[signature=[{}], encType=[{}], msgSignature=[{}],"
                        + " timestamp=[{}], nonce=[{}], requestBody=[\n{}\n] ",
                signature, encType, msgSignature, timestamp, nonce, requestBody);

        if (!StringUtils.equalsIgnoreCase("aes", encType) || !wxOpenService.getWxOpenComponentService().checkSignature(timestamp, nonce, signature)) {
            throw new IllegalArgumentException("非法请求，可能属于伪造的请求！");
        }

        String out = "";
        // aes加密的消息
        WxOpenXmlMessage inMessage = WxOpenXmlMessage.fromEncryptedXml(requestBody, wxOpenService.getWxOpenConfigStorage(), timestamp, nonce, msgSignature);
        this.logger.debug("\n消息解密后内容为：\n{} ", inMessage.toString());
        if (StringUtils.equalsIgnoreCase(inMessage.getInfoType(), "component_verify_ticket")) {
            wxOpenService.getWxOpenComponentService().getWxOpenConfigStorage().setComponentVerifyTicket(inMessage.getComponentVerifyTicket());
            out = "success";
        }
        //新增、跟新授权
        if (StringUtils.equalsAnyIgnoreCase(inMessage.getInfoType(), "authorized", "updateauthorized")) {
            try {
                WxOpenQueryAuthResult queryAuth = wxOpenService.getWxOpenComponentService().getQueryAuth(inMessage.getAuthorizationCode());
                WxOpenAuthorizationInfo authorizationInfo = queryAuth.getAuthorizationInfo();
                wxOpenService.getWxOpenConfigStorage().updateAuthorizerAccessToken(authorizationInfo.getAuthorizerAppid(),
                                        authorizationInfo.getAuthorizerAccessToken(), authorizationInfo.getExpiresIn());
                wxOpenService.getWxOpenConfigStorage().setAuthorizerRefreshToken(authorizationInfo.getAuthorizerAppid(), authorizationInfo.getAuthorizerRefreshToken());
                out = "success";
            } catch (WxErrorException e) {
                throw new ResponseException(ErrorCodeEnum.ERROR, e);
            }
        }
        //取消授权
        if (StringUtils.equalsIgnoreCase(inMessage.getInfoType(), "unauthorized")) {

        }

//        WxMpXmlOutMessage outMessage = this.getWxService().route(inMessage);
//        if (outMessage == null) {
//            return "";
//        }
//
//        out = outMessage.toEncryptedXml(wxOpenService.getWxOpenConfigStorage());


        this.logger.debug("\n组装回复信息：{}", out);

        return out;
    }
    @RequestMapping("{appId}/callback")
    public Object callback(@RequestBody(required = false)String requestBody,
                           @PathVariable ("appId") String appId,
                           @RequestParam("signature") String signature,
                           @RequestParam("timestamp") String timestamp,
                           @RequestParam("nonce") String nonce,
                           @RequestParam("openid") String openid,
                           @RequestParam("encrypt_type") String encType,
                           @RequestParam("msg_signature") String msgSignature) {
        this.logger.info(
                "\n接收微信请求：[appId=[{}], openid=[{}], signature=[{}], encType=[{}], msgSignature=[{}],"
                        + " timestamp=[{}], nonce=[{}], requestBody=[\n{}\n] ",
                appId, openid, signature, encType, msgSignature, timestamp, nonce, requestBody);
        logger.info("query:"+getHttpServletRequest().getQueryString()+"\nbody:"+requestBody);
        if (!StringUtils.equalsIgnoreCase("aes", encType) || !wxOpenService.getWxOpenComponentService().checkSignature(timestamp, nonce, signature)) {
            throw new IllegalArgumentException("非法请求，可能属于伪造的请求！");
        }

        String out = "";
        // aes加密的消息
        WxMpXmlMessage inMessage = WxOpenXmlMessage.fromEncryptedMpXml(requestBody, wxOpenService.getWxOpenConfigStorage(), timestamp, nonce, msgSignature);
        this.logger.debug("\n消息解密后内容为：\n{} ", inMessage.toString());
        //wxOpenService.getWxOpenComponentService().getWxMpServiceByAppid(appId);
        return out;
    }
}
```
