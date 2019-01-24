package me.chanjar.weixin.open.api;

import cn.binarywang.wx.miniapp.api.WxMaService;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.open.bean.fastma.WxAccountBasicInfo;

/**
 * <pre>
 *     微信开放平台【快速创建小程序】的专用接口
 *     https://open.weixin.qq.com/cgi-bin/showdocument?action=dir_list&t=resource/res_list&verify=1&id=21528465979XX32V&token=&lang=zh_CN
 *    注意：该类的接口仅限通过快速创建小程序接口的小程序使用
 * </pre>
 *
 * @author Hipple
 * @date 2019/01/23
 */
public interface WxOpenFastMaService extends WxMaService {

  /**
   * 1 获取帐号基本信息
   */
  String OPEN_GET_ACCOUNT_BASIC_INFO = "https://api.weixin.qq.com/cgi-bin/account/getaccountbasicinfo";

  /**
   * 2 小程序名称设置及改名
   */
  String OPEN_SET_NICKNAME = "https://api.weixin.qq.com/wxa/setnickname";

  /**
   * 3 小程序改名审核状态查询
   */
  String OPEN_API_WXA_QUERYNICKNAME = "https://api.weixin.qq.com/wxa/api_wxa_querynickname";

  /**
   * 4 微信认证名称检测
   */
  String OPEN_CHECK_WX_VERIFY_NICKNAME = "https://api.weixin.qq.com/cgi-bin/wxverify/checkwxverifynickname";

  /**
   * 5 修改头像
   */
  String OPEN_MODIFY_HEADIMAGE = "https://api.weixin.qq.com/cgi-bin/account/modifyheadimage";

  /**
   * 6修改功能介绍
   */
  String OPEN_MODIFY_SIGNATURE = "https://api.weixin.qq.com/cgi-bin/account/modifysignature";

  /**
   * 7 换绑小程序管理员接口
   * <pre>
   *     TODO 暂不实现
   * </pre>
   */
  /**
   * 8.1 获取账号可以设置的所有类目
   */
  String OPEN_GET_ALL_CATEGORIES = "https://api.weixin.qq.com/cgi-bin/wxopen/getallcategories";
  /**
   * 8.2 添加类目
   */
  String OPEN_ADD_CATEGORY = "https://api.weixin.qq.com/cgi-bin/wxopen/addcategory";
  /**
   * 8.3 删除类目
   */
  String OPEN_DELETE_CATEGORY = "https://api.weixin.qq.com/cgi-bin/wxopen/deletecategory";
  /**
   * 8.4 获取账号已经设置的所有类目
   */
  String OPEN_GET_CATEGORY = "https://api.weixin.qq.com/cgi-bin/wxopen/getcategory";
  /**
   * 8.5 修改类目
   */
  String OPEN_MODIFY_CATEGORY = "https://api.weixin.qq.com/cgi-bin/wxopen/modifycategory";


  /**
   * 1.获取小程序的信息
   *
   * @return
   * @throws WxErrorException
   */
  WxAccountBasicInfo getAccountBasicInfo() throws WxErrorException;

  /**
   * 2.小程序名称设置及改名
   * @param nickname 昵称
   * @param idCard 身份证照片–临时素材mediaid(个人号必填)
   * @param license 组织机构代码证或营业执照–临时素材mediaid(组织号必填)
   * @param namingOtherStuff1 其他证明材料---临时素材 mediaid
   * @param namingOtherStuff2 其他证明材料---临时素材 mediaid
   * @throws WxErrorException
   */
  void setNickname(String nickname, String idCard, String license, String namingOtherStuff1, String namingOtherStuff2) throws WxErrorException;

  /**
   * 4. 微信认证名称检测
   * @param nickname 名称
   * @throws WxErrorException
   */
  String checkWxVerifyNickname(String nickname) throws WxErrorException;

  /**
   * 5.修改头像
   * <pre>
   *     图片格式只支持：BMP、JPEG、JPG、GIF、PNG，大小不超过2M
   *      注：实际头像始终为正方形
   * </pre>
   * @param headImgMediaId 头像素材media_id
   * @param x1 裁剪框左上角x坐标（取值范围：[0, 1]）
   * @param y1 裁剪框左上角y坐标（取值范围：[0, 1]）
   * @param x2 裁剪框右下角x坐标（取值范围：[0, 1]）
   * @param y2 裁剪框右下角y坐标（取值范围：[0, 1]）
   * @throws WxErrorException
   */
  void modifyHeadImage(String headImgMediaId, float x1, float y1, float x2, float y2) throws WxErrorException;

  /**
   * 6.修改功能介绍
   * @param signature 简介：4-120字
   * @throws WxErrorException
   */
  void modifySignature(String signature) throws WxErrorException;
}
