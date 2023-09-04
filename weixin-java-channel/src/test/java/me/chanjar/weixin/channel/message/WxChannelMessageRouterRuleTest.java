package me.chanjar.weixin.channel.message;

import static org.testng.Assert.assertEquals;

import me.chanjar.weixin.channel.bean.message.order.OrderPayMessage;
import org.testng.annotations.Test;

/**
 * @author LiXiZe
 * @since 2023-04-20
 */
public class WxChannelMessageRouterRuleTest {

  @Test
  public void testResolveMessageClass() {
    WxChannelMessageRouterRule<OrderPayMessage> rule = new WxChannelMessageRouterRule<>();
    rule.setMessageClass(OrderPayMessage.class);
    assertEquals(rule.getMessageClass(), OrderPayMessage.class);


  }




}
