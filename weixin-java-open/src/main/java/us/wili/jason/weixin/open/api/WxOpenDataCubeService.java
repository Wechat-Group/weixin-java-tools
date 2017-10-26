package us.wili.jason.weixin.open.api;

import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.bean.datacube.*;

import java.util.Date;
import java.util.List;

/**
 * 统计分析相关接口
 * Created by Binary Wang on 2016/8/23.
 *
 * @author binarywang (https://github.com/binarywang)
 */
public interface WxOpenDataCubeService {
  String GET_USER_SUMMARY = "https://api.weixin.qq.com/datacube/getusersummary";
  String GET_USER_CUMULATE = "https://api.weixin.qq.com/datacube/getusercumulate";
  String GET_ARTICLE_SUMMARY = "https://api.weixin.qq.com/datacube/getarticlesummary";
  String GET_ARTICLE_TOTAL = "https://api.weixin.qq.com/datacube/getarticletotal";
  String GET_USER_READ = "https://api.weixin.qq.com/datacube/getuserread";
  String GET_USER_READ_HOUR = "https://api.weixin.qq.com/datacube/getuserreadhour";
  String GET_USER_SHARE = "https://api.weixin.qq.com/datacube/getusershare";
  String GET_USER_SHARE_HOUR = "https://api.weixin.qq.com/datacube/getusersharehour";
  String GET_UPSTREAM_MSG = "https://api.weixin.qq.com/datacube/getupstreammsg";
  String GET_UPSTREAM_MSG_HOUR = "https://api.weixin.qq.com/datacube/getupstreammsghour";
  String GET_UPSTREAM_MSG_WEEK = "https://api.weixin.qq.com/datacube/getupstreammsgweek";
  String GET_UPSTREAM_MSG_MONTH = "https://api.weixin.qq.com/datacube/getupstreammsgmonth";
  String GET_UPSTREAM_MSG_DIST = "https://api.weixin.qq.com/datacube/getupstreammsgdist";
  String GET_UPSTREAM_MSG_DIST_WEEK = "https://api.weixin.qq.com/datacube/getupstreammsgdistweek";
  String GET_UPSTREAM_MSG_DIST_MONTH = "https://api.weixin.qq.com/datacube/getupstreammsgdistmonth";
  String GET_INTERFACE_SUMMARY = "https://api.weixin.qq.com/datacube/getinterfacesummary";
  String GET_INTERFACE_SUMMARY_HOUR = "https://api.weixin.qq.com/datacube/getinterfacesummaryhour";

  //*******************用户分析数据接口***********************//

  /**
   * <pre>
   * 获取用户增减数据
   * 详情请见文档：<a href="http://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421141082&token=&lang=zh_CN">用户分析数据接口</a>
   * 接口url格式：https://api.weixin.qq.com/datacube/getusersummary?access_token=ACCESS_TOKEN
   * </pre>
   *
   * @param beginDate 开始时间
   * @param endDate   最大时间跨度7天，endDate不能早于begingDate
   */
  List<WxDataCubeUserSummary> getUserSummary(String appid, Date beginDate, Date endDate) throws WxErrorException;

  /**
   * <pre>
   * 获取累计用户数据
   * 详情请见文档：<a href="http://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421141082&token=&lang=zh_CN">用户分析数据接口</a>
   * 接口url格式：https://api.weixin.qq.com/datacube/getusercumulate?access_token=ACCESS_TOKEN
   * </pre>
   *
   * @param beginDate 开始时间
   * @param endDate   最大时间跨度7天，endDate不能早于begingDate
   */
  List<WxDataCubeUserCumulate> getUserCumulate(String appid, Date beginDate, Date endDate) throws WxErrorException;

  //*******************图文分析数据接口***********************//

  /**
   * <pre>
   * 获取图文群发每日数据（getarticlesummary）
   * 详情请见文档：<a href="http://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421141084&token=&lang=zh_CN">图文分析数据接口</a>
   * 接口url格式：https://api.weixin.qq.com/datacube/getarticlesummary?access_token=ACCESS_TOKEN
   * </pre>
   *
   * @param beginDate 开始时间
   * @param endDate   最大时间跨度1天，endDate不能早于begingDate
   */
  List<WxDataCubeArticleResult> getArticleSummary(String appid, Date beginDate, Date endDate) throws WxErrorException;

