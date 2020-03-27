package me.chanjar.weixin.mp.bean.datacube;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;

/**
 * 获取图文群发总数据接口(getarticletotal)中的详细字段
 * <p>
 * Created by Binary Wang on 2016/8/24.
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
@Data
public class WxDataCubeArticleTotalDetail implements Serializable {
  private static final long serialVersionUID = -5136169129771430052L;

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


  /**
   * intpagefromkanyikanreaduser 看一看来源阅读人数
   */
  @SerializedName("int_page_from_kanyikan_read_user")
  private Integer intPageFromKanyikanReadUser;

  /**
   * intpagefromkanyikanreadcount 看一看来源阅读次数
   */
  @SerializedName("int_page_from_kanyikan_read_count")
  private Integer intPageFromKanyikanReadCount;

  /**
   * intpagefromsouyisoureaduser 搜一搜来源阅读人数
   */
  @SerializedName("int_page_from_souyisou_read_user")
  private Integer intPageFromSouyisouReadUser;

  /**
   * intpagefromsouyisoureadcount 搜一搜来源阅读次数
   */
  @SerializedName("int_page_from_souyisou_read_count")
  private Integer intPageFromSouyisouReadCount;

}
