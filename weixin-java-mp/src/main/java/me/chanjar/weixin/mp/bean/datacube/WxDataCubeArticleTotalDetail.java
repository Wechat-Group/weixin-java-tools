package me.chanjar.weixin.mp.bean.datacube;

import com.google.gson.annotations.SerializedName;

/**
 * 获取图文群发总数据接口(getarticletotal)中的详细字段
 *
 * @author <a href="https://github.com/binarywang">binarywang(Binary Wang)</a>
 *         Created by Binary Wang on 2016/8/24.
 */
public class WxDataCubeArticleTotalDetail {

  /**
   * stat_date
   * 统计的日期，在getarticletotal接口中，ref_date指的是文章群发出日期， 而stat_date是数据统计日期
   */
  @SerializedName("stat_date")
  private String statDate;

  /**
   * target_user
   * 送达人数，一般约等于总粉丝数（需排除黑名单或其他异常情况下无法收到消息的粉丝）
   */
  @SerializedName("target_user")
  private Integer targetUser;

  /**
   * int_page_read_user
   * 图文页（点击群发图文卡片进入的页面）的阅读人数
   */
  @SerializedName("int_page_read_user")
  private Integer intPageReadUser;

  /**
   * int_page_read_count
   * 图文页的阅读次数
   */
  @SerializedName("int_page_read_count")
  private Integer intPageReadCount;

  /**
   * ori_page_read_user
   * 原文页（点击图文页“阅读原文”进入的页面）的阅读人数，无原文页时此处数据为0
   */
  @SerializedName("ori_page_read_user")
  private Integer oriPageReadUser;

  /**
   * ori_page_read_count
   * 原文页的阅读次数
   */
  @SerializedName("ori_page_read_count")
  private Integer oriPageReadCount;

  /**
   * share_user
   * 分享的人数
   */
  @SerializedName("share_user")
  private Integer shareUser;

  /**
   * share_count
   * 分享的次数
   */
  @SerializedName("share_count")
  private Integer shareCount;

  /**
   * add_to_fav_user
   * 收藏的人数
   */
  @SerializedName("add_to_fav_user")
  private Integer addToFavUser;

  /**
   * add_to_fav_count
   * 收藏的次数
   */
  @SerializedName("add_to_fav_count")
  private Integer addToFavCount;

  /**
   * int_page_from_session_read_user
   * 公众号会话阅读人数
   */
  @SerializedName("int_page_from_session_read_user")
  private Integer intPageFromSessionReadUser;

  /**
   * int_page_from_session_read_count
   * 公众号会话阅读次数
   */
  @SerializedName("int_page_from_session_read_count")
  private Integer intPageFromSessionReadCount;

  /**
   * int_page_from_hist_msg_read_user
   * 历史消息页阅读人数
   */
  @SerializedName("int_page_from_hist_msg_read_user")
  private Integer intPageFromHistMsgReadUser;

  /**
   * int_page_from_hist_msg_read_count
   * 历史消息页阅读次数
   */
  @SerializedName("int_page_from_hist_msg_read_count")
  private Integer intPageFromHistMsgReadCount;

  /**
   * int_page_from_feed_read_user
   * 朋友圈阅读人数
   */
  @SerializedName("int_page_from_feed_read_user")
  private Integer intPageFromFeedReadUser;

  /**
   * int_page_from_feed_read_count
   * 朋友圈阅读次数
   */
  @SerializedName("int_page_from_feed_read_count")
  private Integer intPageFromFeedReadCount;

  /**
   * int_page_from_friends_read_user
   * 好友转发阅读人数
   */
  @SerializedName("int_page_from_friends_read_user")
  private Integer intPageFromFriendsReadUser;

  /**
   * int_page_from_friends_read_count
   * 好友转发阅读次数
   */
  @SerializedName("int_page_from_friends_read_count")
  private Integer intPageFromFriendsReadCount;

