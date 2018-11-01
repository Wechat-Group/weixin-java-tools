package me.chanjar.weixin.common.error;

import lombok.Getter;

/**
 * 微信小程序错误码
 * @author <a href="https://github.com/biggates">biggates</a>
 */
@Getter
public enum WxMiniappErrorMsgEnum {
  /**
   * 获取 access_token 时 AppSecret 错误，或者 access_token 无效。请开发者认真比对 AppSecret 的正确性，或查看是否正在为恰当的小程序调用接口
   * <p>对应操作：<code>sendCustomerMessage</code></p>
   * <p>对应地址：
   *  <pre>POST https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=ACCESS_TOKEN</pre>
   * </p>
   * @see https://developers.weixin.qq.com/miniprogram/dev/api/open-api/customer-message/sendCustomerMessage.html
   */
  CODE_40001(40001, "access_token 无效或 AppSecret 错误"),
  /**
   * 不合法的凭证类型
   * <p>对应操作：<code>sendCustomerMessage</code></p>
   * <p>对应地址：
   *  <pre>POST https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=ACCESS_TOKEN</pre>
   * </p>
   * @see https://developers.weixin.qq.com/miniprogram/dev/api/open-api/customer-message/sendCustomerMessage.html
   */
  CODE_40002(40002, "不合法的凭证类型"),
  /**
   * touser不是正确的openid
   * <p>对应操作：<code>sendCustomerMessage</code>, <code>sendUniformMessage</code></p>
   * <p>对应地址：
   *  <pre>POST https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=ACCESS_TOKEN</pre>
   *  <pre>POST https://api.weixin.qq.com/cgi-bin/message/wxopen/template/uniform_send?access_token=ACCESS_TOKEN</pre>
   * </p>
   * @see https://developers.weixin.qq.com/miniprogram/dev/api/open-api/customer-message/sendCustomerMessage.html
   * @see https://developers.weixin.qq.com/miniprogram/dev/api/open-api/uniform-message/sendUniformMessage.html
   */
  CODE_40003(40003, "openid 不正确"),
  /**
   * 无效媒体文件类型
   * <p>对应操作：<code>uploadTempMedia</code></p>
   * <p>对应地址：
   *  <pre>POST https://api.weixin.qq.com/cgi-bin/media/upload?access_token=ACCESS_TOKEN&type=TYPE</pre>
   * </p>
   * @see https://developers.weixin.qq.com/miniprogram/dev/api/open-api/customer-message/uploadTempMedia.html
   */
  CODE_40004(40004, "无效媒体文件类型"),
  /**
   * 无效媒体文件 ID
   * <p>对应操作：<code>getTempMedia</code></p>
   * <p>对应地址：
   *  <pre>GET https://api.weixin.qq.com/cgi-bin/media/get?access_token=ACCESS_TOKEN&media_id=MEDIA_ID</pre>
   * </p>
   * @see https://developers.weixin.qq.com/miniprogram/dev/api/open-api/customer-message/getTempMedia.html
   */
  CODE_40007(40007, "无效媒体文件 ID"),
  /**
   * appid不正确，或者不符合绑定关系要求
   * <p>对应操作：<code>sendUniformMessage</code></p>
   * <p>对应地址：
   *  <pre>POST https://api.weixin.qq.com/cgi-bin/message/wxopen/template/uniform_send?access_token=ACCESS_TOKEN</pre>
   * </p>
   * @see https://developers.weixin.qq.com/miniprogram/dev/api/open-api/uniform-message/sendUniformMessage.html
   */
  CODE_40013(40013, "appid不正确，或者不符合绑定关系要求"),
  /**
   * template_id 不正确
   * <p>对应操作：<code>sendUniformMessage</code>, <code>sendTemplateMessage</code></p>
   * <p>对应地址：
   *  <pre>POST https://api.weixin.qq.com/cgi-bin/message/wxopen/template/uniform_send?access_token=ACCESS_TOKEN</pre>
   *  <pre>POST https://api.weixin.qq.com/cgi-bin/message/wxopen/template/send?access_token=ACCESS_TOKEN</pre>
   * </p>
   * @see https://developers.weixin.qq.com/miniprogram/dev/api/open-api/uniform-message/sendUniformMessage.html
   * @see https://developers.weixin.qq.com/miniprogram/dev/api/open-api/template-message/sendTemplateMessage.html
   */
  CODE_40037(40037, "template_id 不正确"),
  /**
   * form_id不正确，或者过期
   * <p>对应操作：<code>sendUniformMessage</code>, <code>sendTemplateMessage</code></p>
   * <p>对应地址：
   *  <pre>POST https://api.weixin.qq.com/cgi-bin/message/wxopen/template/uniform_send?access_token=ACCESS_TOKEN</pre>
   *  <pre>POST https://api.weixin.qq.com/cgi-bin/message/wxopen/template/send?access_token=ACCESS_TOKEN</pre>
   * </p>
   * @see https://developers.weixin.qq.com/miniprogram/dev/api/open-api/uniform-message/sendUniformMessage.html
   * @see https://developers.weixin.qq.com/miniprogram/dev/api/open-api/template-message/sendTemplateMessage.html
   */
  CODE_41028(41028, "form_id 不正确，或者过期"),
  /**
   * code 或 template_id 不正确
   * <p>对应操作：<code>code2Session</code>, <code>sendUniformMessage</code>, <code>sendTemplateMessage</code></p>
   * <p>对应地址：
   *  <pre>GET https://api.weixin.qq.com/sns/jscode2session?appid=APPID&secret=SECRET&js_code=JSCODE&grant_type=authorization_code</pre>
   *  <pre>POST https://api.weixin.qq.com/cgi-bin/message/wxopen/template/uniform_send?access_token=ACCESS_TOKEN</pre>
   *  <pre>POST https://api.weixin.qq.com/cgi-bin/message/wxopen/template/send?access_token=ACCESS_TOKEN</pre>
   * </p>
   * @see https://developers.weixin.qq.com/miniprogram/dev/api/open-api/login/code2Session.html
   * @see https://developers.weixin.qq.com/miniprogram/dev/api/open-api/uniform-message/sendUniformMessage.html
   * @see https://developers.weixin.qq.com/miniprogram/dev/api/open-api/template-message/sendTemplateMessage.html
   */
  CODE_41029(41029, "请求的参数不正确"),
  /**
   * form_id 已被使用，或者所传page页面不存在，或者小程序没有发布
   * <p>对应操作：<code>sendUniformMessage</coce>, <code>getWXACodeUnlimit</code></p>
   * <p>对应地址：
   *  <pre>POST https://api.weixin.qq.com/cgi-bin/message/wxopen/template/uniform_send?access_token=ACCESS_TOKEN</pre>
   *  <pre>POST https://api.weixin.qq.com/wxa/getwxacodeunlimit?access_token=ACCESS_TOKEN</pre>
   * </p>
   * @see https://developers.weixin.qq.com/miniprogram/dev/api/open-api/uniform-message/sendUniformMessage.html
   * @see https://developers.weixin.qq.com/miniprogram/dev/api/open-api/qr-code/getWXACodeUnlimit.html
   */
  CODE_41030(41030, "请求的参数不正确"),
  /**
   * 调用分钟频率受限
   * <p>对应操作：<code>getWXACodeUnlimit</code>, <code>sendUniformMessage</coce>, <code>sendTemplateMessage</code></p>
   * <p>对应地址：
   *  <pre>POST https://api.weixin.qq.com/cgi-bin/message/wxopen/template/uniform_send?access_token=ACCESS_TOKEN</pre>
   *  <pre>POST https://api.weixin.qq.com/wxa/getwxacodeunlimit?access_token=ACCESS_TOKEN</pre>
   *  <pre>POST https://api.weixin.qq.com/cgi-bin/message/wxopen/template/send?access_token=ACCESS_TOKEN</pre>
   * </p>
   * @see https://developers.weixin.qq.com/miniprogram/dev/api/open-api/uniform-message/sendUniformMessage.html
   * @see https://developers.weixin.qq.com/miniprogram/dev/api/open-api/qr-code/getWXACodeUnlimit.html
   */
  CODE_45009(45009, "调用分钟频率受限"),
  /**
   * 频率限制，每个用户每分钟100次
   * <p>对应操作：<code>code2Session</code></p>
   * <p>对应地址：
   *  <pre>GET https://api.weixin.qq.com/sns/jscode2session?appid=APPID&secret=SECRET&js_code=JSCODE&grant_type=authorization_code</pre>
   * </p>
   * @see https://developers.weixin.qq.com/miniprogram/dev/api/open-api/login/code2Session.html
   */
  CODE_45011(45011, "频率限制，每个用户每分钟100次"),
  /**
   * 回复时间超过限制
   * <p>对应操作：<code>sendCustomerMessage</code></p>
   * <p>对应地址：
   *  <pre>POST https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=ACCESS_TOKEN</pre>
   * </p>
   * @see https://developers.weixin.qq.com/miniprogram/dev/api/open-api/customer-message/sendCustomerMessage.html
   */
  CODE_45015(45015, "回复时间超过限制"),
  /**
   * 接口调用超过限额， 或生成码个数总和到达最大个数限制
   * <p>对应操作：<code>createWXAQRCode</code>, <code>sendTemplateMessage</code></p>
   * <p>对应地址：
   *  <pre>POST https://api.weixin.qq.com/cgi-bin/wxaapp/createwxaqrcode?access_token=ACCESS_TOKEN</pre>
   *  <pre>POST https://api.weixin.qq.com/cgi-bin/message/wxopen/template/send?access_token=ACCESS_TOKEN</pre>
   * </p>
   * @see https://developers.weixin.qq.com/miniprogram/dev/api/open-api/qr-code/getWXACode.html
   * @see https://developers.weixin.qq.com/miniprogram/dev/api/open-api/template-message/sendTemplateMessage.html
   */
  CODE_45029(45029, "接口调用超过限额"),
  /**
   * 客服接口下行条数超过上限
   * <p>对应操作：<code>sendCustomerMessage</code></p>
   * <p>对应地址：
   *  <pre>POST https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=ACCESS_TOKEN</pre>
   * </p>
   * @see https://developers.weixin.qq.com/miniprogram/dev/api/open-api/customer-message/sendCustomerMessage.html
   */
  CODE_45047(45047, "客服接口下行条数超过上限"),
  /**
   * command字段取值不对
   * <p>对应操作：<code>customerTyping</code>
   * <p>对应地址：
   *  <pre>POST https://api.weixin.qq.com/cgi-bin/message/custom/typing?access_token=ACCESS_TOKEN</pre>
   * </p>
   * @see https://developers.weixin.qq.com/miniprogram/dev/api/open-api/customer-message/customerTyping.html
   */
  CODE_45072(45072, "command字段取值不对"),
  /**
   * 下发输入状态，需要之前30秒内跟用户有过消息交互
   * <p>对应操作：<code>customerTyping</code>
   * <p>对应地址：
   *  <pre>POST https://api.weixin.qq.com/cgi-bin/message/custom/typing?access_token=ACCESS_TOKEN</pre>
   * </p>
   * @see https://developers.weixin.qq.com/miniprogram/dev/api/open-api/customer-message/customerTyping.html
   */
  CODE_45080(45080, "下发输入状态，需要之前30秒内跟用户有过消息交互"),
  /**
   * 已经在输入状态，不可重复下发
   * <p>对应操作：<code>customerTyping</code>
   * <p>对应地址：
   *  <pre>POST https://api.weixin.qq.com/cgi-bin/message/custom/typing?access_token=ACCESS_TOKEN</pre>
   * </p>
   * @see https://developers.weixin.qq.com/miniprogram/dev/api/open-api/customer-message/customerTyping.html
   */
  CODE_45081(45081, "已经在输入状态，不可重复下发"),
  /**
   * API 功能未授权，请确认小程序已获得该接口
   * <p>对应操作：<code>sendCustomerMessage</code></p>
   * <p>对应地址：
   *  <pre>POST https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=ACCESS_TOKEN</pre>
   * </p>
   * @see https://developers.weixin.qq.com/miniprogram/dev/api/open-api/customer-message/sendCustomerMessage.html
   */
  CODE_48001(48001, "API 功能未授权"),
  /**
   * 内容含有违法违规内容
   * <p>对应操作：<code>imgSecCheck</code>, <code>msgSecCheck</code></p>
   * <p>对应地址：
   *  <pre>POST https://api.weixin.qq.com/wxa/img_sec_check?access_token=ACCESS_TOKEN</pre>
   *  <pre>POST https://api.weixin.qq.com/wxa/msg_sec_check?access_token=ACCESS_TOKEN</pre>
   * </p>
   * @see https://developers.weixin.qq.com/miniprogram/dev/api/open-api/sec-check/imgSecCheck.html
   * @see https://developers.weixin.qq.com/miniprogram/dev/api/open-api/sec-check/msgSecCheck.html
   */
  CODE_87014(87014, "内容含有违法违规内容"),
  ;

  private int code;
  private String msg;

  WxMiniappErrorMsgEnum(int code, String msg) {
    this.code = code;
    this.msg = msg;
  }

  /**
   * 通过错误代码查找其中文含义.
   */
  public static String findMsgByCode(int code) {
    WxMiniappErrorMsgEnum[] values = WxMiniappErrorMsgEnum.values();
    for (WxMiniappErrorMsgEnum value : values) {
    if (value.code == code) {
      return value.msg;
    }
    }

    return null;
  }
}
