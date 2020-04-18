# wx-java-mp-spring-boot-starter
## 快速开始
1. 引入依赖
    ```xml
    <dependency>
        <groupId>com.github.binarywang</groupId>
        <artifactId>wx-java-mp-spring-boot-starter</artifactId>
        <version>${version}</version>
    </dependency>
    ```
2. 添加配置(application.properties)
    ```properties
    # 公众号配置(必填)
    wx.mp.appId = appId
    wx.mp.secret = @secret
    wx.mp.token = @token
    wx.mp.aesKey = @aesKey
    # 存储配置redis(可选)
    wx.mp.config-storage.type = redis                     # 配置类型: memory(默认), redis, jedis, redistemplate
    wx.mp.config-storage.key-prefix = wx                  # 相关redis前缀配置: wx(默认)
    wx.mp.config-storage.redis.host = 127.0.0.1
    wx.mp.config-storage.redis.port = 6379
    # http客户端配置
    wx.mp.config-storage.http-client-type=httpclient      # http客户端类型: httpclient(默认), okhttp, joddhttp
    wx.mp.config-storage.http-proxy-host=
    wx.mp.config-storage.http-proxy-port=
    wx.mp.config-storage.http-proxy-username=
    wx.mp.config-storage.http-proxy-password=
    ```
3. 自动注入的类型
- `WxMpService`以及~~相关的服务类, 比如: `wxMpService.getXxxService`。~~
- `WxMpConfigStorage`







