package me.chanjar.weixin.mp.api;

import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.bean.*;
import me.chanjar.weixin.mp.bean.result.WxMpMassSendResult;
import me.chanjar.weixin.mp.bean.result.WxMpMassUploadResult;

/**
 * <pre>
 * 群发消息服务类.
 * Created by Binary Wang on 2017-8-16.
 * </pre>
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
public interface WxMpMassMessageService {
  /**
   * 上传群发用的图文消息.
   */
  String MEDIA_UPLOAD_NEWS_URL = "https://api.weixin.qq.com/cgi-bin/media/uploadnews";
  /**
   * 上传群发用的视频.
   */
  String MEDIA_UPLOAD_VIDEO_URL = "https://api.weixin.qq.com/cgi-bin/media/uploadvideo";
  /**
   * 分组群发消息.
   */
  String MESSAGE_MASS_SENDALL_URL = "https://api.weixin.qq.com/cgi-bin/message/mass/sendall";
  /**
   * 按openId列表群发消息.
   */
  String MESSAGE_MASS_SEND_URL = "https://api.weixin.qq.com/cgi-bin/message/mass/send";
  /**
   * 群发消息预览接口.
   */
  String MESSAGE_MASS_PREVIEW_URL = "https://api.weixin.qq.com/cgi-bin/message/mass/preview";
  /**
   * 删除群发接口.
   */
  String MESSAGE_MASS_DELETE_URL = "https://api.weixin.qq.com/cgi-bin/message/mass/delete";

  /**
   * <pre>
   * 上传群发用的图文消息，上传后才能群发图文消息.
   *
   * 详情请见: http://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421140549&token=&lang=zh_CN
   * </pre>
   *
   * @see #massGroupMessageSend(WxMpMassTagMessage)
   * @see #massOpenIdsMessageSend(WxMpMassOpenIdsMessage)
   */
  WxMpMassUploadResult massNewsUpload(WxMpMassNews news) throws WxErrorException;

  /**
   * <pre>
   * 上传群发用的视频，上传后才能群发视频消息.
   * 详情请见: http://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421140549&token=&lang=zh_CN
   * </pre>
   *
   * @see #massGroupMessageSend(WxMpMassTagMessage)
   * @see #massOpenIdsMessageSend(WxMpMassOpenIdsMessage)
   */
  WxMpMassUploadResult massVideoUpload(WxMpMassVideo video) throws WxErrorException;

  /**
   * <pre>
   * 分组群发消息.
   * 如果发送图文消息，必须先使用 {@link #massNewsUpload(WxMpMassNews)} 获得media_id，然后再发送
   * 如果发送视频消息，必须先使用 {@link #massVideoUpload(WxMpMassVideo)} 获得media_id，然后再发送
   * 详情请见: http://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421140549&token=&lang=zh_CN
   * </pre>
   */
  WxMpMassSendResult massGroupMessageSend(WxMpMassTagMessage message) throws WxErrorException;

  /**
   * <pre>
   * 按openId列表群发消息.
   * 如果发送图文消息，必须先使用 {@link #massNewsUpload(WxMpMassNews)} 获得media_id，然后再发送
   * 如果发送视频消息，必须先使用 {@link #massVideoUpload(WxMpMassVideo)} 获得media_id，然后再发送
   * 详情请见: http://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421140549&token=&lang=zh_CN
   * </pre>
   */
  WxMpMassSendResult massOpenIdsMessageSend(WxMpMassOpenIdsMessage message) throws WxErrorException;

  /**
   * <pre>
   * 群发消息预览接口.
   * 开发者可通过该接口发送消息给指定用户，在手机端查看消息的样式和排版。为了满足第三方平台开发者的需求，在保留对openID预览能力的同时，增加了对指定微信号发送预览的能力，但该能力每日调用次数有限制（100次），请勿滥用。
   * 接口调用请求说明
   *  http请求方式: POST
   *  https://api.weixin.qq.com/cgi-bin/message/mass/preview?access_token=ACCESS_TOKEN
   * 详情请见：http://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421140549&token=&lang=zh_CN
   * </pre>
   *
   * @return wxMpMassSendResult
   */
  WxMpMassSendResult massMessagePreview(WxMpMassPreviewMessage wxMpMassPreviewMessage) throws WxErrorException;

  /**
   * <pre>
   * 删除群发.
   * 群发之后，随时可以通过该接口删除群发。
   * 请注意：
   * 1、只有已经发送成功的消息才能删除
   * 2、删除消息是将消息的图文详情页失效，已经收到的用户，还是能在其本地看到消息卡片。
   * 3、删除群发消息只能删除图文消息和视频消息，其他类型的消息一经发送，无法删除。
   * 4、如果多次群发发送的是一个图文消息，那么删除其中一次群发，就会删除掉这个图文消息也，导致所有群发都失效
   * 接口调用请求说明：
   *  http请求方式: POST
   *  https://api.weixin.qq.com/cgi-bin/message/mass/delete?access_token=ACCESS_TOKEN
   * 详情请见：https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1481187827_i0l21
   * </pre>
   *
   * @param msgId        发送出去的消息ID
   * @param articleIndex 要删除的文章在图文消息中的位置，第一篇编号为1，该字段不填或填0会删除全部文章
   */
  void delete(Long msgId, Integer articleIndex) throws WxErrorException;

}
