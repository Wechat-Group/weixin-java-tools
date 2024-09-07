# wx-java-qidian-spring-boot-starter

## 快速开始

1. 引入依赖
   ```xml
   <dependency>
       <groupId>com.github.binarywang</groupId>
       <artifactId>wx-java-qidian-spring-boot-starter</artifactId>
       <version>${version}</version>
   </dependency>
   ```
2. 添加配置(application.properties)
   ```properties
   # 公众号配置(必填)
   wx.qidian.appId = appId
   wx.qidian.secret = @secret
   wx.qidian.token = @token
   wx.qidian.aesKey = @aesKey
   # 存储配置redis(可选)
   wx.qidian.config-storage.type = Jedis                     # 配置类型: Memory(默认), Jedis, RedisTemplate
   wx.qidian.config-storage.key-prefix = wx                  # 相关redis前缀配置: wx(默认)
   wx.qidian.config-storage.redis.host = 127.0.0.1
   wx.qidian.config-storage.redis.port = 6379
   #单机和sentinel同时存在时，优先使用sentinel配置
   #wx.qidian.config-storage.redis.sentinel-ips=127.0.0.1:16379,127.0.0.1:26379
   #wx.qidian.config-storage.redis.sentinel-name=mymaster
   # http客户端配置
   wx.qidian.config-storage.http-client-type=httpclient      # http客户端类型: HttpClient(默认), OkHttp, JoddHttp
   wx.qidian.config-storage.http-proxy-host=
   wx.qidian.config-storage.http-proxy-port=
   wx.qidian.config-storage.http-proxy-username=
   wx.qidian.config-storage.http-proxy-password=
   # 公众号地址host配置
   #wx.qidian.hosts.api-host=http://proxy.com/
   #wx.qidian.hosts.open-host=http://proxy.com/
   #wx.qidian.hosts.mp-host=http://proxy.com/
   ```
3. 自动注入的类型

- `WxQidianService`
- `WxQidianConfigStorage`

4、参考 demo：
https://github.com/binarywang/wx-java-mp-demo
