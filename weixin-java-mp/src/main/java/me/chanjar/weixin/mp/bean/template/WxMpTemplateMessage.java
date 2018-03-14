package me.chanjar.weixin.mp.bean.template;

import lombok.Builder;
import lombok.Data;
import me.chanjar.weixin.mp.util.json.WxMpGsonBuilder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 参考 http://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1433751277&token=&lang=zh_CN 发送模板消息接口部分
 */
@Data
@Builder
public class WxMpTemplateMessage implements Serializable {
  private static final long serialVersionUID = 5063374783759519418L;

  /**
   * 接收者openid
   */
  private String toUser;

  /**
   * 模板ID
   */
  private String templateId;

  /**
   * <pre>
   * 跳小程序所需数据，不需跳小程序可不用传该数据
   * url和miniprogram都是非必填字段，若都不传则模板无跳转；若都传，会优先跳转至小程序。
   * 开发者可根据实际需要选择其中一种跳转方式即可。当用户的微信客户端版本不支持跳小程序时，将会跳转至url。
   * </pre>
   */
  private String url;
  /**
   * 模板跳转链接
   *
   * @see #url
   */
  private MiniProgram miniProgram;

  /**
   * 模板数据
   */
  @Builder.Default
  private final List<WxMpTemplateData> data = new ArrayList<>();

  public void addWxMpTemplateData(WxMpTemplateData datum) {
    this.data.add(datum);
  }

  public String toJson() {
    return WxMpGsonBuilder.INSTANCE.create().toJson(this);
  }

  @Data
  public static class MiniProgram implements Serializable {
    private static final long serialVersionUID = -7945254706501974849L;

    private String appid;
    private String pagePath;

    public MiniProgram(String appid, String pagePath) {
      this.appid = appid;
      this.pagePath = pagePath;
    }

  }

}
