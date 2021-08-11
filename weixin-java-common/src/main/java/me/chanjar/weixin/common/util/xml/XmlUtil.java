package me.chanjar.weixin.common.util.xml;

import java.io.InputStream;

/**
 * @author caiqy
 */
public class XmlUtil {

    public static String toXml(Object obj) {
        return XStreamInitializer.getInstance(obj.getClass()).toXML(obj);
    }

    @SuppressWarnings("unchecked")
    public static <T> T toObject(String xml, Class<T> clz) {
        return (T) XStreamInitializer.getInstance(clz).fromXML(xml);
    }

    @SuppressWarnings("unchecked")
    public static <T> T toObject(InputStream is, Class<T> clz) {
        return (T) XStreamInitializer.getInstance(clz).fromXML(is);
    }

}
