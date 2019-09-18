package me.chanjar.weixin.common.util.xml;

import com.thoughtworks.xstream.converters.basic.AbstractSingleValueConverter;

/**
 * @author Pwenlee
 * @date 2019/9/18 10:20
 * @description
 */
public class XStreamCDataIntegerArrayConverter extends AbstractSingleValueConverter {

    @Override
    public boolean canConvert(Class clazz) {
      return clazz == Integer[].class;
    }

    @Override
    public String toString(Object obj) {
      return "<![CDATA[" + super.toString(obj) + "]]>";
    }

    @Override
    public Object fromString(String s) {
      if(null != s){
        String[] strings = s.split(",");
        if(null != strings){
          int length = strings.length;
          Integer[] integers = new Integer[length];
          for (int i = 0; i < length; i++){
            integers[i] = Integer.valueOf(strings[i]);
          }
          return integers;
        }
      }
      return new Integer[0];
    }
}
