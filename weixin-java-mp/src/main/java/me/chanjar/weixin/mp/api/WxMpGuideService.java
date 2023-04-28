package me.chanjar.weixin.mp.api;

import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.bean.guide.*;

import java.util.List;

/**
 * 微信导购助手（现在叫对话能力）接口.
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a> created on  2020 -10-06
 */
public interface WxMpGuideService {

  /**
   * 为服务号添加顾问
   * <pre>
   * 请求地址： POST https://api.weixin.qq.com/cgi-bin/guide/addguideacct?access_token=ACCESS_TOKEN
   * 文档地址：https://developers.weixin.qq.com/doc/offiaccount/Shopping_Guide/guide-account/shopping-guide.addGuideAcct.html
   * </pre>
   *
   * @param account    顾问微信号（guide_account和guide_openid二选一，若同时请求，默认为guide_account）
   * @param openid     顾问openid或者unionid（guide_account和guide_openid二选一）
   * @param headImgUrl 顾问头像，头像url只能用《上传图文消息内的图片获取URL》 me.chanjar.weixin.mp.api.impl.WxMpMaterialServiceImpl#mediaImgUpload(java.io.File)
   * @param nickName   顾问昵称
   * @throws WxErrorException .
   */
  void addGuide(String account, String openid, String headImgUrl, String nickName) throws WxErrorException;

  /**
   * 为服务号添加顾问
   * <pre>
   * 请求地址： POST https://api.weixin.qq.com/cgi-bin/guide/addguideacct?access_token=ACCESS_TOKEN
   * 文档地址：https://developers.weixin.qq.com/doc/offiaccount/Shopping_Guide/guide-account/shopping-guide.addGuideAcct.html
   * </pre>
   *
   * @param guideInfo 顾问信息
   * @throws WxErrorException .
   */
  void addGuide(WxMpGuideInfo guideInfo) throws WxErrorException;

  /**
   * 修改顾问的昵称或头像
   * <pre>
   * 请求地址： POST https://api.weixin.qq.com/cgi-bin/guide/updateguideacct?access_token=ACCESS_TOKEN
   * 文档地址：https://developers.weixin.qq.com/doc/offiaccount/Shopping_Guide/guide-account/shopping-guide.updateGuideAcct.html
   * </pre>
   *
   * @param guideInfo 顾问信息
   * @throws WxErrorException .
   */
  void updateGuide(WxMpGuideInfo guideInfo) throws WxErrorException;

  /**
   * 获取顾问信息
   *
   * <pre>
   * 请求地址：  POST https://api.weixin.qq.com/cgi-bin/guide/getguideacct?access_token=ACCESS_TOKEN
   * 文档地址：https://developers.weixin.qq.com/doc/offiaccount/Shopping_Guide/guide-account/shopping-guide.getGuideAcct.html
   * </pre>
   *
   * @param account 顾问微信号（guide_account和guide_openid二选一，若同时请求，默认为guide_account）
   * @param openid  顾问openid或者unionid（guide_account和guide_openid二选一）
   * @return 顾问信息 guide
   * @throws WxErrorException .
   */
  WxMpGuideInfo getGuide(String account, String openid) throws WxErrorException;

  /**
   * 删除顾问
   *
   * <pre>
   * 请求地址：  POST https://api.weixin.qq.com/cgi-bin/guide/delguideacct?access_token=ACCESS_TOKEN
   * 文档地址：https://developers.weixin.qq.com/doc/offiaccount/Shopping_Guide/guide-account/shopping-guide.delGuideAcct.html
   * </pre>
   *
   * @param account 顾问微信号（guide_account和guide_openid二选一，若同时请求，默认为guide_account）
   * @param openid  顾问openid或者unionid（guide_account和guide_openid二选一）
   * @throws WxErrorException .
   */
  void delGuide(String account, String openid) throws WxErrorException;

  /**
   * 获取服务号顾问列表
   *
   * <pre>
   * 请求地址： POST https://api.weixin.qq.com/cgi-bin/guide/getguideacctlist?access_token=ACCESS_TOKEN
   * 文档地址：https://developers.weixin.qq.com/doc/offiaccount/Shopping_Guide/guide-account/shopping-guide.getGuideAcctList.html
   * </pre>
   *
   * @param page 分页页数，从0开始
   * @param num  每页数量
   * @return 顾问信息列表 wx mp guide list
   * @throws WxErrorException .
   */
  WxMpGuideList listGuide(int page, int num) throws WxErrorException;

