# wx-java-mp-multi-solon-plugin

## 快速开始

1. 引入依赖
    ```xml
    <dependency>
        <groupId>com.github.binarywang</groupId>
        <artifactId>wx-java-mp-multi-solon-plugin</artifactId>
        <version>${version}</version>
    </dependency>
    ```
2. 添加配置(app.properties)
    ```properties
    # 公众号配置
    ## 应用 1 配置(必填)
    wx.mp.apps.tenantId1.app-id=appId
    wx.mp.apps.tenantId1.app-secret=@secret
    ## 选填
    wx.mp.apps.tenantId1.token=@token
    wx.mp.apps.tenantId1.aes-key=@aesKey
    wx.mp.apps.tenantId1.use-stable-access-token=@useStableAccessToken
    ## 应用 2 配置(必填)
    wx.mp.apps.tenantId2.app-id=@appId
    wx.mp.apps.tenantId2.app-secret =@secret
    ## 选填
    wx.mp.apps.tenantId2.token=@token
    wx.mp.apps.tenantId2.aes-key=@aesKey
    wx.mp.apps.tenantId2.use-stable-access-token=@useStableAccessToken
   
    # ConfigStorage 配置（选填）
    ## 配置类型: memory(默认), jedis, redisson, redis_template
    wx.mp.config-storage.type=memory
    ## 相关redis前缀配置: wx:mp:multi(默认)
    wx.mp.config-storage.key-prefix=wx:mp:multi
    wx.mp.config-storage.redis.host=127.0.0.1
    wx.mp.config-storage.redis.port=6379
	  ## 单机和 sentinel 同时存在时，优先使用sentinel配置
	  # wx.mp.config-storage.redis.sentinel-ips=127.0.0.1:16379,127.0.0.1:26379
	  # wx.mp.config-storage.redis.sentinel-name=mymaster 
   
    # http 客户端配置（选填）
    ## # http客户端类型: http_client(默认), ok_http, jodd_http
    wx.mp.config-storage.http-client-type=http_client
    wx.mp.config-storage.http-proxy-host=
    wx.mp.config-storage.http-proxy-port=
    wx.mp.config-storage.http-proxy-username=
    wx.mp.config-storage.http-proxy-password=
    ## 最大重试次数，默认：5 次，如果小于 0，则为 0
    wx.mp.config-storage.max-retry-times=5
    ## 重试时间间隔步进，默认：1000 毫秒，如果小于 0，则为 1000
    wx.mp.config-storage.retry-sleep-millis=1000
   
	  # 公众号地址 host 配置
	  # wx.mp.hosts.api-host=http://proxy.com/
	  # wx.mp.hosts.open-host=http://proxy.com/
	  # wx.mp.hosts.mp-host=http://proxy.com/
    ```
3. 自动注入的类型：`WxMpMultiServices`

4. 使用样例

```java
import com.binarywang.solon.wxjava.mp_multi.service.WxMpMultiServices;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.WxMpUserService;
import org.noear.solon.annotation.Component;
import org.noear.solon.annotation.Inject;

@Component
public class DemoService {
  @Inject
  private WxMpMultiServices wxMpMultiServices;

  public void test() {
    // 应用 1 的 WxMpService
    WxMpService wxMpService1 = wxMpMultiServices.getWxMpService("tenantId1");
    WxMpUserService userService1 = wxMpService1.getUserService();
    userService1.userInfo("xxx");
    // todo ...

    // 应用 2 的 WxMpService
    WxMpService wxMpService2 = wxMpMultiServices.getWxMpService("tenantId2");
    WxMpUserService userService2 = wxMpService2.getUserService();
    userService2.userInfo("xxx");
    // todo ...

    // 应用 3 的 WxMpService
    WxMpService wxMpService3 = wxMpMultiServices.getWxMpService("tenantId3");
    // 判断是否为空
    if (wxMpService3 == null) {
      // todo wxMpService3 为空，请先配置 tenantId3 微信公众号应用参数
      return;
    }
    WxMpUserService userService3 = wxMpService3.getUserService();
    userService3.userInfo("xxx");
    // todo ...
  }
}
```
