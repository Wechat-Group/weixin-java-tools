package me.chanjar.weixin.common.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * <pre>
 * 自定义的ToString方法，用于产生去掉空值属性的字符串
 * Created by Binary Wang on 2016-10-27.
 * @author <a href="https://github.com/binarywang">binarywang(Binary Wang)</a>
 * </pre>
 */
public class ToStringUtils {
  public static final ToStringStyle THE_STYLE = new SimpleMultiLineToStringStyle();
  private static class SimpleMultiLineToStringStyle extends ToStringStyle {
    private static final long serialVersionUID = 4645306494220335355L;
    private static final String LINE_SEPARATOR = "\n";
    private static final String NULL_TEXT = "<null>";

    public SimpleMultiLineToStringStyle() {
      super();
      this.setContentStart("[");
      this.setFieldSeparator(LINE_SEPARATOR + "  ");
      this.setFieldSeparatorAtStart(true);
      this.setContentEnd(LINE_SEPARATOR + "]");
      this.setNullText(NULL_TEXT);
      this.setUseShortClassName(true);
      this.setUseIdentityHashCode(false);
    }
  }

  /**
   * 用于产生去掉空值属性并以换行符分割各属性键值的toString字符串
   * @param obj
   */
  public static String toSimpleString(Object obj) {
    String toStringResult = ToStringBuilder.reflectionToString(obj, THE_STYLE);
    String[] split = toStringResult.split(SimpleMultiLineToStringStyle.LINE_SEPARATOR);
    StringBuilder result = new StringBuilder();
    for (String string : split) {
      if (string.endsWith(SimpleMultiLineToStringStyle.NULL_TEXT)) {
        continue;
      }

      result.append(string + SimpleMultiLineToStringStyle.LINE_SEPARATOR);
    }

    if (result.length() == 0) {
      return "";
    }

    //如果没有非空的属性，就输出 <all null properties>
    if (StringUtils.countMatches(result, SimpleMultiLineToStringStyle.LINE_SEPARATOR) == 2) {
      return result.toString().split(SimpleMultiLineToStringStyle.LINE_SEPARATOR)[0]
        + "<all null values>]";
    }

    return result.deleteCharAt(result.length() - 1).toString();
  }
}
