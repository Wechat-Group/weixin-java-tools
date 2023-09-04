package me.chanjar.weixin.channel.message;

import static me.chanjar.weixin.channel.constant.MessageEventConstants.BRAND;
import static me.chanjar.weixin.channel.constant.MessageEventConstants.PRODUCT_SPU_AUDIT;
import static org.testng.Assert.*;

import com.google.inject.Inject;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.channel.api.WxChannelService;
import me.chanjar.weixin.channel.bean.message.product.BrandMessage;
import me.chanjar.weixin.channel.bean.message.product.SpuAuditMessage;
import me.chanjar.weixin.channel.message.rule.HandlerConsumer;
import me.chanjar.weixin.channel.test.ApiTestModule;
import me.chanjar.weixin.channel.util.JsonUtils;
import me.chanjar.weixin.common.session.WxSessionManager;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

/**
 * @author LiXiZe
 * @since 2023-04-21
 */
@Slf4j
@Guice(modules = ApiTestModule.class)
public class WxChannelMessageRouterTest {

  @Inject
  private WxChannelService channelService;

  @Test
  public void test1() {
    WxChannelMessageRouter router = new WxChannelMessageRouter();
    /* 品牌资质事件回调 */
    this.addRule(router, BrandMessage.class, BRAND, this::brandUpdate);
    /* 商品审核结果 */
    this.addRule(router, SpuAuditMessage.class, PRODUCT_SPU_AUDIT, this::spuAudit);
    String spuAuditJson = "{\n"
      + "    \"ToUserName\":\"gh_*\",\n"
      + "    \"FromUserName\":\"OPENID\",\n"
      + "    \"CreateTime\":1662480000,\n"
      + "    \"MsgType\":\"event\",\n"
      + "    \"Event\":\"product_spu_audit\",\n"
      + "    \"ProductSpuAudit\": {\n"
      + "        \"product_id\":\"12345678\",\n"
      + "        \"status\":3,\n"
      + "        \"reason\":\"abc\"\n"
      + "    }\n"
      + "}";
    WxChannelMessage message = JsonUtils.decode(spuAuditJson, WxChannelMessage.class);
    Object result = router.route(message, spuAuditJson, "xxxWWQQxxx", channelService);
    if (result != null) {
      log.info("result:{}", result);
    } else {
      log.info("return null");
    }
  }

  public void brandUpdate(BrandMessage message, String content, String appId,
    Map<String, Object> context, WxSessionManager sessionManager) {
    log.info("品牌更新:{}", JsonUtils.encode(message));
  }


  public void spuAudit(SpuAuditMessage message, String content, String appId,
    Map<String, Object> context, WxSessionManager sessionManager) {
    log.info("商品审核:{}", JsonUtils.encode(message));
  }

  /**
   * 添加一条规则进入路由器
   *
   * @param clazz 消息类型
   * @param event 事件类型
   * @param consumer 处理器
   * @param <T> 消息类型
   */
  protected <T extends WxChannelMessage> void addRule(WxChannelMessageRouter router, Class<T> clazz, String event,
    HandlerConsumer<T, String, String, Map<String, Object>, WxSessionManager> consumer) {
    WxChannelMessageRouterRule<T> rule = new WxChannelMessageRouterRule<>();
    rule.setMessageClass(clazz).setEvent(event).setAsync(false);
    rule.getHandlers().add((message, content, appId, context, sessionManager) -> {
      consumer.accept(message, content, appId, context, sessionManager);
      return "success";
    });
    router.getRules().add(rule);
  }

}
