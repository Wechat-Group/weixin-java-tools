package me.chanjar.weixin.open.api.impl;

import com.google.gson.JsonObject;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.bean.datacube.*;
import me.chanjar.weixin.open.api.WxOpenDataCubeService;
import me.chanjar.weixin.open.api.WxOpenService;
import org.apache.commons.lang3.time.FastDateFormat;

import java.text.Format;
import java.util.Date;
import java.util.List;

/**
 * Created by Binary Wang on 2016/8/23.
 *
 * @author binarywang (https://github.com/binarywang)
 */
public class WxOpenDataCubeServiceImpl implements WxOpenDataCubeService {

  private final Format dateFormat = FastDateFormat.getInstance("yyyy-MM-dd");

  private WxOpenService wxOpenService;

  public WxOpenDataCubeServiceImpl(WxOpenService wxOpenService) {
    this.wxOpenService = wxOpenService;
  }

  @Override
  public List<WxDataCubeUserSummary> getUserSummary(String appid, Date beginDate, Date endDate) throws WxErrorException {
    String responseContent = this.wxOpenService.post(GET_USER_SUMMARY, buildParams(beginDate, endDate));
    return WxDataCubeUserSummary.fromJson(responseContent);
  }

  @Override
  public List<WxDataCubeUserCumulate> getUserCumulate(String appid, Date beginDate, Date endDate) throws WxErrorException {
    String responseContent = this.wxOpenService.post(GET_USER_CUMULATE, buildParams(beginDate, endDate));
    return WxDataCubeUserCumulate.fromJson(responseContent);
  }

  @Override
  public List<WxDataCubeArticleResult> getArticleSummary(String appid, Date beginDate, Date endDate) throws WxErrorException {
    return this.getArticleResults(appid, GET_ARTICLE_SUMMARY, beginDate, endDate);
  }

  @Override
  public List<WxDataCubeArticleTotal> getArticleTotal(String appid, Date beginDate, Date endDate) throws WxErrorException {
    String responseContent = this.wxOpenService.post(appid, GET_ARTICLE_TOTAL, buildParams(beginDate, endDate));
    return WxDataCubeArticleTotal.fromJson(responseContent);
  }

  @Override
  public List<WxDataCubeArticleResult> getUserRead(String appid, Date beginDate, Date endDate) throws WxErrorException {
    return this.getArticleResults(appid, GET_USER_READ, beginDate, endDate);
  }

  @Override
  public List<WxDataCubeArticleResult> getUserReadHour(String appid, Date beginDate, Date endDate) throws WxErrorException {
    return this.getArticleResults(appid, GET_USER_READ_HOUR, beginDate, endDate);
  }

  @Override
  public List<WxDataCubeArticleResult> getUserShare(String appid, Date beginDate, Date endDate) throws WxErrorException {
    return this.getArticleResults(appid, GET_USER_SHARE, beginDate, endDate);
  }

  @Override
  public List<WxDataCubeArticleResult> getUserShareHour(String appid, Date beginDate, Date endDate) throws WxErrorException {
    return this.getArticleResults(appid, GET_USER_SHARE_HOUR, beginDate, endDate);
  }

  private List<WxDataCubeArticleResult> getArticleResults(String appid, String url, Date beginDate, Date endDate) throws WxErrorException {
    String responseContent = this.wxOpenService.post(url, buildParams(beginDate, endDate));
    return WxDataCubeArticleResult.fromJson(responseContent);
  }

  @Override
  public List<WxDataCubeMsgResult> getUpstreamMsg(String appid, Date beginDate, Date endDate) throws WxErrorException {
    return this.getUpstreamMsg(GET_UPSTREAM_MSG, beginDate, endDate);
  }

  @Override
  public List<WxDataCubeMsgResult> getUpstreamMsgHour(String appid, Date beginDate, Date endDate) throws WxErrorException {
    return this.getUpstreamMsg(GET_UPSTREAM_MSG_HOUR, beginDate, endDate);
  }

  @Override
  public List<WxDataCubeMsgResult> getUpstreamMsgWeek(String appid, Date beginDate, Date endDate) throws WxErrorException {
    return this.getUpstreamMsg(GET_UPSTREAM_MSG_WEEK, beginDate, endDate);
  }

  @Override
  public List<WxDataCubeMsgResult> getUpstreamMsgMonth(String appid, Date beginDate, Date endDate) throws WxErrorException {
    return this.getUpstreamMsg(GET_UPSTREAM_MSG_MONTH, beginDate, endDate);
  }

  @Override
  public List<WxDataCubeMsgResult> getUpstreamMsgDist(String appid, Date beginDate, Date endDate) throws WxErrorException {
    return this.getUpstreamMsg(GET_UPSTREAM_MSG_DIST, beginDate, endDate);
  }

  @Override
  public List<WxDataCubeMsgResult> getUpstreamMsgDistWeek(String appid, Date beginDate, Date endDate) throws WxErrorException {
    return this.getUpstreamMsg(GET_UPSTREAM_MSG_DIST_WEEK, beginDate, endDate);
  }

  @Override
  public List<WxDataCubeMsgResult> getUpstreamMsgDistMonth(String appid, Date beginDate, Date endDate) throws WxErrorException {
    return this.getUpstreamMsg(GET_UPSTREAM_MSG_DIST_MONTH, beginDate, endDate);
  }

  private List<WxDataCubeMsgResult> getUpstreamMsg(String appid, String url, Date beginDate, Date endDate) throws WxErrorException {
    String responseContent = this.wxOpenService.post(url, buildParams(beginDate, endDate));
    return WxDataCubeMsgResult.fromJson(responseContent);
  }

  @Override
  public List<WxDataCubeInterfaceResult> getInterfaceSummary(String appid, Date beginDate, Date endDate) throws WxErrorException {
    String responseContent = this.wxOpenService.post(GET_INTERFACE_SUMMARY, buildParams(beginDate, endDate));
    return WxDataCubeInterfaceResult.fromJson(responseContent);
  }

  private String buildParams(Date beginDate, Date endDate) {
    JsonObject param = new JsonObject();
    param.addProperty("begin_date", this.dateFormat.format(beginDate));
    param.addProperty("end_date", this.dateFormat.format(endDate));
    return param.toString();
  }

  @Override
  public List<WxDataCubeInterfaceResult> getInterfaceSummaryHour(String appid, Date beginDate, Date endDate) throws WxErrorException {
    String responseContent = this.wxOpenService.post(GET_INTERFACE_SUMMARY_HOUR, buildParams(beginDate, endDate));
    return WxDataCubeInterfaceResult.fromJson(responseContent);
  }
}
