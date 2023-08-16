# weixin-java-config
1.目录说明：多配置文件目录

2.项目多配置集锦
```yml
wechat:
  pay: #微信服务商支付
    configs:
    - appId: wxe97b2x9c2b3d #spAppId
      mchId: 16486610 #服务商商户
      subAppId: wx118cexxe3c07679 #子appId
      subMchId: 16496705 #子商户
      apiV3Key: Dc1DBwSc094jAKDGR5aqqb7PTHr #apiV3密钥
      privateKeyPath: classpath:cert/apiclient_key.pem #服务商证书文件，apiclient_key.pem证书文件的绝对路径或者以classpath:开头的类路径（可以配置绝对路径）
      privateCertPath: classpath:cert/apiclient_cert.pem #apiclient_cert.pem证书文件的绝对路径或者以classpath:开头的类路径
  miniapp: #小程序
    configs:
    - appid: wx118ce3xxc76ccg
      secret: 8a132a276ee2f8fb58b1ed8f2
      token: #微信小程序消息服务器配置的token
      aesKey: #微信小程序消息服务器配置的EncodingAESKey
      msgDataFormat: JSON
  cp: #企业微信
    corpId: wwa3be8efd2addfgj
    appConfigs:
    - agentId: 10001 #客户联系
      secret: T5fTj1n-sBAT4rKNW5c9IYNfPdXZ8-oGol5tX
      token: 2bSNqTcLtFYBUa1u2
      aesKey: AXazu2Xyw44SNY1x8go2phn9p9B2O9oiEfqPN
    - agentId: 10003 #会话内容存档
      secret: xIpum7Yt4NMXcyxdzcQ2l_46BG4QIQDR57MhA
      token:
      aesKey:
    - agentId: 3010011 #打卡
      secret: 3i2Mhfusifaw_-04bMYI8OoKGxPe9mDALbUxV
      token:
      aesKey:
    - agentId: 19998 #通讯录同步
      secret: rNyDae0Pg-3d-wqTd_ozMSJfF0DEjTCz3b_pr
      token: xUke8yZciAZqImGZ
      aesKey: EUTVyArqJcfnpFiudxjRpuOexNqBoPbwrNG3R
    - agentId: 20000 #微盘
      secret: D-TVMvUji7PZZdjhZOSgiy2MTuBd0OCdvI_zi
      token:
      aesKey:
```

