package me.chanjar.weixin.cp.bean.templatecard;

import com.google.gson.JsonObject;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

/**
 * 引用文献样式
 *
 * @author zp
 * @date 2022/1/2
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuoteArea implements Serializable {

  private static final long serialVersionUID = -2209656515382964356L;

  /**
   * 非必填 引用文献样式区域点击事件，0或不填代表没有点击事件，1 代表跳转url，2 代表跳转小程序
   */
  private Integer type;
  /**
   * 点击跳转的url，quote_area.type是1时必填
   */
  private String url;
  /**
   * 点击跳转的小程序的appid，必须是与当前应用关联的小程序，quote_area.type是2时必填
   */
  private String appid;
  /**
   * 点击跳转的小程序的pagepath，quote_area.type是2时选填
   */
  private String pagepath;
  /**
   * 引用文献样式的标题
   */
  private String title;
  /**
   * 引用文献样式的引用文案
   */
  private String quoteText;

  public JsonObject toJson() {
    JsonObject quoteAreaJson = new JsonObject();
    if (null != this.getType()) {
      quoteAreaJson.addProperty("type", this.getType());
    }
    if (StringUtils.isNotBlank(this.getUrl())) {
      quoteAreaJson.addProperty("url", this.getUrl());
    }
    if (StringUtils.isNotBlank(this.getAppid())) {
      quoteAreaJson.addProperty("appid", this.getAppid());
    }
    if (StringUtils.isNotBlank(this.getPagepath())) {
      quoteAreaJson.addProperty("pagepath", this.getPagepath());
    }
    if (StringUtils.isNotBlank(this.getTitle())) {
      quoteAreaJson.addProperty("title", this.getTitle());
    }
    if (StringUtils.isNotBlank(this.getQuoteText())) {
      quoteAreaJson.addProperty("quote_text", this.getQuoteText());
    }
    return quoteAreaJson;
  }

}
