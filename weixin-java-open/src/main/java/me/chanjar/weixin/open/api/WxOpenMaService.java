package me.chanjar.weixin.open.api;

import cn.binarywang.wx.miniapp.api.WxMaService;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.open.bean.ma.WxMaOpenCommitExtInfo;
import me.chanjar.weixin.open.bean.message.WxOpenMaSubmitAuditMessage;
import me.chanjar.weixin.open.bean.result.*;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * <pre>
 *     微信开放平台代小程序实现服务能力
 *     https://open.weixin.qq.com/cgi-bin/showdocument?action=dir_list&t=resource/res_list&verify=1&id=open1489144594_DhNoV&token=&lang=zh_CN
 * </pre>
 *
 * @author yqx
 * @date 2018/9/12
 */
public interface WxOpenMaService extends WxMaService {

  /**
   * 设置小程序服务器域名.
   *
   * <pre>
   *     授权给第三方的小程序，其服务器域名只可以为第三方的服务器，当小程序通过第三方发布代码上线后，小程序原先自己配置的服务器域名将被删除，
   *     只保留第三方平台的域名，所以第三方平台在代替小程序发布代码之前，需要调用接口为小程序添加第三方自身的域名。
   *     提示：需要先将域名登记到第三方平台的小程序服务器域名中，才可以调用接口进行配置
   * </pre>
   */
  String API_MODIFY_DOMAIN = "https://api.weixin.qq.com/wxa/modify_domain";

  /**
   * 设置小程序业务域名（仅供第三方代小程序调用）
   * <pre>
   *     授权给第三方的小程序，其业务域名只可以为第三方的服务器，当小程序通过第三方发布代码上线后，小程序原先自己配置的业务域名将被删除，
   *     只保留第三方平台的域名，所以第三方平台在代替小程序发布代码之前，需要调用接口为小程序添加业务域名。
   * 提示：
   * 1、需要先将域名登记到第三方平台的小程序业务域名中，才可以调用接口进行配置。
   * 2、为授权的小程序配置域名时支持配置子域名，例如第三方登记的业务域名如为qq.com，则可以直接将qq.com及其子域名（如xxx.qq.com）也配置到授权的小程序中。
   * </pre>
   */
  String API_SET_WEBVIEW_DOMAIN = "https://api.weixin.qq.com/wxa/setwebviewdomain";

  /**
   * 获取帐号基本信息
   * <pre>
   * GET请求
   * 注意：需要使用1.3环节获取到的新创建小程序appid及authorization_code换取authorizer_refresh_token进而得到authorizer_access_token。
   * </pre>
   */
  String API_GET_ACCOUNT_BASICINFO = "https://api.weixin.qq.com/cgi-bin/account/getaccountbasicinfo";

  /**
   * 绑定微信用户为小程序体验者
   */
  String API_BIND_TESTER = "https://api.weixin.qq.com/wxa/bind_tester";


  /**
   * 解除绑定微信用户为小程序体验者
   */
  String API_UNBIND_TESTER = "https://api.weixin.qq.com/wxa/unbind_tester";


  /**
   * 获取体验者列表
   */
  String API_GET_TESTERLIST = "https://api.weixin.qq.com/wxa/memberauth";

  /**
   * 以下接口基础信息设置
   * <p>
   *     https://open.weixin.qq.com/cgi-bin/showdocument?action=dir_list&t=resource/res_list&verify=1&id=21517799059ZSEMr&token=6f965b5daf30a98a6bbd2a386faea5c934e929bf&lang=zh_CN
   * </p>
   */

  /**
   * 1. 设置小程序隐私设置（是否可被搜索）
   */
  String API_CHANGE_WXA_SEARCH_STATUS = "https://api.weixin.qq.com/wxa/changewxasearchstatus";

  /**
   * 2. 查询小程序当前隐私设置（是否可被搜索）
   */
  String API_GET_WXA_SEARCH_STATUS = "https://api.weixin.qq.com/wxa/getwxasearchstatus";