3.主要代码
###### 1）微信服务商支付
```java
@Data
@ConfigurationProperties(prefix = "wechat.pay")
public class WxPayProperties {

    private List<Config> configs;

    @Getter
    @Setter
    public static class Config {

        private String appId;
        private String mchId;
        private String subAppId;
        private String subMchId;
        private String apiV3Key;
        private String privateKeyPath;
        private String privateCertPath;

    }

}
```
```java
@Configuration
@EnableConfigurationProperties(WxPayProperties.class)
@AllArgsConstructor
public class WxPayConfiguration {

    private WxPayProperties properties;

    @Bean
    public WxPayService wxPayService() {

        // 多配置
        WxPayService wxPayService = new WxPayServiceImpl();
        Map<String, WxPayConfig> payConfigs = this.properties.getConfigs().stream().map(config -> {
            WxPayConfig payConfig = new WxPayConfig();
            payConfig.setAppId(StringUtils.trimToNull(config.getAppId()));
            payConfig.setMchId(StringUtils.trimToNull(config.getMchId()));
            payConfig.setSubAppId(StringUtils.trimToNull(config.getSubAppId()));
            payConfig.setSubMchId(StringUtils.trimToNull(config.getSubMchId()));
            payConfig.setApiV3Key(StringUtils.trimToNull(config.getApiV3Key()));
            payConfig.setPrivateKeyPath(StringUtils.trimToNull(config.getPrivateKeyPath()));
            payConfig.setPrivateCertPath(StringUtils.trimToNull(config.getPrivateCertPath()));

            // 可以指定是否使用沙箱环境
            payConfig.setUseSandboxEnv(false);
            return payConfig;
        }).collect(Collectors.toMap(config -> config.getSubMchId(), a -> a));

        wxPayService.setMultiConfig(payConfigs);
        return wxPayService;
    }

}
```
###### 2）微信小程序
```java
@Setter
@Getter
@ConfigurationProperties(prefix = "wechat.miniapp")
public class WxMaProperties {

    private List<Config> configs;

    @Data
    public static class Config {

        /**
         * 设置微信小程序的appid
         */
        private String appid;

        /**
         * 设置微信小程序的Secret
         */
        private String secret;

        /**
         * 设置微信小程序消息服务器配置的token
         */
        private String token;

        /**
         * 设置微信小程序消息服务器配置的EncodingAESKey
         */
        private String aesKey;

        /**
         * 消息格式，XML或者JSON
         */
        private String msgDataFormat;

    }

}
```
```java
@Configuration
@EnableConfigurationProperties(WxMaProperties.class)
public class WxMaConfiguration {

    private WxMaProperties properties;
    private static Map<String, WxMaService> maServices;
    private static final Map<String, WxMaMessageRouter> routers = Maps.newHashMap();

    @Autowired
    public WxMaConfiguration(WxMaProperties properties) {
        this.properties = properties;
    }

    public static WxMaService getMaService(String appId) {
        WxMaService wxService = maServices.get(appId);
        Optional.ofNullable(wxService).orElseThrow(() -> new RuntimeException("没有配置appId"));
        return wxService;
    }

    public static WxMaMessageRouter getRouter(String appId) {
        return routers.get(appId);
    }

    @PostConstruct
    public void init() {
        List<WxMaProperties.Config> configs = this.properties.getConfigs();
        if (configs == null) {
            return;
        }

        maServices = configs.stream().map(a -> {
                    // 多配置
                    WxMaDefaultConfigImpl config = new WxMaDefaultConfigImpl();
                    config.setAppid(a.getAppid());
                    config.setSecret(a.getSecret());
                    config.setToken(a.getToken());
                    config.setAesKey(a.getAesKey());
                    config.setMsgDataFormat(a.getMsgDataFormat());

                    WxMaService service = new WxMaServiceImpl();
                    service.setWxMaConfig(config);

                    routers.put(a.getAppid(), this.newRouter(service));
                    return service;
                }).collect(Collectors.toMap(s -> s.getWxMaConfig().getAppid(), a -> a));
    }

    private WxMaMessageRouter newRouter(WxMaService service) {
        final WxMaMessageRouter router = new WxMaMessageRouter(service);
        router
                .rule().handler(logHandler).next()
                .rule().async(false).content("订阅消息").handler(subscribeMsgHandler).end()
                .rule().async(false).content("文本").handler(textHandler).end()
                .rule().async(false).content("图片").handler(picHandler).end()
                .rule().async(false).content("二维码").handler(qrcodeHandler).end();
        return router;
    }

    private final WxMaMessageHandler subscribeMsgHandler = (wxMessage, context, service, sessionManager) -> {
        service.getMsgService().sendSubscribeMsg(WxMaSubscribeMessage.builder()
                .templateId("此处更换为自己的模板id")
                .data(Lists.newArrayList(
                        new WxMaSubscribeMessage.MsgData("keyword1", "339208499")))
                .toUser(wxMessage.getFromUser())
                .build());
        return null;
    };

    private final WxMaMessageHandler logHandler = (wxMessage, context, service, sessionManager) -> {
        log.info("收到logHandler消息：" + wxMessage.toString());
        service.getMsgService().sendKefuMsg(WxMaKefuMessage.newTextBuilder().content("收到信息为：" + wxMessage.toJson())
                .toUser(wxMessage.getFromUser()).build());
        return null;
    };

    private final WxMaMessageHandler textHandler = (wxMessage, context, service, sessionManager) -> {
        log.info("收到textHandler消息：" + wxMessage.toString());
        service.getMsgService().sendKefuMsg(WxMaKefuMessage.newTextBuilder().content("回复文本消息")
                .toUser(wxMessage.getFromUser()).build());
        return null;
    };

    private final WxMaMessageHandler picHandler = (wxMessage, context, service, sessionManager) -> {
        log.info("收到picHandler消息：" + wxMessage.toString());
        try {
            WxMediaUploadResult uploadResult = service.getMediaService()
                    .uploadMedia("image", "png",
                            ClassLoader.getSystemResourceAsStream("tmp.png"));
            service.getMsgService().sendKefuMsg(
                    WxMaKefuMessage
                            .newImageBuilder()
                            .mediaId(uploadResult.getMediaId())
                            .toUser(wxMessage.getFromUser())
                            .build());
        } catch (WxErrorException e) {
            e.printStackTrace();
        }

        return null;
    };

    private final WxMaMessageHandler qrcodeHandler = (wxMessage, context, service, sessionManager) -> {
        log.info("收到qrcodeHandler消息：" + wxMessage.toString());
        try {
            final File file = service.getQrcodeService().createQrcode("123", 430);
            WxMediaUploadResult uploadResult = service.getMediaService().uploadMedia("image", file);
            service.getMsgService().sendKefuMsg(
                    WxMaKefuMessage
                            .newImageBuilder()
                            .mediaId(uploadResult.getMediaId())
                            .toUser(wxMessage.getFromUser())
                            .build());
        } catch (WxErrorException e) {
            e.printStackTrace();
        }

        return null;
    };

}
```
###### 3）企业微信
```java
@Getter
@Setter
@ConfigurationProperties(prefix = "wechat.cp")
public class WxCpProperties {

  /**
   * 设置企业微信的corpId
   */
  private String corpId;

  private List<AppConfig> appConfigs;

  @Getter
  @Setter
  public static class AppConfig {
    /**
     * 设置企业微信应用的AgentId
     */
    private Integer agentId;

    /**
     * 设置企业微信应用的Secret
     */
    private String secret;

    /**
     * 设置企业微信应用的token
     */
    private String token;

    /**
     * 设置企业微信应用的EncodingAESKey
     */
    private String aesKey;

  }

}
```
```java
@Configuration
@EnableConfigurationProperties(WxCpProperties.class)
public class WxCpConfiguration {

    private LogHandler logHandler;
    private NullHandler nullHandler;
    private LocationHandler locationHandler;
    private MenuHandler menuHandler;
    private MsgHandler msgHandler;
    private UnsubscribeHandler unsubscribeHandler;
    private SubscribeHandler subscribeHandler;

    private WxCpProperties properties;

    private static Map<Integer, WxCpMessageRouter> routers = Maps.newHashMap();
    private static Map<Integer, WxCpService> cpServices = Maps.newHashMap();

    @Autowired
    public WxCpConfiguration(LogHandler logHandler, NullHandler nullHandler, LocationHandler locationHandler,
                             MenuHandler menuHandler, MsgHandler msgHandler, UnsubscribeHandler unsubscribeHandler,
                             SubscribeHandler subscribeHandler, WxCpProperties properties) {
        this.logHandler = logHandler;
        this.nullHandler = nullHandler;
        this.locationHandler = locationHandler;
        this.menuHandler = menuHandler;
        this.msgHandler = msgHandler;
        this.unsubscribeHandler = unsubscribeHandler;
        this.subscribeHandler = subscribeHandler;
        this.properties = properties;
    }


    public static Map<Integer, WxCpMessageRouter> getRouters() {
        return routers;
    }


    public static WxCpService getCpService(Integer agentId) {
        WxCpService cpService = cpServices.get(agentId);
        Optional.ofNullable(cpService).orElseThrow(() -> new RuntimeException("cpService不能为空"));
        return cpService;
    }

    @PostConstruct
    public void initServices() {
        cpServices = this.properties.getAppConfigs().stream().map(a -> {
            val configStorage = new WxCpDefaultConfigImpl();
            configStorage.setCorpId(this.properties.getCorpId());
            configStorage.setAgentId(a.getAgentId());
            configStorage.setCorpSecret(a.getSecret());
            configStorage.setToken(a.getToken());
            configStorage.setAesKey(a.getAesKey());

            val service = new WxCpServiceImpl();
            service.setWxCpConfigStorage(configStorage);

            routers.put(a.getAgentId(), this.newRouter(service));
            return service;
        }).collect(Collectors.toMap(service -> service.getWxCpConfigStorage().getAgentId(), a -> a));
    }

    private WxCpMessageRouter newRouter(WxCpService wxCpService) {
        final val newRouter = new WxCpMessageRouter(wxCpService);

        // 记录所有事件的日志 （异步执行）
        newRouter.rule().handler(this.logHandler).next();

        // 自定义菜单事件
        newRouter.rule().async(false).msgType(WxConsts.XmlMsgType.EVENT)
            .event(WxConsts.MenuButtonType.CLICK).handler(this.menuHandler).end();

        // 点击菜单链接事件（这里使用了一个空的处理器，可以根据自己需要进行扩展）
        newRouter.rule().async(false).msgType(WxConsts.XmlMsgType.EVENT)
            .event(WxConsts.MenuButtonType.VIEW).handler(this.nullHandler).end();

        // 关注事件
        newRouter.rule().async(false).msgType(WxConsts.XmlMsgType.EVENT)
            .event(WxConsts.EventType.SUBSCRIBE).handler(this.subscribeHandler)
            .end();

        // 取消关注事件
        newRouter.rule().async(false).msgType(WxConsts.XmlMsgType.EVENT)
            .event(WxConsts.EventType.UNSUBSCRIBE)
            .handler(this.unsubscribeHandler).end();

        // 上报地理位置事件
        newRouter.rule().async(false).msgType(WxConsts.XmlMsgType.EVENT)
            .event(WxConsts.EventType.LOCATION).handler(this.locationHandler)
            .end();

        // 接收地理位置消息
        newRouter.rule().async(false).msgType(WxConsts.XmlMsgType.LOCATION)
            .handler(this.locationHandler).end();

        // 扫码事件（这里使用了一个空的处理器，可以根据自己需要进行扩展）
        newRouter.rule().async(false).msgType(WxConsts.XmlMsgType.EVENT)
            .event(WxConsts.EventType.SCAN).handler(this.nullHandler).end();

        newRouter.rule().async(false).msgType(WxConsts.XmlMsgType.EVENT)
            .event(WxCpConsts.EventType.CHANGE_CONTACT).handler(new ContactChangeHandler()).end();

        newRouter.rule().async(false).msgType(WxConsts.XmlMsgType.EVENT)
            .event(WxCpConsts.EventType.ENTER_AGENT).handler(new EnterAgentHandler()).end();

        // 默认
        newRouter.rule().async(false).handler(this.msgHandler).end();

        return newRouter;
    }

}
```
4.其他请移步wiki：[GitHub wiki](https://github.com/Wechat-Group/WxJava/wiki)
