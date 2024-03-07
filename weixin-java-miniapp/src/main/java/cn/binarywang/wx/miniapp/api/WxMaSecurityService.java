package cn.binarywang.wx.miniapp.api;

import cn.binarywang.wx.miniapp.bean.WxMaMediaAsyncCheckResult;
import cn.binarywang.wx.miniapp.bean.safety.request.WxMaUserSafetyRiskRankRequest;
import cn.binarywang.wx.miniapp.bean.safety.response.WxMaUserSafetyRiskRankResponse;
import cn.binarywang.wx.miniapp.bean.security.WxMaMediaSecCheckCheckRequest;
import cn.binarywang.wx.miniapp.bean.security.WxMaMsgSecCheckCheckRequest;
import cn.binarywang.wx.miniapp.bean.security.WxMaMsgSecCheckCheckResponse;
import me.chanjar.weixin.common.error.WxErrorException;

import java.io.File;

/**
 * <pre>
 * 小程序安全相关接口.
 * Created by Binary Wang on 2018/11/24.
 * </pre>
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
public interface WxMaSecurityService {
  /**
   * <pre>
   * 校验一张图片是否含有违法违规内容.
   * 应用场景举例：
   * 1）图片智能鉴黄：涉及拍照的工具类应用(如美拍，识图类应用)用户拍照上传检测；电商类商品上架图片检测；媒体类用户文章里的图片检测等；
   * 2）敏感人脸识别：用户头像；媒体类用户文章里的图片检测；社交类用户上传的图片检测等。频率限制：单个 appId 调用上限为 1000 次/分钟，100,000 次/天
   * 详情请见: <a href="https://developers.weixin.qq.com/miniprogram/dev/api/open-api/sec-check/imgSecCheck.html">https://developers.weixin.qq.com/miniprogram/dev/api/open-api/sec-check/imgSecCheck.html</a>
   * </pre>
   *
   * @param file the file
   * @return the boolean
   * @throws WxErrorException the wx error exception
   */
  boolean checkImage(File file) throws WxErrorException;

  /**
   * 校验一张图片是否含有违法违规内容
   *
   * @param fileUrl 文件网络地址
   * @return 是否违规 boolean
   * @throws WxErrorException .
   */
  boolean checkImage(String fileUrl) throws WxErrorException;

  /**
   * <pre>
   * 检查一段文本是否含有违法违规内容。
   * 应用场景举例：
   * 用户个人资料违规文字检测；
   * 媒体新闻类用户发表文章，评论内容检测；
   * 游戏类用户编辑上传的素材(如答题类小游戏用户上传的问题及答案)检测等。 频率限制：单个 appId 调用上限为 4000 次/分钟，2,000,000 次/天*
   * 详情请见: <a href="https://developers.weixin.qq.com/miniprogram/dev/api/open-api/sec-check/msgSecCheck.html">https://developers.weixin.qq.com/miniprogram/dev/api/open-api/sec-check/msgSecCheck.html</a>
   * </pre>
   *
   * @param msgString the msg string
   * @return the boolean
   * @throws WxErrorException the wx error exception
   */
  boolean checkMessage(String msgString) throws WxErrorException;


  /**
   * <pre>
   * 检查一段文本是否含有违法违规内容（新版本接口，主要是request和response做了参数优化）
   * 详情请见: <a href="https://developers.weixin.qq.com/miniprogram/dev/api-backend/open-api/sec-check/security.msgSecCheck.html">https://developers.weixin.qq.com/miniprogram/dev/api-backend/open-api/sec-check/security.msgSecCheck.html</a>
   * </pre>
   * @param msgRequest request
   * @return WxMaMsgSecCheckCheckResponse
   * @throws WxErrorException the wx error exception
   */
  WxMaMsgSecCheckCheckResponse checkMessage(WxMaMsgSecCheckCheckRequest msgRequest) throws WxErrorException;

  /**
   * <pre>
   * 异步校验图片/音频是否含有违法违规内容。
   * 应用场景举例：
   * 语音风险识别：社交类用户发表的语音内容检测；
   * 图片智能鉴黄：涉及拍照的工具类应用(如美拍，识图类应用)用户拍照上传检测；电商类商品上架图片检测；媒体类用户文章里的图片检测等；
   * 敏感人脸识别：用户头像；媒体类用户文章里的图片检测；社交类用户上传的图片检测等。
   * 频率限制：
   * 单个 appId 调用上限为 2000 次/分钟，200,000 次/天；文件大小限制：单个文件大小不超过10M
   * 详情请见:
   * <a href="https://developers.weixin.qq.com/miniprogram/dev/api-backend/open-api/sec-check/security.mediaCheckAsync.html">https://developers.weixin.qq.com/miniprogram/dev/api-backend/open-api/sec-check/security.mediaCheckAsync.html</a>
   * </pre>
   *
   * @param mediaUrl  要检测的多媒体url
   * @param mediaType 媒体类型,{@link cn.binarywang.wx.miniapp.constant.WxMaConstants.SecCheckMediaType}
   * @return wx ma media async check result
   * @throws WxErrorException the wx error exception
   */
  WxMaMediaAsyncCheckResult mediaCheckAsync(String mediaUrl, int mediaType) throws WxErrorException;

  /**
   * <pre>
   * 异步校验图片/音频是否含有违法违规内容。(新版本接口，主要对request和respone做了参数优化)
   * 应用场景举例：
   * 语音风险识别：社交类用户发表的语音内容检测；
   * 图片智能鉴黄：涉及拍照的工具类应用(如美拍，识图类应用)用户拍照上传检测；电商类商品上架图片检测；媒体类用户文章里的图片检测等；
   * 敏感人脸识别：用户头像；媒体类用户文章里的图片检测；社交类用户上传的图片检测等。
   * 频率限制：
   * 单个 appId 调用上限为 2000 次/分钟，200,000 次/天；文件大小限制：单个文件大小不超过10M
   * 详情请见:
   * <a href="https://developers.weixin.qq.com/miniprogram/dev/api-backend/open-api/sec-check/security.mediaCheckAsync.html">https://developers.weixin.qq.com/miniprogram/dev/api-backend/open-api/sec-check/security.mediaCheckAsync.html</a>
   * </pre>
   *
   * @param request 请求
   * @return wx ma media async check result
   * @throws WxErrorException the wx error exception
   */

  WxMaMediaAsyncCheckResult mediaCheckAsync(WxMaMediaSecCheckCheckRequest request) throws WxErrorException;

  /**
   * <pre>
   * 根据提交的用户信息数据获取用户的安全等级，无需用户授权
   * 文档：https://developers.weixin.qq.com/miniprogram/dev/api-backend/open-api/safety-control-capability/riskControl.getUserRiskRank.html
   * </pre>
   *
   * @param wxMaUserSafetyRiskRankRequest 获取用户安全等级请求
   * @throws WxErrorException 通用异常
   */
  WxMaUserSafetyRiskRankResponse getUserRiskRank(WxMaUserSafetyRiskRankRequest wxMaUserSafetyRiskRankRequest) throws WxErrorException;

}
