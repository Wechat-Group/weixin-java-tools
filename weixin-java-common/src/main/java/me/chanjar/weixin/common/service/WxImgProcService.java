package me.chanjar.weixin.common.service;

import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.bean.imgproc.WxImgProcAiCropResult;
import me.chanjar.weixin.common.bean.imgproc.WxImgProcQrCodeResult;
import me.chanjar.weixin.common.bean.imgproc.WxImgProcSuperResolutionResult;

import java.io.File;

/**
 * 多项图像处理能力相关的API.
 * https://developers.weixin.qq.com/doc/offiaccount/Intelligent_Interface/Img_Proc.html
 *
 * @author Theo Nie
 */
public interface WxImgProcService {

  /**
   * 二维码/条码识别接口
   * 说明：
   * 1.图片支持使用img参数实时上传，也支持使用img_url参数传送图片地址，由微信后台下载图片进行识别
   * 2.文件大小限制：小于2M
   * 3.支持条码、二维码、DataMatrix和PDF417的识别。
   * 4.二维码、DataMatrix会返回位置坐标，条码和PDF417暂不返回位置坐标。
   *
   * @param imgUrl 图片url地址
   * @return WxMpImgProcQrCodeResult
   * @throws WxErrorException .
   */
  WxImgProcQrCodeResult qrCode(String imgUrl) throws WxErrorException;

  /**
   * 二维码/条码识别接口
   * 说明：
   * 1.图片支持使用img参数实时上传，也支持使用img_url参数传送图片地址，由微信后台下载图片进行识别
   * 2.文件大小限制：小于2M
   * 3.支持条码、二维码、DataMatrix和PDF417的识别。
   * 4.二维码、DataMatrix会返回位置坐标，条码和PDF417暂不返回位置坐标。
   *
   * @param imgFile 图片文件对象
   * @return WxMpImgProcQrCodeResult
   * @throws WxErrorException .
   */
  WxImgProcQrCodeResult qrCode(File imgFile) throws WxErrorException;

  /**
   * 图片高清化接口
   * 说明：
   * 1.图片支持使用img参数实时上传，也支持使用img_url参数传送图片地址，由微信后台下载图片进行识别
   * 2.文件大小限制：小于2M
   * 3.目前支持将图片超分辨率高清化2倍，即生成图片分辨率为原图2倍大小
   * 返回的media_id有效期为3天，期间可以通过“获取临时素材”接口获取图片二进制
   *
   * @param imgUrl 图片url地址
   * @return WxMpImgProcSuperResolutionResult
   * @throws WxErrorException .
   */
  WxImgProcSuperResolutionResult superResolution(String imgUrl) throws WxErrorException;

  /**
   * 图片高清化接口
   * 说明：
   * 1.图片支持使用img参数实时上传，也支持使用img_url参数传送图片地址，由微信后台下载图片进行识别
   * 2.文件大小限制：小于2M
   * 3.目前支持将图片超分辨率高清化2倍，即生成图片分辨率为原图2倍大小
   * 返回的media_id有效期为3天，期间可以通过“获取临时素材”接口获取图片二进制
   *
   * @param imgFile 图片文件对象
   * @return WxMpImgProcSuperResolutionResult
   * @throws WxErrorException .
   */
  WxImgProcSuperResolutionResult superResolution(File imgFile) throws WxErrorException;

  /**
   * 图片智能裁剪接口
   * 说明：
   * 1.图片支持使用img参数实时上传，也支持使用img_url参数传送图片地址，由微信后台下载图片进行识别
   * 2.文件大小限制：小于2M
   * 3.该接口默认使用最佳宽高比
   * @param imgUrl 图片url地址
   * @return WxMpImgProcAiCropResult
   * @throws WxErrorException .
   */
  WxImgProcAiCropResult aiCrop(String imgUrl) throws WxErrorException;

  /**
   * 图片智能裁剪接口
   * 说明：
   * 1.图片支持使用img参数实时上传，也支持使用img_url参数传送图片地址，由微信后台下载图片进行识别
   * 2.文件大小限制：小于2M
   * @param imgUrl 图片url地址
   * @param ratios 宽高比，最多支持5个，请以英文逗号分隔
   * @return WxMpImgProcAiCropResult
   * @throws WxErrorException .
   */
  WxImgProcAiCropResult aiCrop(String imgUrl, String ratios) throws WxErrorException;

  /**
   * 图片智能裁剪接口
   * 说明：
   * 1.图片支持使用img参数实时上传，也支持使用img_url参数传送图片地址，由微信后台下载图片进行识别
   * 2.文件大小限制：小于2M
   * 3.该接口默认使用最佳宽高比
   * @param imgFile 图片文件对象
   * @return WxMpImgProcAiCropResult
   * @throws WxErrorException .
   */
  WxImgProcAiCropResult aiCrop(File imgFile) throws WxErrorException;

  /**
   * 图片智能裁剪接口
   * 说明：
   * 1.图片支持使用img参数实时上传，也支持使用img_url参数传送图片地址，由微信后台下载图片进行识别
   * 2.文件大小限制：小于2M
   * @param imgFile 图片文件对象
   * @param ratios 宽高比，最多支持5个，请以英文逗号分隔
   * @return WxMpImgProcAiCropResult
   * @throws WxErrorException .
   */
  WxImgProcAiCropResult aiCrop(File imgFile, String ratios) throws WxErrorException;
}
