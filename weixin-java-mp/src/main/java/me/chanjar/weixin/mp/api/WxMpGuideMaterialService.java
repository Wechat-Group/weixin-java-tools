package me.chanjar.weixin.mp.api;

import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.bean.guide.WxMpGuideCardMaterialInfo;
import me.chanjar.weixin.mp.bean.guide.WxMpGuideImgMaterialInfoList;
import me.chanjar.weixin.mp.bean.guide.WxMpGuideWordMaterialInfoList;

import java.util.List;

/**
 * @author <a href="https://www.sacoc.cn">广州跨界-宋心成</a>
 * @date 2021/5/13/013
 */
public interface WxMpGuideMaterialService {

  /**
   * 添加小程序卡片素材
   * <p>
   * 踩坑记录（2021/5/12）：该方法只支持临时素材mediaid
   *
   * <pre>
   * 请求地址： POST https://api.weixin.qq.com/cgi-bin/guide/setguidecardmaterial?access_token=ACCESS_TOKEN
   * 文档地址：https://developers.weixin.qq.com/doc/offiaccount/Shopping_Guide/model-account/shopping-guide.setGuideCardMaterial.html
   * </pre>
   *
   * @param mediaId 图片素材，只能用《素材管理获取media_id》（注意：只支持临时素材的media_id）
   * @param type    操作类型，填0，表示服务号素材
   * @param title   小程序卡片名字
   * @param path    小程序路径
   * @param appId   小程序的appid
   * @throws WxErrorException .
   */
  void setGuideCardMaterial(String mediaId, int type, String title, String path, String appId) throws WxErrorException;

  /**
   * 查询小程序卡片素材
   *
   * <pre>
   * 请求地址： POST https://api.weixin.qq.com/cgi-bin/guide/getguidecardmaterial?access_token=ACCESS_TOKEN
   * 文档地址：https://developers.weixin.qq.com/doc/offiaccount/Shopping_Guide/model-account/shopping-guide.getGuideCardMaterial.html
   * </pre>
   *
   * @param type 操作类型，填0，表示服务号素材
   * @return 小程序卡片素材信息列表
   * @throws WxErrorException .
   */
  List<WxMpGuideCardMaterialInfo> getGuideCardMaterial(int type) throws WxErrorException;

  /**
   * 删除小程序卡片素材
   *
   * <pre>
   * 请求地址： POST https://api.weixin.qq.com/cgi-bin/guide/delguidecardmaterial?access_token=ACCESS_TOKEN
   * 文档地址：https://developers.weixin.qq.com/doc/offiaccount/Shopping_Guide/model-account/shopping-guide.delGuideCardMaterial.html
   * </pre>
   *
   * @param type  操作类型，填0，表示服务号素材
   * @param title 小程序卡片名字
   * @param path  小程序路径
   * @param appId 小程序的appid
   * @throws WxErrorException .
   */
  void delGuideCardMaterial(int type, String title, String path, String appId) throws WxErrorException;

  /**
   * 添加图片素材
   *
   * <pre>
   * 请求地址： POST https://api.weixin.qq.com/cgi-bin/guide/setguideimagematerial?access_token=ACCESS_TOKEN
   * 文档地址：https://developers.weixin.qq.com/doc/offiaccount/Shopping_Guide/model-account/shopping-guide.setGuideImageMaterial.html
   * </pre>
   *
   * @param mediaId 图片素材，只能用《素材管理获取media_id》（注意：只支持临时素材的media_id）
   * @param type    操作类型，填0，表示服务号素材
   * @throws WxErrorException .
   */
  void setGuideImageMaterial(String mediaId, int type) throws WxErrorException;

  /**
   * 查询图片素材
   *
   * <pre>
   * 请求地址： POST https://api.weixin.qq.com/cgi-bin/guide/getguideimagematerial?access_token=ACCESS_TOKEN
   * 文档地址：https://developers.weixin.qq.com/doc/offiaccount/Shopping_Guide/model-account/shopping-guide.getGuideImageMaterial.html
   * </pre>
   *
   * @param type  操作类型，填0，表示服务号素材
   * @param start 分页查询，起始位置
   * @param num   分页查询，查询个数
   * @return 图片素材列表
   * @throws WxErrorException .
   */
  WxMpGuideImgMaterialInfoList getGuideImageMaterial(int type, int start, int num) throws WxErrorException;

  /**
   * 删除图片素材
   *
   * <pre>
   * 请求地址： POST https://api.weixin.qq.com/cgi-bin/guide/delguideimagematerial?access_token=ACCESS_TOKEN
   * 文档地址：https://developers.weixin.qq.com/doc/offiaccount/Shopping_Guide/model-account/shopping-guide.delGuideImageMaterial.html
   * </pre>
   *
   * @param type   操作类型，填0，表示服务号素材
   * @param picUrl 图片素材内容
   * @throws WxErrorException .
   */
  void delGuideImageMaterial(int type, String picUrl) throws WxErrorException;

  /**
   * 添加文字素材
   *
   * <pre>
   * 请求地址： POST https://api.weixin.qq.com/cgi-bin/guide/setguidewordmaterial?access_token=ACCESS_TOKEN
   * 文档地址：https://developers.weixin.qq.com/doc/offiaccount/Shopping_Guide/model-account/shopping-guide.setGuideWordMaterial.html
   * </pre>
   *
   * @param type 操作类型，填0，表示服务号素材
   * @param word 文字素材内容
   * @throws WxErrorException .
   */
  void setGuideWordMaterial(int type, String word) throws WxErrorException;

  /**
   * 查询文字素材
   *
   * <pre>
   * 请求地址： POST https://api.weixin.qq.com/cgi-bin/guide/getguidewordmaterial?access_token=ACCESS_TOKEN
   * 文档地址：https://developers.weixin.qq.com/doc/offiaccount/Shopping_Guide/model-account/shopping-guide.getGuideWordMaterial.html
   * </pre>
   *
   * @param type  操作类型，填0，表示服务号素材
   * @param start 分页查询，起始位置
   * @param num   分页查询，查询个数
   * @return 文字素材列表
   * @throws WxErrorException 。
   */
  WxMpGuideWordMaterialInfoList getGuideWordMaterial(int type, int start, int num) throws WxErrorException;

  /**
   * 删除文字素材
   *
   * <pre>
   * 请求地址： POST https://api.weixin.qq.com/cgi-bin/guide/delguidewordmaterial?access_token=ACCESS_TOKEN
   * 文档地址：https://developers.weixin.qq.com/doc/offiaccount/Shopping_Guide/model-account/shopping-guide.delGuideWordMaterial.html
   * </pre>
   *
   * @param type 操作类型，填0，表示服务号素材
   * @param word 文字素材内容
   * @throws WxErrorException .
   */
  void delGuideWordMaterial(int type, String word) throws WxErrorException;

}
