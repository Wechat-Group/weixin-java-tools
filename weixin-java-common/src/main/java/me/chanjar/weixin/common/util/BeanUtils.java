package me.chanjar.weixin.common.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.thoughtworks.xstream.annotations.XStreamAlias;

import me.chanjar.weixin.common.annotation.Required;
import me.chanjar.weixin.common.bean.result.WxError;
import me.chanjar.weixin.common.exception.WxErrorException;

/**
 * <pre>
 * bean操作的一些工具类
 * Created by Binary Wang on 2016-10-21.
 * @author <a href="https://github.com/binarywang">binarywang(Binary Wang)</a>
 * </pre>
 */
public class BeanUtils {
	private static Logger log = LoggerFactory.getLogger(BeanUtils.class);

  /**
   * 检查bean里标记为@Required的field是否为空，为空则抛异常
   * @param bean 要检查的bean对象
   * @throws WxErrorException
   */
  public static void checkRequiredFields(Object bean) throws WxErrorException {
    List<String> nullFields = Lists.newArrayList();

    List<Field> fields = new ArrayList<>( Arrays.asList(bean.getClass().getDeclaredFields()));
    fields.addAll(Arrays.asList(bean.getClass().getSuperclass().getDeclaredFields()));
    for (Field field : fields) {
      try {
        boolean isAccessible = field.isAccessible();
        field.setAccessible(true);
        if (field.isAnnotationPresent(Required.class)
          && field.get(bean) == null) {
          nullFields.add(field.getName());
        }
        field.setAccessible(isAccessible);
      } catch (SecurityException | IllegalArgumentException
        | IllegalAccessException e) {
        e.printStackTrace();
      }
    }

    if (!nullFields.isEmpty()) {
      String msg  = "必填字段 " + nullFields + " 必须提供值";	
      log.debug(msg);
      throw new WxErrorException(WxError.newBuilder().setErrorMsg(msg).build());
    }
  }

	/**
	 * 将bean按照@XStreamAlias标识的字符串内容生成以之为key的map对象
	 * 
	 * @param bean 包含@XStreamAlias的xml bean对象
	 * @return map对象
	 */
	public static Map<String, String> xmlBean2Map(Object bean) {
		Map<String, String> result = Maps.newHashMap();
		List<Field> fields = new ArrayList<>(Arrays.asList(bean.getClass().getDeclaredFields()));
		fields.addAll(Arrays.asList(bean.getClass().getSuperclass().getDeclaredFields()));
		for (Field field : fields) {
			try {
				boolean isAccessible = field.isAccessible();
				field.setAccessible(true);
				if (field.get(bean) == null) {
					field.setAccessible(isAccessible);
					continue;
				}

				if (field.isAnnotationPresent(XStreamAlias.class)) {
					result.put(field.getAnnotation(XStreamAlias.class).value(), field.get(bean).toString());
				}

				field.setAccessible(isAccessible);
			} catch (SecurityException | IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}

		}

		return result;
	}
}