  /**
   * <pre>
   * 获取图文群发总数据（getarticletotal）
   * 详情请见文档：<a href="http://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421141084&token=&lang=zh_CN">图文分析数据接口</a>
   * 接口url格式：https://api.weixin.qq.com/datacube/getarticletotal?access_token=ACCESS_TOKEN
   *
   * @param beginDate 开始时间
   * @param endDate   最大时间跨度1天，endDate不能早于begingDate
   */
  List<WxDataCubeArticleTotal> getArticleTotal(String appid, Date beginDate, Date endDate) throws WxErrorException;

  /**
   * <pre>
   * 获取图文统计数据（getuserread）
   * 详情请见文档：<a href="http://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421141084&token=&lang=zh_CN">图文分析数据接口</a>
   * 接口url格式：https://api.weixin.qq.com/datacube/getuserread?access_token=ACCESS_TOKEN
   *
   * @param beginDate 开始时间
   * @param endDate   最大时间跨度3天，endDate不能早于begingDate
   */
  List<WxDataCubeArticleResult> getUserRead(String appid, Date beginDate, Date endDate) throws WxErrorException;

  /**
   * <pre>
   * 获取图文统计分时数据（getuserreadhour）
   * 详情请见文档：<a href="http://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421141084&token=&lang=zh_CN">图文分析数据接口</a>
   * 接口url格式：https://api.weixin.qq.com/datacube/getuserreadhour?access_token=ACCESS_TOKEN
   *
   * @param beginDate 开始时间
   * @param endDate   最大时间跨度1天，endDate不能早于begingDate
   */
  List<WxDataCubeArticleResult> getUserReadHour(String appid, Date beginDate, Date endDate) throws WxErrorException;

  /**
   * <pre>
   * 获取图文分享转发数据（getusershare）
   * 详情请见文档：<a href="http://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421141084&token=&lang=zh_CN">图文分析数据接口</a>
   * 接口url格式：https://api.weixin.qq.com/datacube/getusershare?access_token=ACCESS_TOKEN
   *
   * @param beginDate 开始时间
   * @param endDate   最大时间跨度7天，endDate不能早于begingDate
   */
  List<WxDataCubeArticleResult> getUserShare(String appid, Date beginDate, Date endDate) throws WxErrorException;

  /**
   * <pre>
   * 获取图文分享转发分时数据（getusersharehour）
   * 详情请见文档：<a href="http://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421141084&token=&lang=zh_CN">图文分析数据接口</a>
   * 接口url格式：https://api.weixin.qq.com/datacube/getusersharehour?access_token=ACCESS_TOKEN
   *
   * @param beginDate 开始时间
   * @param endDate   最大时间跨度1天，endDate不能早于begingDate
   */
  List<WxDataCubeArticleResult> getUserShareHour(String appid, Date beginDate, Date endDate) throws WxErrorException;

  //*******************消息分析数据接口***********************//

  /**
   * <pre>
   * 获取消息发送概况数据（getupstreammsg）
   * 详情请见文档：<a href="http://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421141085&token=&lang=zh_CN">消息分析数据接口</a>
   * 接口url格式：https://api.weixin.qq.com/datacube/getupstreammsg?access_token=ACCESS_TOKEN
   *
   * @param beginDate 开始时间
   * @param endDate   最大时间跨度7天，endDate不能早于begingDate
   */
  List<WxDataCubeMsgResult> getUpstreamMsg(String appid, Date beginDate, Date endDate) throws WxErrorException;

  /**
   * <pre>
   * 获取消息分送分时数据（getupstreammsghour）
   * 详情请见文档：<a href="http://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421141085&token=&lang=zh_CN">消息分析数据接口</a>
   * 接口url格式：https://api.weixin.qq.com/datacube/getupstreammsghour?access_token=ACCESS_TOKEN
   *
   * @param beginDate 开始时间
   * @param endDate   最大时间跨度1天，endDate不能早于begingDate
   */
  List<WxDataCubeMsgResult> getUpstreamMsgHour(String appid, Date beginDate, Date endDate) throws WxErrorException;

