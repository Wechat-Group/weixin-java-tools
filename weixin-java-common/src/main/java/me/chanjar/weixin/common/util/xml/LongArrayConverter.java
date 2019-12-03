package me.chanjar.weixin.common.util.xml;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.Iterables;
import com.thoughtworks.xstream.converters.basic.StringConverter;

/**
 * Long型数组转换器.
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 * @date 2019-08-22
 */
public class LongArrayConverter extends StringConverter {
  @Override
  public boolean canConvert(Class type) {
    return type == Long[].class;
  }

  @Override
  public String toString(Object obj) {
    return "<![CDATA[" + Joiner.on(",").join((Long[]) obj) + "]]>";
  }

  @Override
  public Object fromString(String str) {
    final Iterable<String> iterable = Splitter.on(",").split(str);
    final String[] strings = Iterables.toArray(iterable, String.class);
    Long[] result = new Long[strings.length];
    int index = 0;
    for (String string : strings) {
      result[index++] = Long.parseLong(string);
    }

    return result;
  }
}
