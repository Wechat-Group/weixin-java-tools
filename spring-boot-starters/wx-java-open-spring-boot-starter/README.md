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
    ```
    # 开放平台配置(必填)
    wx.open.appId = @appId
	  wx.open.secret = @secret
	  wx.open.token = @token
	  wx.open.aesKey = @aesKey
	  # 存储配置redis(可选), 优先使用(wx.open.config-storage.redis)配置的redis, 支持自定注入的JedisPool
	  wx.open.config-storage.type = redis             # 可选值, memory(默认), redis
	  wx.open.config-storage.redis.host = 127.0.0.1
	  wx.open.config-storage.redis.port = 6379
    ```
3. 支持自动注入的类型: `WxOpenService, WxOpenMessageRouter, WxOpenComponentService`

4. 覆盖自动配置: 自定义注入的bean会覆盖自动注入的
  - WxOpenConfigStorage
  - WxOpenService







