package cn.binarywang.wx.miniapp.api;

import cn.binarywang.wx.miniapp.bean.code.*;
import me.chanjar.weixin.common.error.WxErrorException;

import java.util.List;

/**
 * 小程序代码管理相关 API（大部分只能是第三方平台调用）
 * 文档：https://open.weixin.qq.com/cgi-bin/showdocument?action=dir_list&t=resource/res_list&verify=1&id=open1489140610_Uavc4&token=&lang=zh_CN
 *
 * @author <a href="https://github.com/charmingoh">Charming</a>
 * @since 2018-04-26 19:43
 */
public interface WxMaCodeService {
  /**
   * 为授权的小程序帐号上传小程序代码（仅仅支持第三方开放平台）.
   *
   * @param commitRequest 参数
   * @throws WxErrorException 上传失败时抛出，具体错误码请看类注释文档
   */
  void commit(WxMaCodeCommitRequest commitRequest) throws WxErrorException;

  /**
   * 获取体验小程序的体验二维码.
   * 文档地址：
   * https://open.weixin.qq.com/cgi-bin/showdocument?action=dir_list&t=resource/res_list&verify=1&id=open1489140610_Uavc4&token=&lang=zh_CN
   *
   * @param path 指定体验版二维码跳转到某个具体页面（如果不需要的话，则不需要填path参数，可在路径后以“?参数”方式传入参数）
   *             具体的路径加参数需要urlencode（方法内部处理），比如page/index?action=1编码后得到page%2Findex%3Faction%3D1
   * @return 二维码 bytes
   * @throws WxErrorException 上传失败时抛出，具体错误码请看类注释文档
   */
  byte[] getQrCode(String path) throws WxErrorException;

  /**
   * 获取授权小程序帐号的可选类目.
   *
   * @return List<WxMaCategory>
   * @throws WxErrorException 获取失败时返回，具体错误码请看此接口的注释文档
   */
  List<WxMaCodeSubmitAuditItem> getCategory() throws WxErrorException;

  /**
   * 获取小程序的第三方提交代码的页面配置（仅供第三方开发者代小程序调用）.
   *
   * @return page_list 页面配置列表
   * @throws WxErrorException 获取失败时返回，具体错误码请看此接口的注释文档
   */
  List<String> getPage() throws WxErrorException;

  /**
   * 将第三方提交的代码包提交审核（仅供第三方开发者代小程序调用）.
   *
   * @param auditRequest 提交审核参数
   * @return 审核编号
   * @throws WxErrorException 提交失败时返回，具体错误码请看此接口的注释文档
   */
  long submitAudit(WxMaCodeSubmitAuditRequest auditRequest) throws WxErrorException;

  /**
   * 查询某个指定版本的审核状态（仅供第三方代小程序调用）.
   *
   * @param auditId 提交审核时获得的审核id
   * @return 审核状态
   * @throws WxErrorException 查询失败时返回，具体错误码请看此接口的注释文档
   */
  WxMaCodeAuditStatus getAuditStatus(long auditId) throws WxErrorException;

  /**
   * 查询最新一次提交的审核状态（仅供第三方代小程序调用）.
   * 文档：<a href="https://developers.weixin.qq.com/doc/oplatform/openApi/OpenApiDoc/miniprogram-management/code-management/getLatestAuditStatus.html">文档地址</a>
   * @return 审核状态
   * @throws WxErrorException 查询失败时返回，具体错误码请看此接口的注释文档
   */
  WxMaCodeAuditStatus getLatestAuditStatus() throws WxErrorException;

  /**
   * 发布已通过审核的小程序（仅供第三方代小程序调用）.
   *
   * @throws WxErrorException 发布失败时抛出，具体错误码请看此接口的注释文档
   */
  void release() throws WxErrorException;

  /**
   * 修改小程序线上代码的可见状态（仅供第三方代小程序调用）.
   *
   * @param action 设置可访问状态，发布后默认可访问，close为不可见，open为可见
   * @throws WxErrorException 发布失败时抛出，具体错误码请看此接口的注释文档
   */
  void changeVisitStatus(String action) throws WxErrorException;

  /**
   * 小程序版本回退（仅供第三方代小程序调用）.
   *
   * @throws WxErrorException 失败时抛出，具体错误码请看此接口的注释文档
   */
  void revertCodeRelease() throws WxErrorException;

  /**
   * 查询当前设置的最低基础库版本及各版本用户占比 （仅供第三方代小程序调用）.
   *
   * @return 小程序版本分布信息
   * @throws WxErrorException 失败时抛出，具体错误码请看此接口的注释文档
   */
  WxMaCodeVersionDistribution getSupportVersion() throws WxErrorException;

  /**
   * 查询小程序版本信息
   *
   * @return 小程序的体验版和线上版本信息
   * @throws WxErrorException 失败时抛出，具体错误码请看此接口的注释文档
   */
  WxMaCodeVersionInfo getVersionInfo() throws WxErrorException;

  /**
   * 设置最低基础库版本（仅供第三方代小程序调用）.
   *
   * @param version 版本
   * @throws WxErrorException 失败时抛出，具体错误码请看此接口的注释文档
   */
  void setSupportVersion(String version) throws WxErrorException;

  /**
   * 小程序审核撤回.
   * 单个帐号每天审核撤回次数最多不超过1次，一个月不超过10次
   *
   * @throws WxErrorException 失败时抛出，具体错误码请看此接口的注释文档
   */
  void undoCodeAudit() throws WxErrorException;
}
