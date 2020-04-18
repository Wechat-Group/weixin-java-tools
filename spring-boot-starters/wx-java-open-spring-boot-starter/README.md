# wx-java-open-spring-boot-starter
## 快速开始
1. 引入依赖
    ```xml
    <dependency>
        <groupId>com.github.binarywang</groupId>
        <artifactId>wx-java-open-spring-boot-starter</artifactId>
        <version>${version}</version>
    </dependency>
    ```
2. 添加配置(application.properties)
    ```properties
    # 公众号配置(必填)
    wx.open.appId = appId
    wx.open.secret = @secret
    wx.open.token = @token
    wx.open.aesKey = @aesKey
    # 存储配置redis(可选)
    # 优先注入容器的(JedisPool, RedissonClient), 当配置了wx.open.config-storage.redis.host, 不会使用容器注入redis连接配置
    wx.open.config-storage.type = redis                     # 配置类型: memory(默认), redis(jedis), jedis, redisson, redistemplate
    wx.open.config-storage.key-prefix = wx                  # 相关redis前缀配置: wx(默认)
    wx.open.config-storage.redis.host = 127.0.0.1
    wx.open.config-storage.redis.port = 6379
    # http客户端配置
    wx.open.config-storage.http-client-type=httpclient      # http客户端类型: httpclient(默认)
    wx.open.config-storage.http-proxy-host=
    wx.open.config-storage.http-proxy-port=
    wx.open.config-storage.http-proxy-username=
    wx.open.config-storage.http-proxy-password=
    ```
3. 支持自动注入的类型: `WxOpenService, WxOpenMessageRouter, WxOpenComponentService`

4. 覆盖自动配置: 自定义注入的bean会覆盖自动注入的
  - WxOpenConfigStorage
  - WxOpenService
