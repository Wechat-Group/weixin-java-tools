# wx-java-mp-starter
## 快速开始
1. 引入依赖
    ```xml
    <dependency>
        <groupId>com.github.binarywang</groupId>
        <artifactId>wx-java-mp-starter</artifactId>
        <version>${version}</version>
    </dependency>
    ```
2. 添加配置(application.properties)
    ```properties
    # 公众号配置(必填)
    wx.mp.appId = @appId
	wx.mp.secret = @secret
	wx.mp.token = @token
	wx.mp.aesKey = @aesKey
	# 存储配置redis(可选)
	wx.mp.config-storage.type = redis
	wx.mp.config-storage.redis.host = 127.0.0.1
	wx.mp.config-storage.redis.port = 6379
    ```
3. 支持自动注入的类型

`WxMpService`以及相关的服务类, 比如: `wxMpService.getXxxService`。







