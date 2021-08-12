package me.chanjar.weixin.common.util.xml;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.Iterables;
import com.thoughtworks.xstream.converters.basic.StringConverter;


/**
 * String 数组转换
 *
 * @author chily.lin
 */
public class StringArrayConverter extends StringConverter {
  @Override
  public boolean canConvert(Class type) {
    return type == String[].class;
  }

  @Override
  public String toString(Object obj) {
    return "<![CDATA[" + Joiner.on(",").join((String[]) obj) + "]]>";
  }

  @Override
  public Object fromString(String str) {
    final Iterable<String> iterable = Splitter.on(",").split(str);
    String[] results = Iterables.toArray(iterable, String.class);
    return results;
  }
}
