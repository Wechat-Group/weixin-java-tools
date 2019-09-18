package me.chanjar.weixin.common.util.xml;

import com.thoughtworks.xstream.converters.basic.AbstractSingleValueConverter;

/**
 * @author Pwenlee
 * @date 2019/9/18 10:20
 * @description
 */
public class XStreamCDataLongArrayConverter extends AbstractSingleValueConverter {

    @Override
    public boolean canConvert(Class clazz) {
      return clazz == Long[].class;
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
          Long[] longs = new Long[length];
          for (int i = 0; i < length; i++){
            longs[i] = Long.valueOf(strings[i]);
          }
          return longs;
        }
      }
      return new Long[0];
    }
}
