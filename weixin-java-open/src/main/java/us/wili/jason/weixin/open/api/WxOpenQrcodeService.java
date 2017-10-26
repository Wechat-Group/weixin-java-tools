package us.wili.jason.weixin.open.api;

import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.bean.result.WxMpQrCodeTicket;

import java.io.File;

/**
 * <pre>
 * 二维码相关操作接口
 * 文档地址：https://mp.weixin.qq.com/wiki?action=doc&id=mp1443433542&t=0.9274944716856435
 * </pre>
 *
 * @author Binary Wang
 */
public interface WxOpenQrcodeService {

  /**
   * <pre>
   * 换取临时二维码ticket
   * 详情请见: <a href="https://mp.weixin.qq.com/wiki?action=doc&id=mp1443433542&t=0.9274944716856435">生成带参数的二维码</a>
   * </pre>
   *
   * @param sceneId       场景值ID，临时二维码时为32位非0整型
   * @param expireSeconds 该二维码有效时间，以秒为单位。 最大不超过2592000（即30天），此字段如果不填，则默认有效期为30秒。
   */
  WxMpQrCodeTicket qrCodeCreateTmpTicket(String appid, int sceneId, Integer expireSeconds) throws WxErrorException;


  /**
   * <pre>
   * 换取临时二维码ticket
   * 详情请见: <a href="https://mp.weixin.qq.com/wiki?action=doc&id=mp1443433542&t=0.9274944716856435">生成带参数的二维码</a>
   * </pre>
   *
   * @param sceneStr      场景值ID（字符串形式的ID），字符串类型，长度限制为1到64
   * @param expireSeconds 该二维码有效时间，以秒为单位。 最大不超过2592000（即30天），此字段如果不填，则默认有效期为30秒。
   */
  WxMpQrCodeTicket qrCodeCreateTmpTicket(String appid, String sceneStr, Integer expireSeconds) throws WxErrorException;

  /**
   * <pre>
   * 换取永久二维码ticket
   * 详情请见: <a href="https://mp.weixin.qq.com/wiki?action=doc&id=mp1443433542&t=0.9274944716856435">生成带参数的二维码</a>
   * </pre>
   *
   * @param sceneId 场景值ID，最大值为100000（目前参数只支持1--100000）
   */
  WxMpQrCodeTicket qrCodeCreateLastTicket(String appid, int sceneId) throws WxErrorException;

  /**
   * <pre>
   * 换取永久字符串二维码ticket
   * 详情请见: <a href="https://mp.weixin.qq.com/wiki?action=doc&id=mp1443433542&t=0.9274944716856435">生成带参数的二维码</a>
   * </pre>
   *
   * @param sceneStr 参数。字符串类型长度现在为1到64
   */
  WxMpQrCodeTicket qrCodeCreateLastTicket(String appid, String sceneStr) throws WxErrorException;

  /**
   * <pre>
   * 换取二维码图片文件，jpg格式
   * 详情请见: <a href="https://mp.weixin.qq.com/wiki?action=doc&id=mp1443433542&t=0.9274944716856435">生成带参数的二维码</a>
   * </pre>
   *
   * @param ticket 二维码ticket
   */
  File qrCodePicture(String appid, WxMpQrCodeTicket ticket) throws WxErrorException;

  /**
   * <pre>
   * 换取二维码图片url地址（可以选择是否生成压缩的网址）
   * 详情请见: <a href="https://mp.weixin.qq.com/wiki?action=doc&id=mp1443433542&t=0.9274944716856435">生成带参数的二维码</a>
   * </pre>
   *
   * @param ticket       二维码ticket
   * @param needShortUrl 是否需要压缩的二维码地址
   */
  String qrCodePictureUrl(String appid, String ticket, boolean needShortUrl) throws WxErrorException;

  /**
   * <pre>
   * 换取二维码图片url地址
   * 详情请见: <a href="https://mp.weixin.qq.com/wiki?action=doc&id=mp1443433542&t=0.9274944716856435">生成带参数的二维码</a>
   * </pre>
   *
   * @param ticket 二维码ticket
   */
  String qrCodePictureUrl(String appid, String ticket) throws WxErrorException;

}