  /**
   * int_page_from_other_read_user
   * 其他场景阅读人数
   */
  @SerializedName("int_page_from_other_read_user")
  private Integer intPageFromOtherReadUser;

  /**
   * int_page_from_other_read_count
   * 其他场景阅读次数
   */
  @SerializedName("int_page_from_other_read_count")
  private Integer intPageFromOtherReadCount;

  /**
   * feed_share_from_session_user
   * 公众号会话转发朋友圈人数
   */
  @SerializedName("feed_share_from_session_user")
  private Integer feedShareFromSessionUser;

  /**
   * feed_share_from_session_cnt
   * 公众号会话转发朋友圈次数
   */
  @SerializedName("feed_share_from_session_cnt")
  private Integer feedShareFromSessionCnt;

  /**
   * feed_share_from_feed_user
   * 朋友圈转发朋友圈人数
   */
  @SerializedName("feed_share_from_feed_user")
  private Integer feedShareFromFeedUser;

  /**
   * feed_share_from_feed_cnt
   * 朋友圈转发朋友圈次数
   */
  @SerializedName("feed_share_from_feed_cnt")
  private Integer feedShareFromFeedCnt;

  /**
   * feed_share_from_other_user
   * 其他场景转发朋友圈人数
   */
  @SerializedName("feed_share_from_other_user")
  private Integer feedShareFromOtherUser;

  /**
   * feed_share_from_other_cnt
   * 其他场景转发朋友圈次数
   */
  @SerializedName("feed_share_from_other_cnt")
  private Integer feedShareFromOtherCnt;

  public String getStatDate() {
    return this.statDate;
  }

  public void setStatDate(String statDate) {
    this.statDate = statDate;
  }

  public Integer getTargetUser() {
    return this.targetUser;
  }

  public void setTargetUser(Integer targetUser) {
    this.targetUser = targetUser;
  }

  public Integer getIntPageReadUser() {
    return this.intPageReadUser;
  }

  public void setIntPageReadUser(Integer intPageReadUser) {
    this.intPageReadUser = intPageReadUser;
  }

  public Integer getIntPageReadCount() {
    return this.intPageReadCount;
  }

  public void setIntPageReadCount(Integer intPageReadCount) {
    this.intPageReadCount = intPageReadCount;
  }

  public Integer getOriPageReadUser() {
    return this.oriPageReadUser;
  }

  public void setOriPageReadUser(Integer oriPageReadUser) {
    this.oriPageReadUser = oriPageReadUser;
  }

  public Integer getOriPageReadCount() {
    return this.oriPageReadCount;
  }

  public void setOriPageReadCount(Integer oriPageReadCount) {
    this.oriPageReadCount = oriPageReadCount;
  }

  public Integer getShareUser() {
    return this.shareUser;
  }

  public void setShareUser(Integer shareUser) {
    this.shareUser = shareUser;
  }

  public Integer getShareCount() {
    return this.shareCount;
  }

  public void setShareCount(Integer shareCount) {
    this.shareCount = shareCount;
  }

  public Integer getAddToFavUser() {
    return this.addToFavUser;
  }

  public void setAddToFavUser(Integer addToFavUser) {
    this.addToFavUser = addToFavUser;
  }

  public Integer getAddToFavCount() {
    return this.addToFavCount;
  }

  public void setAddToFavCount(Integer addToFavCount) {
    this.addToFavCount = addToFavCount;
  }

  public Integer getIntPageFromSessionReadUser() {
    return this.intPageFromSessionReadUser;
  }

  public void setIntPageFromSessionReadUser(Integer intPageFromSessionReadUser) {
    this.intPageFromSessionReadUser = intPageFromSessionReadUser;
  }

  public Integer getIntPageFromSessionReadCount() {
    return this.intPageFromSessionReadCount;
  }

  public void setIntPageFromSessionReadCount(
    Integer intPageFromSessionReadCount) {
    this.intPageFromSessionReadCount = intPageFromSessionReadCount;
  }

