package me.chanjar.weixin.common.util;

import java.io.StringReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.dom4j.tree.DefaultText;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

/**
 * <pre>
 * XML转换工具类.
 * Created by Binary Wang on 2018/11/4.
 * </pre>
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
public class XmlUtils {

  public static Map<String, Object> xml2Map(String xmlString) {
    Map<String, Object> map = new HashMap<>(16);
    try {
      SAXReader saxReader = new SAXReader();
      Document doc = saxReader.read(new StringReader(xmlString));
      Element root = doc.getRootElement();
      List<Element> elements = root.elements();
      for (Element element : elements) {
          map.put(element.getName(), element2MapOrString(element));
      }
    } catch (DocumentException e) {
      throw new RuntimeException(e);
    }

    return map;
  }

  private static Object element2MapOrString(Element element) {
    Map<String, Object> result = Maps.newHashMap();

    final List<Node> content = element.content();
    if (content.size() <= 1) {
      return element.getText();
    }

    final Set<String> names = names(content);
    if (names.size() == 1) {
      // 说明是个列表，各个子对象是相同的name
      List<Object> list = Lists.newArrayList();
      for (Node node : content) {
        if (node instanceof DefaultText) {
          continue;
        }

        if (node instanceof Element) {
          list.add(element2MapOrString((Element) node));
        }
      }

      result.put(names.iterator().next(), list);
    } else {
      for (Node node : content) {
        if (node instanceof DefaultText) {
          continue;
        }

        if (node instanceof Element) {
          result.put(node.getName(), element2MapOrString((Element) node));
        }
      }
    }

    return result;
  }

  private static Set<String> names(List<Node> nodes) {
    Set<String> names = Sets.newHashSet();
    for (Node node : nodes) {
      if (node instanceof DefaultText) {
        continue;
      }
      names.add(node.getName());
    }

    return names;
  }
}
