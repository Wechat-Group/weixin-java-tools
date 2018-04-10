package me.chanjar.weixin.common.api;

/**
 * <pre>
 * 消息重复检查器.
 * 微信服务器在五秒内收不到响应会断掉连接，并且重新发起请求，总共重试三次
 * </pre>
 */
public interface WxMessageDuplicateChecker {

  /**
   * 判断消息是否重复.
   * <h2>公众号的排重方式</h2>
   *
   * <p>普通消息：关于重试的消息排重，推荐使用msgid排重。<a href="http://mp.weixin.qq.com/wiki/10/79502792eef98d6e0c6e1739da387346.html">文档参考</a>。</p>
   * <p>事件消息：关于重试的消息排重，推荐使用FromUserName + CreateTime 排重。<a href="http://mp.weixin.qq.com/wiki/2/5baf56ce4947d35003b86a9805634b1e.html">文档参考</a></p>
   *
   * <h2>企业号的排重方式</h2>
   * <p>官方文档完全没有写，参照公众号的方式排重。</p>
   * <p>或者可以采取更简单的方式，如果有MsgId就用MsgId排重，如果没有就用FromUserName+CreateTime排重</p>
   *
   * @param messageId messageId需要根据上面讲的方式构造
   * @return 如果是重复消息，返回true，否则返回false
   */
  boolean isDuplicate(String messageId);

}