  /**
   * 生成顾问二维码
   * <p>
   * 生成顾问二维码后，微信用户扫码直接跳转公众号首页。分为两种情况：
   * 1.微信用户已经关注公众号，扫码后绑定该顾问。
   * 2.微信用户未关注公众号，扫码后 3 分钟内关注该公众号，则绑定该顾问
   *
   * <pre>
   * 请求地址： POST https://api.weixin.qq.com/cgi-bin/guide/guidecreateqrcode?access_token=ACCESS_TOKEN
   * 文档地址：https://developers.weixin.qq.com/doc/offiaccount/Shopping_Guide/guide-account/shopping-guide.guideCreateQrCode.html
   * </pre>
   *
   * @param account    顾问微信号（guide_account和guide_openid二选一，若同时请求，默认为guide_account）
   * @param openid     顾问openid或者unionid（guide_account和guide_openid二选一）
   * @param qrcodeInfo 额外参数，用于事件推送
   * @return 二维码下载链接 string
   * @throws WxErrorException .
   */
  String createGuideQrCode(String account, String openid, String qrcodeInfo) throws WxErrorException;

  /**
   * 获取顾问聊天记录
   * <p>
   * 支持拉取该顾问近 30 天的聊天记录。begin_time 与 end_time 同时非0情况下，该参数才会生效，否则为默认值。
   *
   * <pre>
   * 请求地址： POST https://api.weixin.qq.com/cgi-bin/guide/getguidebuyerchatrecord?access_token=ACCESS_TOKEN
   * 文档地址：https://developers.weixin.qq.com/doc/offiaccount/Shopping_Guide/guide-account/shopping-guide.getGuideBuyerChatRecord.html
   * </pre>
   *
   * @param account      顾问微信号（guide_account和guide_openid二选一，若同时请求，默认为guide_account）
   * @param openid       顾问openid或者unionid（guide_account和guide_openid二选一）
   * @param clientOpenid 客户openid 若不填，则拉取该顾问所有客户的聊天记录。若填写，则拉取顾问与某一个客户的聊天记录
   * @param beginTime    消息的起始UNIX时间戳，如果不填，默认当前时间的前30天(仅支持30天范围内的查询)
   * @param endTime      消息的截止UNIX时间戳，如果不填，默认当前时间。
   * @param page         分页页数，从0开始
   * @param num          每页数量
   * @return 顾问聊天记录列表 guide chat record
   * @throws WxErrorException the wx error exception
   */
  WxMpGuideMsgList getGuideChatRecord(String account, String openid, String clientOpenid, Long beginTime, Long endTime, int page, int num) throws WxErrorException;

  /**
   * 设置快捷回复与关注自动回复
   * <p>
   * 快捷回复：指顾问在对话详情页，可快速选择的回复内容。
   * 注意：1.快捷回复只允许全部删除 2.快捷回复的添加删除需要指定顾问的guide_account和guide_openid二选一
   * <p>
   * 关注自动回复：是指客户通过扫顾问码、扫顾问分组码、微信广告三种方式主动关注公众号并绑定顾问，
   * 顾问会下发的自动回复，即顾问欢迎语。最多可下发两条消息，支持文字、图片或小程序素材，可更新、删除，设置好后先后下发。
   * 不指定 guide_account 和 guide_openid 时，可设置所有顾问默认的关注自动回复。
   * 对单个顾问来说，如果指定 guide_account 和 guide_openid 设置了自动回复，则下发它，否则下发所有顾问默认的关注自动回复
   * 注意：自动回复每次设置会覆盖原有的，自动回复只允许出现两条
   * <p>
   * 特别注意：删除需要传递 guideAutoReply:{"msgtype":"1"}
   * 删除目前只支持两条全部删除 2021/5/8
   * <p>
   * 自动回复格式：
   * String content: 新客户关注自动回复内容,图片填mediaid,获取方式同图片素材,小程序卡片填下面请求demo中字段的json格式
   * int msgtype:    1表示文字，2表示图片，3表示小程序卡片
   * 例如：JsonObject：{"content": "abc","msgtype":"1"}
   *
   * <pre>
   * 请求地址： POST https://api.weixin.qq.com/cgi-bin/guide/setguideconfig?access_token=ACCESS_TOKEN
   * 文档地址：https://developers.weixin.qq.com/doc/offiaccount/Shopping_Guide/guide-account/shopping-guide.setGuideConfig.html
   * </pre>
   *
   * @param account            顾问微信号（guide_account和guide_openid二选一，若同时请求，默认为guide_account）
   * @param openid             顾问openid或者unionid（guide_account和guide_openid二选一）
   * @param isDelete           操作类型，false表示添加 true表示删除
   * @param guideFastReplyList 快捷回复列表
   * @param guideAutoReply     第一条新客户关注自动回复
   * @param guideAutoReplyPlus 第二条新客户关注自动回复
   * @throws WxErrorException .
   */
  void setGuideConfig(String account, String openid, boolean isDelete, List<String> guideFastReplyList, WxMpAddGuideAutoReply guideAutoReply, WxMpAddGuideAutoReply guideAutoReplyPlus) throws WxErrorException;

