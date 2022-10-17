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
2. 添加配置(application.properties)
    ```properties
    # 企业微信号配置(必填)
    wx.cp.corp-id = @corp-id
    wx.cp.corp-secret = @corp-secret
    # 选填
    wx.cp.token = @token
    wx.cp.aes-key = @aes-key
    wx.cp.agent-id = @agent-id
    # ConfigStorage 配置（选填）
    wx.cp.config-storage.type=memory # memory 默认，目前只支持 memory 类型，可以自行扩展 redis 等类型
    # http 客户端配置（选填）
    wx.cp.config-storage.http-proxy-host=
    wx.cp.config-storage.http-proxy-port=
    wx.cp.config-storage.http-proxy-username=
    wx.cp.config-storage.http-proxy-password=
    # 最大重试次数，默认：5 次，如果小于 0，则为 0
    wx.cp.config-storage.max-retry-times=5
    # 重试时间间隔步进，默认：1000 毫秒，如果小于 0，则为 1000
    wx.cp.config-storage.retry-sleep-millis=1000
    ```
3. 支持自动注入的类型: `WxCpService`, `WxCpConfigStorage`

4. 覆盖自动配置: 自定义注入的bean会覆盖自动注入的

- WxCpService
- WxCpConfigStorage
