package cn.binarywang.wx.miniapp.bean.device;

import cn.binarywang.wx.miniapp.json.WxMaGsonBuilder;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 小程序设备订阅消息请求参数
 *
 * @author <a href="https://github.com/leejuncheng">JCLee</a>
 * @since 2021-12-16 17:13:22
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WxMaDeviceSubscribeMessageRequest implements Serializable {

  private static final long serialVersionUID = -7973228178407991299L;

  /**
   * 	接收者（用户）的 openid列表.
   */
  @SerializedName("to_openid_list")
  private List<String> toOpenidList;

  /**
   * 下发通知的设备唯⼀序列号。由⼚商⽣成
   */
  @SerializedName("sn")
  private String sn;

  /**
   * 所需下发的消息模板ID
   */
  @SerializedName("template_id")
  private String templateId;

  /**
   * 点击模板卡片后的跳转页面，仅限本小程序内的页面。支持带参数,（示例index?foo=bar）。该字段不填则模板无跳转.
   */
  @SerializedName("page")
  private String page;

  /**
   * 跳转小程序类型：developer为开发版；trial为体验版；formal为正式版；默认为正式版.
   */
  @SerializedName("miniprogram_state")
  private String miniprogramState;

  /**
   * 进入小程序查看”的语言类型，支持zh_CN(简体中文)、en_US(英文)、zh_HK(繁体中文)、zh_TW(繁体中文)，默认为zh_CN.
   */
  @SerializedName("lang")
  private String lang;

  /**
   * 	模板内容，格式形如 { "key1": { "value": any }, "key2": { "value": any } }.
   */
  @SerializedName("data")
  private Object data;

  public String toJson() {
    return WxMaGsonBuilder.create().toJson(this);
  }
}
