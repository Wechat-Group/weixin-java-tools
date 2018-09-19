package me.chanjar.weixin.mp.api;

import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.AiLangType;

import java.io.File;

/**
 * <pre>
 * 微信AI开放接口（语音识别，微信翻译）.
 * https://mp.weixin.qq.com/wiki?t=resource/res_main&id=21516712282KzWVE
 *  Created by BinaryWang on 2018/6/9.
 * </pre>
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
public interface WxMpAiOpenService {
  String VOICE_UPLOAD_URL = "http://api.weixin.qq.com/cgi-bin/media/voice/addvoicetorecofortext?format=%s&voice_id=%s&lang=%s";
  String VOICE_QUERY_RESULT_URL = "http://api.weixin.qq.com/cgi-bin/media/voice/queryrecoresultfortext";

  /**
   * <pre>
   * 提交语音.
   * 接口调用请求说明
   *
   * http请求方式: POST
   * http://api.weixin.qq.com/cgi-bin/media/voice/addvoicetorecofortext?access_token=ACCESS_TOKEN&format=&voice_id=xxxxxx&lang=zh_CN
   * 参数说明
   *
   * 参数	是否必须	说明
   * access_token	是	接口调用凭证
   * format	是	文件格式 （只支持mp3，16k，单声道，最大1M）
   * voice_id	是	语音唯一标识
   * lang	否	语言，zh_CN 或 en_US，默认中文
   * 语音内容放body里或者上传文件的形式
   * </pre>
   *
   * @param lang      语言，zh_CN 或 en_US，默认中文
   * @param voiceFile 语音文件
   * @param voiceId   语音唯一标识
   */
  void uploadVoice(String voiceId, AiLangType lang, File voiceFile) throws WxErrorException;

  /**
   * <pre>
   * 获取语音识别结果.
   * 接口调用请求说明
   *
   * http请求方式: POST
   * http://api.weixin.qq.com/cgi-bin/media/voice/queryrecoresultfortext?access_token=ACCESS_TOKEN&voice_id=xxxxxx&lang=zh_CN
   * 请注意，添加完文件之后10s内调用这个接口
   *
   * 参数说明
   *
   * 参数	是否必须	说明
   * access_token	是	接口调用凭证
   * voice_id	是	语音唯一标识
   * lang	否	语言，zh_CN 或 en_US，默认中文
   * </pre>
   *
   * @param lang    语言，zh_CN 或 en_US，默认中文
   * @param voiceId 语音唯一标识
   */
  String queryRecognitionResult(String voiceId, AiLangType lang) throws WxErrorException;

  /**
   * 识别指定语音文件内容.
   * 此方法揉合了前两两个方法：uploadVoice 和 queryRecognitionResult
   *
   * @param lang      语言，zh_CN 或 en_US，默认中文
   * @param voiceFile 语音文件
   * @param voiceId   语音唯一标识
   */
  String recogniseVoice(String voiceId, AiLangType lang, File voiceFile) throws WxErrorException;

  /**
   * <pre>
   * 微信翻译.
   * 接口调用请求说明
   *
   * http请求方式: POST
   * http://api.weixin.qq.com/cgi-bin/media/voice/translatecontent?access_token=ACCESS_TOKEN&lfrom=xxx&lto=xxx
   * 参数说明
   *
   * 参数	是否必须	说明
   * access_token	是	接口调用凭证
   * lfrom	是	源语言，zh_CN 或 en_US
   * lto	是	目标语言，zh_CN 或 en_US
   * 源内容放body里或者上传文件的形式（utf8格式，最大600Byte)
   * </pre>
   *
   * @param langFrom 源语言，zh_CN 或 en_US
   * @param langTo 目标语言，zh_CN 或 en_US
   * @param content 要翻译的文本内容
   */
  String translate(AiLangType langFrom, AiLangType langTo, String content) throws WxErrorException;
}
