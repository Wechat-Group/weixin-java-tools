package me.chanjar.weixin.mp.util.text;

import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.MessageFormat;

/**
 * 文本格式化工具类
 *
 * @author WcJun
 * @date 2018/04/13
 */
public final class MessageFormatUtil {

  /**
   * Slf4j 日志对象
   */
  private static final Logger LOGGER = LoggerFactory.getLogger(MessageFormatUtil.class);

  /**
   * 避免被实例化
   */
  private MessageFormatUtil() {
    throw new AssertionError("No " + MessageFormatUtil.class.getName() + " instances for you!");
  }

  /**
   * 格式化文本
   *
   * @param content 需要格式化的文本
   * @param args    多参数
   * @return 格式化后的文本
   */
  public static String format(String content, Object... args) {
    LOGGER.info("format the content is:{}", content);
    //Validate
    Validate.notNull(content, "the content can't be null!");
    //call the MessageFormat
    return MessageFormat.format(content, args);
  }
}