  /**
   * 获取快捷回复与关注自动回复
   * 如果要获取服务号维度的新客户关注自动回复，不填guide_account与guide_openid即可
   *
   * <pre>
   * 请求地址： POST https://api.weixin.qq.com/cgi-bin/guide/getguideconfig?access_token=ACCESS_TOKEN
   * 文档地址：https://developers.weixin.qq.com/doc/offiaccount/Shopping_Guide/guide-account/shopping-guide.getGuideConfig.html
   * </pre>
   *
   * @param account 顾问微信号（guide_account和guide_openid二选一，若同时请求，默认为guide_account）
   * @param openid  顾问openid或者unionid（guide_account和guide_openid二选一）
   * @return 顾问的 快捷回复，关注顾问自动回复
   * @throws WxErrorException the wx error exception
   */
  WxMpGuideConfig getGuideConfig(String account, String openid) throws WxErrorException;

  /**
   * 为服务号设置敏感词与离线自动回复
   * 顾问在小程序离线状态时，客户发消息会收到设置的离线自动回复，最多支持 300 字。 顾问在小程序内发消息，如果触发敏感词将无法发出。
   * <p>
   * 注意：添加模式 black_keyword字段传递null将删除全部敏感词
   * black_keyword字段有值将对敏感词进行追加而不是全量更新覆盖 （实际测试与官方文档有冲突）
   *
   * <pre>
   * 请求地址： POST https://api.weixin.qq.com/cgi-bin/guide/setguideacctconfig?access_token=ACCESS_TOKEN
   * 文档地址：https://developers.weixin.qq.com/doc/offiaccount/Shopping_Guide/guide-account/shopping-guide.setGuideAcctConfig.html
   * </pre>
   *
   * @param isDelete       操作类型，false表示添加 true表示删除
   * @param blackKeyword   敏感词，每次全量更新覆盖原来数据（如果不设置就不传black_keyword字段）
   * @param guideAutoReply 离线自动回复（如果不设置就不传guide_auto_reply字段）
   * @throws WxErrorException .
   */
  void setGuideAcctConfig(boolean isDelete, List<String> blackKeyword, String guideAutoReply) throws WxErrorException;

  /**
   * 获取离线自动回复与敏感词
   *
   * <pre>
   * 请求地址： POST https://api.weixin.qq.com/cgi-bin/guide/getguideacctconfig?access_token=ACCESS_TOKEN
   * 文档地址：https://developers.weixin.qq.com/doc/offiaccount/Shopping_Guide/guide-account/shopping-guide.getGuideAcctConfig.html
   * </pre>
   *
   * @return 离线自动回复与敏感词 guide acct config
   * @throws WxErrorException .
   */
  WxMpGuideAcctConfig getGuideAcctConfig() throws WxErrorException;

  /**
   * 允许微信用户复制小程序页面路径
   * 请求成功后，该微信号用户可在微信上复制对应小程序的任意页面path，有效期为60天。若需要添加小程序卡片素材时的path，可以用这个方式获取。
   *
   * <pre>
   * 请求地址： POST https://api.weixin.qq.com/cgi-bin/guide/pushshowwxapathmenu?access_token=ACCESS_TOKEN
   * 文档地址：https://developers.weixin.qq.com/doc/offiaccount/Shopping_Guide/guide-account/shopping-guide.pushShowWxaPathMenu.html
   * </pre>
   *
   * @param appId    小程序appid，暂时只支持小程序，不支持小游戏
   * @param userName 关注该公众号的微信号
   * @throws WxErrorException .
   */
  void pushShowWxaPathMenu(String appId, String userName) throws WxErrorException;

