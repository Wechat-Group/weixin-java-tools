package me.chanjar.weixin.open.api;

import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.open.bean.ma.privacy.*;

/**
 * 微信第三方平台 小程序用户隐私保护指引接口 / 申请隐私接口
 * (从2022年4月18日开始，部分小程序前端 api 需申请后，方可使用。该接口用于获取“需申请并审核通过”后才可使用的接口列表。)
 * <a href="https://developers.weixin.qq.com/doc/oplatform/Third-party_Platforms/2.0/api/privacy_config/set_privacy_setting.html">配置小程序用户隐私保护指引</a>、<a href="https://developers.weixin.qq.com/doc/oplatform/Third-party_Platforms/2.0/api/apply_api/get_privacy_interface.html">获取接口列表</a>
 *
 * @author <a href="https://www.sacoc.cn">广州跨界</a>
 */
public interface WxOpenMaPrivacyService {

  /**
   * 1 设置小程序用户隐私保护指引
   */
  String OPEN_SET_PRIVACY_SETTING = "https://api.weixin.qq.com/cgi-bin/component/setprivacysetting";

  /**
   * 2 查询小程序用户隐私保护指引
   */
  String OPEN_GET_PRIVACY_SETTING = "https://api.weixin.qq.com/cgi-bin/component/getprivacysetting";

  /**
   * 3 上传小程序用户隐私保护指引文件
   */
  String OPEN_UPLOAD_PRIVACY_FILE = "https://api.weixin.qq.com/cgi-bin/component/uploadprivacyextfile";

  /**
   * 4 获取接口列表 从2022年4月18日开始，部分小程序前端 api 需申请后
   */
  String GET_PRIVATE_INTERFACE = "https://api.weixin.qq.com/wxa/security/get_privacy_interface";

  /**
   * 5 申请接口 从2022年4月18日开始，部分小程序前端 api 需申请后
   */
  String APPLY_PRIVATE_INTERFACE = "https://api.weixin.qq.com/wxa/security/apply_privacy_interface";

  /**
   * <a href="https://developers.weixin.qq.com/doc/oplatform/Third-party_Platforms/2.0/api/privacy_config/get_privacy_setting.html">查询小程序用户隐私保护指引</a>
   *
   * @param privacyVer 1表示现网版本，即，传1则该接口返回的内容是现网版本的；2表示开发版，即，传2则该接口返回的内容是开发版本的。默认是2。
   * @return 查询结果
   * @throws WxErrorException 如果出错,抛出此异常
   */
  GetPrivacySettingResult getPrivacySetting(Integer privacyVer) throws WxErrorException;

  /**
   * <a href="https://developers.weixin.qq.com/doc/oplatform/Third-party_Platforms/2.0/api/privacy_config/set_privacy_setting.html">设置小程序用户隐私保护指引</a>
   *
   * @param dto 参数对象
   * @throws WxErrorException 如果出错,抛出此异常
   */
  void setPrivacySetting(SetPrivacySetting dto) throws WxErrorException;

  /**
   * 上传小程序用户隐私保护指引文件
   * 本接口用于上传自定义的小程序的用户隐私保护指引
   * 仅限文本文件, 限制文件大小为不超过100kb，否则会报错
   * <a href="https://developers.weixin.qq.com/doc/oplatform/Third-party_Platforms/2.0/api/privacy_config/upload_privacy_exfile.html">上传小程序用户隐私保护指引文件</a>
   *
   * @param content 文本文件内容
   * @return 上传结果
   * @throws WxErrorException 如果出错,抛出此异常
   */
  UploadPrivacyFileResult uploadPrivacyFile(String content) throws WxErrorException;

  /**
   * 隐私接口-获取接口列表
   * 从2022年4月18日开始，部分小程序前端 api 需申请后，方可使用。该接口用于获取“需申请并审核通过”后才可使用的接口列表。
   * <a href="https://developers.weixin.qq.com/doc/oplatform/Third-party_Platforms/2.0/api/apply_api/get_privacy_interface.html">隐私接口-获取接口列表</a>
   *
   * @return 获取结果
   * @throws WxErrorException 如果出错,抛出此异常
   */
  GetPrivacyInterfaceResult getPrivacyInterface() throws WxErrorException;

  /**
   * 隐私接口-申请接口
   * 从2022年4月18日开始，部分小程序前端 api 需申请后，方可使用。该接口用于获取“需申请并审核通过”后才可使用的接口列表。
   * <a href="https://developers.weixin.qq.com/doc/oplatform/Third-party_Platforms/2.0/api/apply_api/get_privacy_interface.html">隐私接口-申请接口</a>
   *
   * @param dto 请求参数
   * @return 获取结果
   * @throws WxErrorException 如果出错,抛出此异常
   */
  ApplyPrivacyInterfaceResult applyPrivacyInterface(ApplyPrivacyInterface dto) throws WxErrorException;
}
