package me.chanjar.weixin.common.error;

import lombok.Getter;

/**
 * 微信小程序错误码
 *
 * @author <a href="https://github.com/biggates">biggates</a>
 */
@Getter
public enum WxMaErrorMsgEnum {
  /**
   * <pre>
   * 获取 access_token 时 AppSecret 错误，
   * 或者 access_token 无效。请开发者认真比对 AppSecret 的正确性，或查看是否正在为恰当的小程序调用接口
   * 对应操作：<code>sendCustomerMessage</code>
   * 对应地址：
   * POST https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=ACCESS_TOKEN
   * 参考文档地址： https://developers.weixin.qq.com/miniprogram/dev/api/open-api/customer-message/sendCustomerMessage.html
   * </pre>
   */
  CODE_40001(40001, "access_token 无效或 AppSecret 错误"),
  /**
   * <pre>
   * 不合法的凭证类型
   * 对应操作：<code>sendCustomerMessage</code>
   * 对应地址：
   * POST https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=ACCESS_TOKEN
   * 参考文档地址： https://developers.weixin.qq.com/miniprogram/dev/api/open-api/customer-message/sendCustomerMessage.html
   * </pre>
   */
  CODE_40002(40002, "不合法的凭证类型"),
  /**
   * <pre>
   * touser不是正确的openid.
   * 对应操作：<code>sendCustomerMessage</code>, <code>sendUniformMessage</code>
   * 对应地址：
   * POST https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=ACCESS_TOKEN
   * POST https://api.weixin.qq.com/cgi-bin/message/wxopen/template/uniform_send?access_token=ACCESS_TOKEN
   * 参考文档地址： https://developers.weixin.qq.com/miniprogram/dev/api/open-api/customer-message/sendCustomerMessage.html
   * https://developers.weixin.qq.com/miniprogram/dev/api/open-api/uniform-message/sendUniformMessage.html
   * </pre>
   */
  CODE_40003(40003, "openid 不正确"),
  /**
   * <pre>
   * 无效媒体文件类型
   * 对应操作：<code>uploadTempMedia</code>
   * 对应地址：
   * POST https://api.weixin.qq.com/cgi-bin/media/upload?access_token=ACCESS_TOKEN&type=TYPE
   * 参考文档地址： https://developers.weixin.qq.com/miniprogram/dev/api/open-api/customer-message/uploadTempMedia.html
   * </pre>
   */
  CODE_40004(40004, "无效媒体文件类型"),
  /**
   * <pre>
   * 无效媒体文件 ID.
   * 对应操作：<code>getTempMedia</code>
   * 对应地址：
   * GET https://api.weixin.qq.com/cgi-bin/media/get?access_token=ACCESS_TOKEN&media_id=MEDIA_ID
   * 参考文档地址： https://developers.weixin.qq.com/miniprogram/dev/api/open-api/customer-message/getTempMedia.html
   * </pre>
   */
  CODE_40007(40007, "无效媒体文件 ID"),
  /**
   * <pre>
   * appid不正确，或者不符合绑定关系要求.
   * 对应操作：<code>sendUniformMessage</code>
   * 对应地址：
   * POST https://api.weixin.qq.com/cgi-bin/message/wxopen/template/uniform_send?access_token=ACCESS_TOKEN
   * 参考文档地址： https://developers.weixin.qq.com/miniprogram/dev/api/open-api/uniform-message/sendUniformMessage.html
   * </pre>
   */
  CODE_40013(40013, "appid不正确，或者不符合绑定关系要求"),
  /**
   * <pre>
   * template_id 不正确.
   * 对应操作：<code>sendUniformMessage</code>, <code>sendTemplateMessage</code>
   * 对应地址：
   * POST https://api.weixin.qq.com/cgi-bin/message/wxopen/template/uniform_send?access_token=ACCESS_TOKEN
   * POST https://api.weixin.qq.com/cgi-bin/message/wxopen/template/send?access_token=ACCESS_TOKEN
   * 参考文档地址： https://developers.weixin.qq.com/miniprogram/dev/api/open-api/uniform-message/sendUniformMessage.html
   * https://developers.weixin.qq.com/miniprogram/dev/api/open-api/template-message/sendTemplateMessage.html
   * </pre>
   */
  CODE_40037(40037, "template_id 不正确"),
  /**
   * <pre>
   * form_id不正确，或者过期.
   * 对应操作：<code>sendUniformMessage</code>, <code>sendTemplateMessage</code>
   * 对应地址：
   * POST https://api.weixin.qq.com/cgi-bin/message/wxopen/template/uniform_send?access_token=ACCESS_TOKEN
   * POST https://api.weixin.qq.com/cgi-bin/message/wxopen/template/send?access_token=ACCESS_TOKEN
   * 参考文档地址： https://developers.weixin.qq.com/miniprogram/dev/api/open-api/uniform-message/sendUniformMessage.html
   * https://developers.weixin.qq.com/miniprogram/dev/api/open-api/template-message/sendTemplateMessage.html
   * </pre>
   */
  CODE_41028(41028, "form_id 不正确，或者过期"),
  /**
   * <pre>
   * code 或 template_id 不正确.
   * 对应操作：<code>code2Session</code>, <code>sendUniformMessage</code>, <code>sendTemplateMessage</code>
   * 对应地址：
   * GET https://api.weixin.qq.com/sns/jscode2session?appid=APPID&secret=SECRET&js_code=JSCODE&grant_type=authorization_code
   * POST https://api.weixin.qq.com/cgi-bin/message/wxopen/template/uniform_send?access_token=ACCESS_TOKEN
   * POST https://api.weixin.qq.com/cgi-bin/message/wxopen/template/send?access_token=ACCESS_TOKEN
   * 参考文档地址： https://developers.weixin.qq.com/miniprogram/dev/api/open-api/login/code2Session.html
   * https://developers.weixin.qq.com/miniprogram/dev/api/open-api/uniform-message/sendUniformMessage.html
   * https://developers.weixin.qq.com/miniprogram/dev/api/open-api/template-message/sendTemplateMessage.html
   * </pre>
   */
  CODE_41029(41029, "请求的参数不正确"),
  /**
   * <pre>
   * form_id 已被使用，或者所传page页面不存在，或者小程序没有发布
   * 对应操作：<code>sendUniformMessage</coce>, <code>getWXACodeUnlimit</code>
   * 对应地址：
   * POST https://api.weixin.qq.com/cgi-bin/message/wxopen/template/uniform_send?access_token=ACCESS_TOKEN
   * POST https://api.weixin.qq.com/wxa/getwxacodeunlimit?access_token=ACCESS_TOKEN
   * 参考文档地址： https://developers.weixin.qq.com/miniprogram/dev/api/open-api/uniform-message/sendUniformMessage.html
   *  https://developers.weixin.qq.com/miniprogram/dev/api/open-api/qr-code/getWXACodeUnlimit.html
   * </pre>
   */
  CODE_41030(41030, "请求的参数不正确"),
  /**
   * <pre>
   * 调用分钟频率受限.
   * 对应操作：<code>getWXACodeUnlimit</code>, <code>sendUniformMessage</code>, <code>sendTemplateMessage</code>
   * 对应地址：
   * POST https://api.weixin.qq.com/cgi-bin/message/wxopen/template/uniform_send?access_token=ACCESS_TOKEN
   * POST https://api.weixin.qq.com/wxa/getwxacodeunlimit?access_token=ACCESS_TOKEN
   * POST https://api.weixin.qq.com/cgi-bin/message/wxopen/template/send?access_token=ACCESS_TOKEN
   * 参考文档地址： https://developers.weixin.qq.com/miniprogram/dev/api/open-api/uniform-message/sendUniformMessage.html
   * https://developers.weixin.qq.com/miniprogram/dev/api/open-api/qr-code/getWXACodeUnlimit.html
   * </pre>
   */
  CODE_45009(45009, "调用分钟频率受限"),
  /**
   * <pre>
   * 频率限制，每个用户每分钟100次.
   * 对应操作：<code>code2Session</code>
   * 对应地址：
   * GET https://api.weixin.qq.com/sns/jscode2session?appid=APPID&secret=SECRET&js_code=JSCODE&grant_type=authorization_code
   * 参考文档地址： https://developers.weixin.qq.com/miniprogram/dev/api/open-api/login/code2Session.html
   * </pre>
   */
  CODE_45011(45011, "频率限制，每个用户每分钟100次"),
  /**
   * <pre>
   * 回复时间超过限制.
   * 对应操作：<code>sendCustomerMessage</code>
   * 对应地址：
   * POST https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=ACCESS_TOKEN
   * 参考文档地址： https://developers.weixin.qq.com/miniprogram/dev/api/open-api/customer-message/sendCustomerMessage.html
   * </pre>
   */
  CODE_45015(45015, "回复时间超过限制"),
  /**
   * <pre>
   * 接口调用超过限额， 或生成码个数总和到达最大个数限制.
   * 对应操作：<code>createWXAQRCode</code>, <code>sendTemplateMessage</code>
   * 对应地址：
   * POST https://api.weixin.qq.com/cgi-bin/wxaapp/createwxaqrcode?access_token=ACCESS_TOKEN
   * POST https://api.weixin.qq.com/cgi-bin/message/wxopen/template/send?access_token=ACCESS_TOKEN
   * 参考文档地址： https://developers.weixin.qq.com/miniprogram/dev/api/open-api/qr-code/getWXACode.html
   * https://developers.weixin.qq.com/miniprogram/dev/api/open-api/template-message/sendTemplateMessage.html
   * </pre>
   */
  CODE_45029(45029, "接口调用超过限额"),
  /**
   * <pre>
   * 客服接口下行条数超过上限.
   * 对应操作：<code>sendCustomerMessage</code>
   * 对应地址：
   * POST https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=ACCESS_TOKEN
   * 参考文档地址： https://developers.weixin.qq.com/miniprogram/dev/api/open-api/customer-message/sendCustomerMessage.html
   * </pre>
   */
  CODE_45047(45047, "客服接口下行条数超过上限"),
  /**
   * <pre>
   * command字段取值不对
   * 对应操作：<code>customerTyping</code>
   * 对应地址：
   * POST https://api.weixin.qq.com/cgi-bin/message/custom/typing?access_token=ACCESS_TOKEN
   * 参考文档地址： https://developers.weixin.qq.com/miniprogram/dev/api/open-api/customer-message/customerTyping.html
   * </pre>
   */
  CODE_45072(45072, "command字段取值不对"),
  /**
   * <pre>
   * 下发输入状态，需要之前30秒内跟用户有过消息交互.
   * 对应操作：<code>customerTyping</code>
   * 对应地址：
   * POST https://api.weixin.qq.com/cgi-bin/message/custom/typing?access_token=ACCESS_TOKEN
   * 参考文档地址： https://developers.weixin.qq.com/miniprogram/dev/api/open-api/customer-message/customerTyping.html
   */
  CODE_45080(45080, "下发输入状态，需要之前30秒内跟用户有过消息交互"),
  /**
   * <pre>
   * 已经在输入状态，不可重复下发.
   * 对应操作：<code>customerTyping</code>
   * 对应地址：
   * POST https://api.weixin.qq.com/cgi-bin/message/custom/typing?access_token=ACCESS_TOKEN
   * 参考文档地址： https://developers.weixin.qq.com/miniprogram/dev/api/open-api/customer-message/customerTyping.html
   * </pre>
   */
  CODE_45081(45081, "已经在输入状态，不可重复下发"),
  /**
   * <pre>
   * API 功能未授权，请确认小程序已获得该接口.
   * 对应操作：<code>sendCustomerMessage</code>
   * 对应地址：
   * POST https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=ACCESS_TOKEN
   * 参考文档地址： https://developers.weixin.qq.com/miniprogram/dev/api/open-api/customer-message/sendCustomerMessage.html
   * </pre>
   */
  CODE_48001(48001, "API 功能未授权"),
  /**
   * <pre>
   * 内容含有违法违规内容.
   * 对应操作：<code>imgSecCheck</code>, <code>msgSecCheck</code>
   * 对应地址：
   * POST https://api.weixin.qq.com/wxa/img_sec_check?access_token=ACCESS_TOKEN
   * POST https://api.weixin.qq.com/wxa/msg_sec_check?access_token=ACCESS_TOKEN
   * 参考文档地址： https://developers.weixin.qq.com/miniprogram/dev/api/open-api/sec-check/imgSecCheck.html
   * https://developers.weixin.qq.com/miniprogram/dev/api/open-api/sec-check/msgSecCheck.html
   * </pre>
   */
  CODE_87014(87014, "内容含有违法违规内容");

  private int code;
  private String msg;

  WxMaErrorMsgEnum(int code, String msg) {
    this.code = code;
    this.msg = msg;
  }

  /**
   * <pre>
   * 通过错误代码查找其中文含义.
   */
  public static String findMsgByCode(int code) {
    for (WxMaErrorMsgEnum value : WxMaErrorMsgEnum.values()) {
      if (value.code == code) {
        return value.msg;
      }
    }

    return null;
  }
}
