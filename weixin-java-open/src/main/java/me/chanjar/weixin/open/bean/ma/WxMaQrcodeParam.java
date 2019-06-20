package me.chanjar.weixin.open.bean.ma;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 微信小程序体验二维码参数
 *
 * @author yqx
 * @date 2018/9/13
 */
@Data
public class WxMaQrcodeParam {

  WxMaQrcodeParam() {

  }

  /**
   * 页面路径
   */
  private String pagePath;

  /**
   * 页面参数
   */
  private Map<String, String> pageParams;

  /**
   * 添加页面参数
   *
   * @param key
   * @param value
   */
  public WxMaQrcodeParam addPageParam(String key, String value) {
    if (StringUtils.isNoneBlank(key, value)) {
      if (pageParams == null)
        pageParams = new HashMap<>();
      pageParams.put(key, value);
    }
    return this;
  }

  /**
   * 添加页面参数
   *
   * @param params
   * @return
   */
  public WxMaQrcodeParam addPageParam(Map<String, String> params) {
    if (params != null) {
      if (pageParams == null)
        pageParams = new HashMap<>();
      pageParams.putAll(params);
    }
    return this;
  }

  /**
   * 组装完整的页面请求路径（带参数）
   *
   * @return
   */
  public String getRequestPath() {
    if (StringUtils.isNotBlank(getPagePath()) && getPageParams() != null && !getPageParams().isEmpty()) {
      Set<String> keys = getPageParams().keySet();
      StringBuilder sb = new StringBuilder();
      for (String key : keys) {
        sb.append("&").append(key).append("=").append(getPageParams().get(key));
      }
      return pagePath + "?" + sb.substring(1);
    } else {
      return pagePath;
    }
  }

  public static WxMaQrcodeParam create(String pagePath) {
    WxMaQrcodeParam instance = new WxMaQrcodeParam();
    instance.setPagePath(pagePath);
    return instance;
  }
}
