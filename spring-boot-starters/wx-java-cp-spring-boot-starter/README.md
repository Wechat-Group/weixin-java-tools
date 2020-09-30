# wx-java-cp-spring-boot-starter
## 快速开始
1. 引入依赖
    ```xml
    <dependency>
        <groupId>com.github.binarywang</groupId>
        <artifactId>wx-java-cp-spring-boot-starter</artifactId>
        <version>${version}</version>
    </dependency>
    ```
2. 添加配置(application.yml)
    ```yml
   logging:
  level:
    org.springframework.web: INFO
    com.github.binarywang.demo.wx.cp: DEBUG
    me.chanjar.weixin: DEBUG
wechat:
  cp:
    corpId: 111
    appConfigs:
      - agentId: 1000001
        secret: 1111
        token: 111
        aesKey: 111
      - agentId: 1000002
        secret: 1111
        token: 111
        aesKey: 111
server:
  port: 8000
    ```
3. 自动注入的类型
- `WxCpService`以及~~相关的服务类, 比如: `wxCpService.getXxxService`。~~
- `WxCpConfigStorage`