  /**
   * 3.1. 获取展示的公众号信息
   */
  String API_GET_SHOW_WXA_ITEM = "https://api.weixin.qq.com/wxa/getshowwxaitem";

  /**
   * 3.2 设置展示的公众号
   */
  String API_UPDATE_SHOW_WXA_ITEM = "https://api.weixin.qq.com/wxa/updateshowwxaitem";


  /**
   * 以下接口为三方平台代小程序实现的代码管理功能
   * <p>
   *     https://open.weixin.qq.com/cgi-bin/showdocument?action=dir_list&t=resource/res_list&verify=1&id=open1489140610_Uavc4&token=fe774228c66725425675810097f9e48d0737a4bf&lang=zh_CN
   * </p>
   */

  /**
   * 1. 为授权的小程序帐号上传小程序代码
   */
  String API_CODE_COMMIT = "https://api.weixin.qq.com/wxa/commit";

  /**
   * 2. 获取体验小程序的体验二维码
   */
  String API_TEST_QRCODE = "https://api.weixin.qq.com/wxa/get_qrcode";

  /**
   * 3. 获取授权小程序帐号的可选类目
   */
  String API_GET_CATEGORY = "https://api.weixin.qq.com/wxa/get_category";

  /**
   * 4. 获取小程序的第三方提交代码的页面配置（仅供第三方开发者代小程序调用）
   */
  String API_GET_PAGE = "https://api.weixin.qq.com/wxa/get_page";

  /**
   * 5. 将第三方提交的代码包提交审核（仅供第三方开发者代小程序调用）
   */
  String API_SUBMIT_AUDIT = "https://api.weixin.qq.com/wxa/submit_audit";

  /**
   * 7. 查询某个指定版本的审核状态（仅供第三方代小程序调用）
   */
  String API_GET_AUDIT_STATUS = "https://api.weixin.qq.com/wxa/get_auditstatus";

  /**
   * 8. 查询最新一次提交的审核状态（仅供第三方代小程序调用）
   */
  String API_GET_LATEST_AUDIT_STATUS = "https://api.weixin.qq.com/wxa/get_latest_auditstatus";

  /**
   * 9. 发布已通过审核的小程序（仅供第三方代小程序调用）
   */
  String API_RELEASE = "https://api.weixin.qq.com/wxa/release";

  /**
   * 10. 修改小程序线上代码的可见状态（仅供第三方代小程序调用)
   */
  String API_CHANGE_VISITSTATUS = "https://api.weixin.qq.com/wxa/change_visitstatus";

  /**
   * 11.小程序版本回退（仅供第三方代小程序调用）
   */
  String API_REVERT_CODE_RELEASE = "https://api.weixin.qq.com/wxa/revertcoderelease";

  /**
   * 12.查询当前设置的最低基础库版本及各版本用户占比 （仅供第三方代小程序调用）
   */
  String API_GET_WEAPP_SUPPORT_VERSION = "https://api.weixin.qq.com/cgi-bin/wxopen/getweappsupportversion";

  /**
   * 13.设置最低基础库版本（仅供第三方代小程序调用）
   */
  String API_SET_WEAPP_SUPPORT_VERSION = "https://api.weixin.qq.com/cgi-bin/wxopen/setweappsupportversion";

  /**
   * 14.设置小程序“扫普通链接二维码打开小程序”能力
   * <p>
   *     TODO 暂时不实现
   * </p>
   */

  /**
   * 15.小程序审核撤回
   * <p>
   * 单个帐号每天审核撤回次数最多不超过1次，一个月不超过10次。
   * </p>
   */
  String API_UNDO_CODE_AUDIT = "https://api.weixin.qq.com/wxa/undocodeaudit";

  /**
   * 16.1 小程序分阶段发布-分阶段发布接口
   */
  String API_GRAY_RELEASE = "https://api.weixin.qq.com/wxa/grayrelease";

