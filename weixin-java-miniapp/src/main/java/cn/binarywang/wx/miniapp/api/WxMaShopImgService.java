package cn.binarywang.wx.miniapp.api;

import me.chanjar.weixin.common.bean.result.WxMinishopImageUploadCustomizeResult;
import me.chanjar.weixin.common.error.WxErrorException;

import java.io.File;

/**
 * 小程序交易组件-接入商品前必需接口
 *
 * @author liming1019
 */
public interface WxMaShopImgService {
  /**
   * 上传图片
   *
   * @param file
   * @return WxMinishopImageUploadCustomizeResult
   * @throws WxErrorException
   */
  WxMinishopImageUploadCustomizeResult uploadImg(File file) throws WxErrorException;

  /**
   * 上传图片,带respType参数
   *
   * @param file
   * @param respType
   * @return WxMinishopImageUploadCustomizeResult
   * @throws WxErrorException
   */
  WxMinishopImageUploadCustomizeResult uploadImg(File file, String respType) throws WxErrorException;
}
