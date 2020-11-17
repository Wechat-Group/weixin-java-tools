package me.chanjar.weixin.common.util.xml;

import com.thoughtworks.xstream.converters.basic.StringConverter;

/**
 * CDATA 内容转换器，加上CDATA标签.
 *
 * @author Daniel Qian
 */
public class XStreamCDataConverter extends StringConverter {

  @Override
  public String toString(Object obj) {
    return "<![CDATA[" + super.toString(obj) + "]]>";
  }

}