  /**
   * 16.2 小程序分阶段发布-取消分阶段发布
   */
  String API_REVERT_GRAY_RELEASE = "https://api.weixin.qq.com/wxa/revertgrayrelease";

  /**
   * 16.3 小程序分阶段发布-查询当前分阶段发布详情
   */
  String API_GET_GRAY_RELEASE_PLAN = "https://api.weixin.qq.com/wxa/getgrayreleaseplan";


  /**
   * 查询服务商的当月提审限额和加急次数（Quota）
   */
  String API_QUERY_QUOTA = "https://api.weixin.qq.com/wxa/queryquota";

  /**
   * 加急审核申请
   */
  String API_SPEED_AUDIT = "https://api.weixin.qq.com/wxa/speedupaudit";


  /**
   * 获得小程序的域名配置信息
   */
  WxOpenMaDomainResult getDomain() throws WxErrorException;

  /**
   * 修改域名
   *
   * @param action delete删除, set覆盖, get获取
   */
  WxOpenMaDomainResult modifyDomain(String action, List<String> requestdomainList, List<String> wsrequestdomainList, List<String> uploaddomainList, List<String> downloaddomainList) throws WxErrorException;

  /**
   * 获取小程序的业务域名
   *
   * @return 直接返回字符串
   */
  String getWebViewDomain() throws WxErrorException;

  /**
   * 获取小程序的业务域名
   *
   * @return
   */
  public WxOpenMaWebDomainResult getWebViewDomainInfo() throws WxErrorException;

  /**
   * 设置小程序的业务域名
   *
   * @param action add添加, delete删除, set覆盖
   * @return 直接返回字符串
   */
  String setWebViewDomain(String action, List<String> domainList) throws WxErrorException;


  /**
   * 设置小程序的业务域名
   *
   * @param action     add添加, delete删除, set覆盖
   * @param domainList
   * @return
   */
  WxOpenMaWebDomainResult setWebViewDomainInfo(String action, List<String> domainList) throws WxErrorException;

  /**
   * 获取小程序的信息
   */
  String getAccountBasicInfo() throws WxErrorException;

  /**
   * 绑定小程序体验者
   *
   * @param wechatid 体验者微信号（不是openid）
   * @return
   * @throws WxErrorException
   */
  WxOpenMaBindTesterResult bindTester(String wechatid) throws WxErrorException;

  /**
   * 解除绑定小程序体验者
   *
   * @param wechatid 体验者微信号（不是openid）
   */
  WxOpenResult unbindTester(String wechatid) throws WxErrorException;

  /**
   * 获得体验者列表
   */
  WxOpenMaTesterListResult getTesterList() throws WxErrorException;


  /**
   * 设置小程序隐私设置（是否可被搜索）
   *
   * @param status 1表示不可搜索，0表示可搜索
   */
  public WxOpenResult changeWxaSearchStatus(Integer status) throws WxErrorException;


  /**
   * 2. 查询小程序当前隐私设置（是否可被搜索）
   */
  public WxOpenMaSearchStatusResult getWxaSearchStatus() throws WxErrorException;

  /**
   * 3.1 获取展示的公众号信息
   */
  public WxOpenMaShowItemResult getShowWxaItem() throws WxErrorException;


  /**
   * 3.2 设置展示的公众号
   *
   * @param flag    0 关闭，1 开启
   * @param mpappid 如果开启，需要传新的公众号appid
   */
  public WxOpenResult updateShowwxaitem(Integer flag, String mpappid) throws WxErrorException;

  /**
   * 1、为授权的小程序帐号上传小程序代码
   *
   * @param templateId  代码模板ID
   * @param userVersion 用户定义版本
   * @param userDesc    用户定义版本描述
   * @param extInfo     第三方自定义的配置
   */
  WxOpenResult codeCommit(Long templateId, String userVersion, String userDesc, WxMaOpenCommitExtInfo extInfo) throws WxErrorException;

