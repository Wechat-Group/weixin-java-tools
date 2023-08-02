package cn.binarywang.wx.miniapp.api;

import cn.binarywang.wx.miniapp.bean.WxMaDomainAction;
import me.chanjar.weixin.common.error.WxErrorException;

/**
 * 小程序修改服务器地址、成员管理 API（大部分只能是第三方平台调用）
 *
 * @author <a href="https://github.com/charmingoh">Charming</a>
 * @since 2018-04-27 15:46
 */
public interface WxMaSettingService {

  /**
   * 操作服务器域名
   *
   * @param domainAction 域名操作参数
   *                     除了 webViewDomain，都是有效的
   * @return 以下字段仅在 get 时返回完整字段
   * @throws WxErrorException 操作失败时抛出，具体错误码请看文档
   */
  WxMaDomainAction modifyDomain(WxMaDomainAction domainAction) throws WxErrorException;

  /**
   * 设置小程序业务域名（仅供第三方代小程序调用）
   * 授权给第三方的小程序，其业务域名只可以为第三方的服务器，
   * 当小程序通过第三方发布代码上线后，小程序原先自己配置的业务域名将被删除，
   * 只保留第三方平台的域名，所以第三方平台在代替小程序发布代码之前，需要调用接口为小程序添加业务域名。
   * 提示：需要先将域名登记到第三方平台的小程序业务域名中，才可以调用接口进行配置。
   *
   * @param domainAction 域名操作参数
   *                     只有 action 和 webViewDomain 是有效的
   * @return 以下字段仅在 get 时返回完整字段
   * @throws WxErrorException 操作失败时抛出，具体错误码请看文档
   */
  WxMaDomainAction setWebViewDomain(WxMaDomainAction domainAction) throws WxErrorException;

  /**
   * 绑定微信用户为小程序体验者
   *
   * @param wechatId 微信号
   * @throws WxErrorException 失败时抛出，具体错误码请看文档
   */
  void bindTester(String wechatId) throws WxErrorException;

  /**
   * 解除绑定小程序的体验者
   *
   * @param wechatId 微信号
   * @throws WxErrorException 失败时抛出，具体错误码请看文档
   */
  void unbindTester(String wechatId) throws WxErrorException;
}
