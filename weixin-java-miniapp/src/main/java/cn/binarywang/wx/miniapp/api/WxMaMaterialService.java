package cn.binarywang.wx.miniapp.api;

import me.chanjar.weixin.common.bean.result.WxMediaUploadResult;
import me.chanjar.weixin.common.error.WxErrorException;

import java.io.File;

/**
 * <pre>
 * 素材管理的相关接口
 * 文档地址：https://developers.weixin.qq.com/doc/offiaccount/Asset_Management/New_temporary_materials.html
 * Created by lipengjun on 2020/6/29.
 * </pre>
 *
 * @author <a href="https://github.com/lipengjun92">lipengjun (939961241@qq.com)</a>
 */
public interface WxMaMaterialService {
  String MEDIA_UPLOAD_URL = "https://api.weixin.qq.com/cgi-bin/media/upload?type=%s";
  /**
   * <pre>
   * 新增临时素材
   * 小程序获取临时素材，用于直播间商品
   * 素材管理接口对所有认证的订阅号和服务号开放。通过本接口，小程序可以新增临时素材（即上传临时多媒体文件），返回的mediaId用于直播间商品使用。
   * 请注意：
   *  1、对于临时素材，每个素材（media_id）会在开发者上传或粉丝发送到微信服务器3天后自动删除（所以用户发送给开发者的素材，若开发者需要，应尽快下载到本地），以节省服务器资源。
   *  2、media_id是可复用的。
   *  3、素材的格式大小等要求与公众平台官网一致。具体是，图片大小不超过2M，支持png/jpeg/jpg/gif格式，语音大小不超过5M，长度不超过60秒，支持mp3/amr格式
   *  4、需使用https调用本接口。
   *  本接口即为原“上传多媒体文件”接口。
   *  注意事项：
   *    上传的临时多媒体文件有格式和大小限制，如下：
   *    图片（image）: 2M，支持PNG\JPEG\JPG\GIF格式
   *    语音（voice）：2M，播放长度不超过60s，支持AMR\MP3格式
   *    视频（video）：10MB，支持MP4格式
   *    缩略图（thumb）：64KB，支持JPG格式
   * 媒体文件在后台保存时间为3天，即3天后media_id失效。
   * 详情请见: <a href="http://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1444738726&token=&lang=zh_CN">新增临时素材</a>
   * 接口url格式：https://api.weixin.qq.com/cgi-bin/media/upload?access_token=ACCESS_TOKEN&type=TYPE
   * </pre>
   *
   * @param mediaType 媒体类型, 请看{@link me.chanjar.weixin.common.api.WxConsts}
   * @param file      文件对象
   * @throws WxErrorException
   */
  WxMediaUploadResult mediaUpload(String mediaType, File file) throws WxErrorException;
}