  /**
   * 获取体验小程序的体验二维码
   */
  File getTestQrcode(String pagePath, Map<String, String> params) throws WxErrorException;

  /**
   * 获取授权小程序帐号的可选类目
   * <p>
   * 注意：该接口可获取已设置的二级类目及用于代码审核的可选三级类目。
   * </p>
   */
  WxOpenMaCategoryListResult getCategoryList() throws WxErrorException;

  /**
   * 获取小程序的第三方提交代码的页面配置（仅供第三方开发者代小程序调用）
   *
   * @return
   * @throws WxErrorException
   */
  WxOpenMaPageListResult getPageList() throws WxErrorException;

  /**
   * 将第三方提交的代码包提交审核（仅供第三方开发者代小程序调用）
   */
  WxOpenMaSubmitAuditResult submitAudit(WxOpenMaSubmitAuditMessage submitAuditMessage) throws WxErrorException;

  /**
   * 查询某个指定版本的审核状态（仅供第三方代小程序调用）
   */
  WxOpenMaQueryAuditResult getAuditStatus(Long auditid) throws WxErrorException;

  /**
   * 查询最新一次提交的审核状态（仅供第三方代小程序调用）.
   */
  WxOpenMaQueryAuditResult getLatestAuditStatus() throws WxErrorException;

  /**
   * 发布已通过审核的小程序（仅供第三方代小程序调用）.
   */
  WxOpenResult releaesAudited() throws WxErrorException;

  /**
   * 10. 修改小程序线上代码的可见状态（仅供第三方代小程序调用）
   */
  public WxOpenResult changeVisitstatus(String action) throws WxErrorException;


  /**
   * 11. 小程序版本回退（仅供第三方代小程序调用）
   */
  WxOpenResult revertCodeReleaes() throws WxErrorException;

  /**
   * 15. 小程序审核撤回
   * 单个帐号每天审核撤回次数最多不超过1次，一个月不超过10次。
   */
  WxOpenResult undoCodeAudit() throws WxErrorException;

  /**
   * 查询当前设置的最低基础库版本及各版本用户占比 （仅供第三方代小程序调用）
   */
  String getSupportVersion() throws WxErrorException;

  /**
   * 查询当前设置的最低基础库版本及各版本用户占比 （仅供第三方代小程序调用）
   */
  WxOpenMaWeappSupportVersionResult getSupportVersionInfo() throws WxErrorException;

  /**
   * 设置最低基础库版本（仅供第三方代小程序调用）
   */
  String setSupportVersion(String version) throws WxErrorException;

  /**
   * 设置最低基础库版本（仅供第三方代小程序调用）
   */
  WxOpenResult setSupportVersionInfo(String version) throws WxErrorException;

  /**
   * 16. 小程序分阶段发布 - 1)分阶段发布接口
   */
  WxOpenResult grayrelease(Integer grayPercentage) throws WxErrorException;

  /**
   * 16. 小程序分阶段发布 - 2)取消分阶段发布
   */
  WxOpenResult revertgrayrelease() throws WxErrorException;

  /**
   * 16. 小程序分阶段发布 - 3)查询当前分阶段发布详情
   */
  WxOpenMaGrayReleasePlanResult getgrayreleaseplan() throws WxErrorException;


  /**
   * 查询服务商的当月提审限额和加急次数（Quota）
   * https://developers.weixin.qq.com/doc/oplatform/Third-party_Platforms/Mini_Programs/code/query_quota.html
   */
  WxOpenMaQueryQuotaResult queryQuota() throws WxErrorException;

  /**
   * 加急审核申请
   * 有加急次数的第三方可以通过该接口，对已经提审的小程序进行加急操作，加急后的小程序预计2-12小时内审完。
   */
  Boolean speedAudit(Long auditid) throws WxErrorException;

}
