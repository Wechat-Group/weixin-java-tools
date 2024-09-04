# wx-java-cp-solon-plugin

## 快速开始

1. 引入依赖
    ```xml
    <dependency>
        <groupId>com.github.binarywang</groupId>
        <artifactId>wx-java-cp-solon-plugin</artifactId>
        <version>${version}</version>
    </dependency>
    ```
2. 添加配置(app.properties)
    ```properties
    # 企业微信号配置(必填)
    wx.cp.corp-id = @corp-id
    wx.cp.corp-secret = @corp-secret
    # 选填
    wx.cp.agent-id = @agent-id
    wx.cp.token = @token
    wx.cp.aes-key = @aes-key
    wx.cp.msg-audit-priKey = @msg-audit-priKey
    wx.cp.msg-audit-lib-path = @msg-audit-lib-path
    # ConfigStorage 配置（选填）
    wx.cp.config-storage.type=memory # 配置类型: memory(默认), jedis, redisson, redistemplate
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
