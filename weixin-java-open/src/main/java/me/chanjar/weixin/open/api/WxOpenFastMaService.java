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
   * 17 获取帐号基本信息
   * <pre>
   *
   * </pre>
   */
  String OPEN_GET_ACCOUNT_BASIC_INFO = "https://api.weixin.qq.com/cgi-bin/account/getaccountbasicinfo";

  /**
   * 18 小程序名称设置及改名
   */
  String OPEN_SET_NICKNAME = "https://api.weixin.qq.com/wxa/setnickname";

  /**
   * 19 小程序改名审核状态查询
   */
  String OPEN_API_WXA_QUERYNICKNAME = "https://api.weixin.qq.com/wxa/api_wxa_querynickname";

  /**
   * 20 微信认证名称检测
   */
  String OPEN_CHECK_WX_VERIFY_NICKNAME = "https://api.weixin.qq.com/cgi-bin/wxverify/checkwxverifynickname";

  /**
   * 21 修改头像
   */
  String OPEN_MODIFY_HEADIMAGE = "https://api.weixin.qq.com/cgi-bin/account/modifyheadimage";

  /**
   * 22 修改功能介绍
   */
  String OPEN_MODIFY_SIGNATURE = "https://api.weixin.qq.com/cgi-bin/account/modifysignature";

  /**
   * 23 换绑小程序管理员接口
   * <pre>
   *     TODO 暂不实现
   * </pre>
   */
  /**
   * 24.1 获取账号可以设置的所有类目
   */
  String OPEN_GET_ALL_CATEGORIES = "https://api.weixin.qq.com/cgi-bin/wxopen/getallcategories";
  /**
   * 24.2 添加类目
   */
  String OPEN_ADD_CATEGORY = "https://api.weixin.qq.com/cgi-bin/wxopen/addcategory";
  /**
   * 24.3 删除类目
   */
  String OPEN_DELETE_CATEGORY = "https://api.weixin.qq.com/cgi-bin/wxopen/deletecategory";
  /**
   * 24.4 获取账号已经设置的所有类目
   */
  String OPEN_GET_CATEGORY = "https://api.weixin.qq.com/cgi-bin/wxopen/getcategory";
  /**
   * 24.5 修改类目
   */
  String OPEN_MODIFY_CATEGORY = "https://api.weixin.qq.com/cgi-bin/wxopen/modifycategory";


  /**
   * 获取小程序的信息
   *
   * @return
   * @throws WxErrorException
   */
  WxAccountBasicInfo getAccountBasicInfo() throws WxErrorException;
}
