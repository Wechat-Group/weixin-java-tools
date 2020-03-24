package cn.binarywang.wx.miniapp.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import cn.binarywang.wx.miniapp.util.json.WxMaGsonBuilder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 模板消息.
 * 参考 https://developers.weixin.qq.com/miniprogram/dev/api-backend/templateMessage.send.html
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WxMaTemplateMessage implements Serializable {
  private static final long serialVersionUID = 5063374783759519418L;

  /**
   * 接收者（用户）的 openid.
   * <pre>
   * 参数：touser
   * 是否必填： 是
   * 描述： 接收者（用户）的 openid
   * </pre>
   */
  private String toUser;

  /**
   * 所需下发的模板消息的id.
   * <pre>
   * 参数：template_id
   * 是否必填： 是
   * 描述： 所需下发的模板消息的id
   * </pre>
   */
  private String templateId;

  /**
   * 点击模板卡片后的跳转页面，仅限本小程序内的页面.
   * <pre>
   * 参数：page
   * 是否必填： 否
   * 描述： 点击模板卡片后的跳转页面，仅限本小程序内的页面。支持带参数,（示例index?foo=bar）。该字段不填则模板无跳转。
   * </pre>
   */
  private String page;

  /**
   * 表单提交场景下，为 submit 事件带上的 formId；支付场景下，为本次支付的 prepay_id.
   * <pre>
   * 参数：form_id
   * 是否必填： 是
   * 描述： 表单提交场景下，为 submit 事件带上的 formId；支付场景下，为本次支付的 prepay_id
   * </pre>
   */
  private String formId;

  /**
   * 模板内容，不填则下发空模板.
   * <pre>
   * 参数：data
   * 是否必填： 是
   * 描述： 模板内容，不填则下发空模板
   * </pre>
   */
  private List<WxMaTemplateData> data;

  /**
   * 模板需要放大的关键词，不填则默认无放大.
   * <pre>
   * 参数：emphasis_keyword
   * 是否必填： 否
   * 描述： 模板需要放大的关键词，不填则默认无放大
   * </pre>
   */
  private String emphasisKeyword;

  public WxMaTemplateMessage addData(WxMaTemplateData datum) {
    if (this.data == null) {
      this.data = new ArrayList<>();
    }
    this.data.add(datum);

    return this;
  }

  public String toJson() {
    return WxMaGsonBuilder.create().toJson(this);
  }

}