  /**
   * 新建顾问分组
   *
   * <pre>
   * 请求地址： POST https://api.weixin.qq.com/cgi-bin/guide/newguidegroup?access_token=ACCESS_TOKEN
   * 文档地址：https://developers.weixin.qq.com/doc/offiaccount/Shopping_Guide/guide-account/shopping-guide.newGuideGroup.html
   * </pre>
   *
   * @param groupName 顾问分组名称
   * @return 顾问分组唯一id long
   * @throws WxErrorException .
   */
  Long newGuideGroup(String groupName) throws WxErrorException;

  /**
   * 获取服务号下所有顾问分组的列表
   *
   * <pre>
   * 请求地址： POST https://api.weixin.qq.com/cgi-bin/guide/getguidegrouplist?access_token=ACCESS_TOKEN
   * 文档地址：https://developers.weixin.qq.com/doc/offiaccount/Shopping_Guide/guide-account/shopping-guide.getGuideGroupList.html
   * </pre>
   *
   * @return 顾问分组列表 guide group list
   * @throws WxErrorException .
   */
  List<WxMpGuideGroup> getGuideGroupList() throws WxErrorException;

  /**
   * 获取指定顾问分组内顾问信息
   *
   * <pre>
   * 请求地址： POST https://api.weixin.qq.com/cgi-bin/guide/getgroupinfo?access_token=ACCESS_TOKEN
   * 文档地址：https://developers.weixin.qq.com/doc/offiaccount/Shopping_Guide/guide-account/shopping-guide.getGroupInfo.html
   * </pre>
   *
   * @param groupId 顾问群组id
   * @param page    分页页数，从0开始，用于组内顾问分页获取
   * @param num     每页数量
   * @return 顾问分组内顾问信息 group info
   * @throws WxErrorException .
   */
  WxMpGuideGroupInfoList getGroupInfo(long groupId, int page, int num) throws WxErrorException;

  /**
   * 分组内添加顾问
   *
   * <pre>
   * 请求地址： POST https://api.weixin.qq.com/cgi-bin/guide/addguide2guidegroup?access_token=ACCESS_TOKEN
   * 文档地址：https://developers.weixin.qq.com/doc/offiaccount/Shopping_Guide/guide-account/shopping-guide.addGuide2GuideGroup.html
   * </pre>
   *
   * @param groupId 顾问分组id
   * @param account 顾问微信号
   * @throws WxErrorException .
   */
  void addGuide2GuideGroup(long groupId, String account) throws WxErrorException;

  /**
   * 分组内删除顾问
   *
   * <pre>
   * 请求地址： POST https://api.weixin.qq.com/cgi-bin/guide/delguide2guidegroup?access_token=ACCESS_TOKEN
   * 文档地址：https://developers.weixin.qq.com/doc/offiaccount/Shopping_Guide/guide-account/shopping-guide.delGuide2GuideGroup.html
   * </pre>
   *
   * @param groupId 顾问分组id
   * @param account 顾问微信号
   * @throws WxErrorException .
   */
  void delGuide2GuideGroup(long groupId, String account) throws WxErrorException;

  /**
   * 获取顾问所在分组
   *
   * <pre>
   * 请求地址： POST https://api.weixin.qq.com/cgi-bin/guide/getgroupbyguide?access_token=ACCESS_TOKEN
   * 文档地址：https://developers.weixin.qq.com/doc/offiaccount/Shopping_Guide/guide-account/shopping-guide.getGroupByGuide.html
   * </pre>
   *
   * @param account 顾问微信号
   * @return 顾问分组id列表 group by guide
   * @throws WxErrorException .
   */
  List<Long> getGroupByGuide(String account) throws WxErrorException;

  /**
   * 删除指定顾问分组
   *
   * <pre>
   * 请求地址： POST https://api.weixin.qq.com/cgi-bin/guide/delguidegroup?access_token=ACCESS_TOKEN
   * 文档地址：https://developers.weixin.qq.com/doc/offiaccount/Shopping_Guide/guide-account/shopping-guide.delGuideGroup.html
   * </pre>
   *
   * @param groupId 顾问分组id
   * @throws WxErrorException .
   */
  void delGuideGroup(long groupId) throws WxErrorException;
}