  public Integer getIntPageFromHistMsgReadUser() {
    return this.intPageFromHistMsgReadUser;
  }

  public void setIntPageFromHistMsgReadUser(Integer intPageFromHistMsgReadUser) {
    this.intPageFromHistMsgReadUser = intPageFromHistMsgReadUser;
  }

  public Integer getIntPageFromHistMsgReadCount() {
    return this.intPageFromHistMsgReadCount;
  }

  public void setIntPageFromHistMsgReadCount(
    Integer intPageFromHistMsgReadCount) {
    this.intPageFromHistMsgReadCount = intPageFromHistMsgReadCount;
  }

  public Integer getIntPageFromFeedReadUser() {
    return this.intPageFromFeedReadUser;
  }

  public void setIntPageFromFeedReadUser(Integer intPageFromFeedReadUser) {
    this.intPageFromFeedReadUser = intPageFromFeedReadUser;
  }

  public Integer getIntPageFromFeedReadCount() {
    return this.intPageFromFeedReadCount;
  }

  public void setIntPageFromFeedReadCount(Integer intPageFromFeedReadCount) {
    this.intPageFromFeedReadCount = intPageFromFeedReadCount;
  }

  public Integer getIntPageFromFriendsReadUser() {
    return this.intPageFromFriendsReadUser;
  }

  public void setIntPageFromFriendsReadUser(Integer intPageFromFriendsReadUser) {
    this.intPageFromFriendsReadUser = intPageFromFriendsReadUser;
  }

  public Integer getIntPageFromFriendsReadCount() {
    return this.intPageFromFriendsReadCount;
  }

  public void setIntPageFromFriendsReadCount(
    Integer intPageFromFriendsReadCount) {
    this.intPageFromFriendsReadCount = intPageFromFriendsReadCount;
  }

  public Integer getIntPageFromOtherReadUser() {
    return this.intPageFromOtherReadUser;
  }

  public void setIntPageFromOtherReadUser(Integer intPageFromOtherReadUser) {
    this.intPageFromOtherReadUser = intPageFromOtherReadUser;
  }

  public Integer getIntPageFromOtherReadCount() {
    return this.intPageFromOtherReadCount;
  }

  public void setIntPageFromOtherReadCount(Integer intPageFromOtherReadCount) {
    this.intPageFromOtherReadCount = intPageFromOtherReadCount;
  }

  public Integer getFeedShareFromSessionUser() {
    return this.feedShareFromSessionUser;
  }

  public void setFeedShareFromSessionUser(Integer feedShareFromSessionUser) {
    this.feedShareFromSessionUser = feedShareFromSessionUser;
  }

  public Integer getFeedShareFromSessionCnt() {
    return this.feedShareFromSessionCnt;
  }

  public void setFeedShareFromSessionCnt(Integer feedShareFromSessionCnt) {
    this.feedShareFromSessionCnt = feedShareFromSessionCnt;
  }

  public Integer getFeedShareFromFeedUser() {
    return this.feedShareFromFeedUser;
  }

  public void setFeedShareFromFeedUser(Integer feedShareFromFeedUser) {
    this.feedShareFromFeedUser = feedShareFromFeedUser;
  }

  public Integer getFeedShareFromFeedCnt() {
    return this.feedShareFromFeedCnt;
  }

  public void setFeedShareFromFeedCnt(Integer feedShareFromFeedCnt) {
    this.feedShareFromFeedCnt = feedShareFromFeedCnt;
  }

  public Integer getFeedShareFromOtherUser() {
    return this.feedShareFromOtherUser;
  }

  public void setFeedShareFromOtherUser(Integer feedShareFromOtherUser) {
    this.feedShareFromOtherUser = feedShareFromOtherUser;
  }

  public Integer getFeedShareFromOtherCnt() {
    return this.feedShareFromOtherCnt;
  }

  public void setFeedShareFromOtherCnt(Integer feedShareFromOtherCnt) {
    this.feedShareFromOtherCnt = feedShareFromOtherCnt;
  }

}
