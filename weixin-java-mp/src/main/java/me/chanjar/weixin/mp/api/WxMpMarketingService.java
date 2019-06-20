package me.chanjar.weixin.mp.api;

import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.bean.marketing.WxMpAdLeadFilter;
import me.chanjar.weixin.mp.bean.marketing.WxMpAdLeadResult;
import me.chanjar.weixin.mp.bean.marketing.WxMpUserAction;
import me.chanjar.weixin.mp.bean.marketing.WxMpUserActionSet;

import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * <pre>
 * 微信营销接口
 * </pre>
 *
 * @author <a href="https://github.com/007gzs">007</a>
 */
public interface WxMpMarketingService {
  String API_URL_PREFIX = "https://api.weixin.qq.com/marketing/";

  /**
   * <pre>
   * 创建数据源
   * 接口调用请求说明
   * https://wximg.qq.com/wxp/pdftool/get.html?id=rkalQXDBM&pa=39
   * </pre>
   *
   * @param type        用户行为源类型
   * @param name        用户行为源名称 必填
   * @param description 用户行为源描述，字段长度最小 1 字节，长度最大 128 字节
   */
  long addUserActionSets(String type, String name, String description) throws WxErrorException;

  /**
   * <pre>
   * 获取数据源信息
   * </pre>
   *
   * @param userActionSetId 数据源唯一ID
   */
  List<WxMpUserActionSet> getUserActionSets(Long userActionSetId) throws WxErrorException;

  /**
   * 回传数据
   * 接口调用请求说明
   * https://wximg.qq.com/wxp/pdftool/get.html?id=rkalQXDBM&pa=39
   *
   * @param actions 用户行为源类型
   */
  void addUserAction(List<WxMpUserAction> actions) throws WxErrorException;

  /**
   * <pre>
   * 获取朋友圈销售线索数据接口
   * 接口调用请求说明
   *
   * http请求方式: POST
   * http://api.weixin.qq.com/cgi-bin/media/voice/translatecontent?access_token=ACCESS_TOKEN&lfrom=xxx&lto=xxx
   *
   * </pre>
   *
   * @param beginDate 开始日期
   * @param endDate   结束日期
   * @param filtering 过滤条件
   * @param page      页码，获取指定页数据
   * @param page_size 一页获取的数据条数(1-100)
   */
  WxMpAdLeadResult getAdLeads(Date beginDate, Date endDate, List<WxMpAdLeadFilter> filtering, Integer page, Integer page_size) throws WxErrorException, IOException;
}