  /**
   * <pre>
   * 获取消息发送周数据（getupstreammsgweek）
   * 详情请见文档：<a href="http://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421141085&token=&lang=zh_CN">消息分析数据接口</a>
   * 接口url格式：https://api.weixin.qq.com/datacube/getupstreammsgweek?access_token=ACCESS_TOKEN
   *
   * @param beginDate 开始时间
   * @param endDate   最大时间跨度30天，endDate不能早于begingDate
   */
  List<WxDataCubeMsgResult> getUpstreamMsgWeek(String appid, Date beginDate, Date endDate) throws WxErrorException;

  /**
   * <pre>
   * 获取消息发送月数据（getupstreammsgmonth）
   * 详情请见文档：<a href="http://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421141085&token=&lang=zh_CN">消息分析数据接口</a>
   * 接口url格式：https://api.weixin.qq.com/datacube/getupstreammsgmonth?access_token=ACCESS_TOKEN
   *
   * @param beginDate 开始时间
   * @param endDate   最大时间跨度30天，endDate不能早于begingDate
   */
  List<WxDataCubeMsgResult> getUpstreamMsgMonth(String appid, Date beginDate, Date endDate) throws WxErrorException;

  /**
   * <pre>
   * 获取消息发送分布数据（getupstreammsgdist）
   * 详情请见文档：<a href="http://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421141085&token=&lang=zh_CN">消息分析数据接口</a>
   * 接口url格式：https://api.weixin.qq.com/datacube/getupstreammsgdist?access_token=ACCESS_TOKEN
   *
   * @param beginDate 开始时间
   * @param endDate   最大时间跨度15天，endDate不能早于begingDate
   */
  List<WxDataCubeMsgResult> getUpstreamMsgDist(String appid, Date beginDate, Date endDate) throws WxErrorException;

  /**
   * <pre>
   * 获取消息发送分布周数据（getupstreammsgdistweek）
   * 详情请见文档：<a href="http://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421141085&token=&lang=zh_CN">消息分析数据接口</a>
   * 接口url格式：https://api.weixin.qq.com/datacube/getupstreammsgdistweek?access_token=ACCESS_TOKEN
   *
   * @param beginDate 开始时间
   * @param endDate   最大时间跨度30天，endDate不能早于begingDate
   */
  List<WxDataCubeMsgResult> getUpstreamMsgDistWeek(String appid, Date beginDate, Date endDate) throws WxErrorException;

  /**
   * <pre>
   * 获取消息发送分布月数据（getupstreammsgdistmonth）
   * 详情请见文档：<a href="http://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421141085&token=&lang=zh_CN">消息分析数据接口</a>
   * 接口url格式：https://api.weixin.qq.com/datacube/getupstreammsgdistmonth?access_token=ACCESS_TOKEN
   *
   * @param beginDate 开始时间
   * @param endDate   最大时间跨度30天，endDate不能早于begingDate
   */
  List<WxDataCubeMsgResult> getUpstreamMsgDistMonth(String appid, Date beginDate, Date endDate) throws WxErrorException;

  //*******************接口分析数据接口***********************//

  /**
   * <pre>
   * 获取接口分析数据（getinterfacesummary）
   * 详情请见文档：<a href="http://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421141086&token=&lang=zh_CN">接口分析数据接口</a>
   * 接口url格式：https://api.weixin.qq.com/datacube/getinterfacesummary?access_token=ACCESS_TOKEN
   *
   * @param beginDate 开始时间
   * @param endDate   最大时间跨度30天，endDate不能早于begingDate
   */
  List<WxDataCubeInterfaceResult> getInterfaceSummary(String appid, Date beginDate, Date endDate) throws WxErrorException;

  /**
   * <pre>
   * 获取接口分析分时数据（getinterfacesummaryhour）
   * 详情请见文档：<a href="http://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421141086&token=&lang=zh_CN">接口分析数据接口</a>
   * 接口url格式：https://api.weixin.qq.com/datacube/getinterfacesummaryhour?access_token=ACCESS_TOKEN
   *
   * @param beginDate 开始时间
   * @param endDate   最大时间跨度1天，endDate不能早于begingDate
   */
  List<WxDataCubeInterfaceResult> getInterfaceSummaryHour(String appid, Date beginDate, Date endDate) throws WxErrorException;

}
