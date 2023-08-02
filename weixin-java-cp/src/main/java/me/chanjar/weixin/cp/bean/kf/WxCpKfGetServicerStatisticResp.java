package me.chanjar.weixin.cp.bean.kf;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.cp.bean.WxCpBaseResp;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.util.List;

/**
 * 获取「客户数据统计」接待人员明细数据
 *
 * @author MsThink  created on  2023/5/13
 */
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Data
public class WxCpKfGetServicerStatisticResp extends WxCpBaseResp {
  /**
   * 统计数据列表
   */
  @SerializedName("statistic_list")
  private List<WxCpKfGetServicerStatisticResp.StatisticList> statisticList;

  /**
   * The type Statistic list.
   */
  @NoArgsConstructor
  @Data
  public static class StatisticList {
    /**
     * 数据统计日期，为当日0点的时间戳
     */
    @SerializedName("stat_time")
    private Long statTime;

    /**
     * 一天的统计数据。若当天未产生任何下列统计数据或统计数据还未计算完成则不会返回此项
     */
    @SerializedName("statistic")
    private WxCpKfGetServicerStatisticResp.Statistic statistic;
  }

  /**
   * The type Statistic.
   */
  @NoArgsConstructor
  @Data
  public static class Statistic {

    /**
     * 接入人工会话数。客户发过消息并分配给接待人员的咨询会话数
     */
    @SerializedName("session_cnt")
    private Integer sessionCnt;

    /**
     * 咨询客户数。在会话中发送过消息且接入了人工会话的客户数量，若客户多次咨询只计算一个客户
     */
    @SerializedName("customer_cnt")
    private Integer customerCnt;

    /**
     * 咨询消息总数。客户在会话中发送的消息的数量
     */
    @SerializedName("customer_msg_cnt")
    private Integer customerMsgCnt;

    /**
     * 人工回复率。一个自然日内，客户给接待人员发消息的会话中，接待人员回复了的会话的占比。若数据项不返回，代表没有给接待人员发送消息的客户，此项无法计算。
     */
    @SerializedName("reply_rate")
    private Float replyRate;

    /**
     * 平均首次响应时长，单位：秒。一个自然日内，客户给接待人员发送的第一条消息至接待人员回复之间的时长，为首次响应时长。所有的首次回复总时长/已回复的咨询会话数，
     * 即为平均首次响应时长 。若数据项不返回，代表没有给接待人员发送消息的客户，此项无法计算
     */
    @SerializedName("first_reply_average_sec")
    private Float firstReplyAverageSec;

    /**
     * 满意度评价发送数。当api托管了会话分配，满意度原生功能失效，满意度评价发送数为0
     */
    @SerializedName("satisfaction_investgate_cnt")
    private Integer satisfactionInvestgateCnt;

    /**
     * 满意度参评率 。当api托管了会话分配，满意度原生功能失效。若数据项不返回，代表没有发送满意度评价，此项无法计算
     */
    @SerializedName("satisfaction_participation_rate")
    private Float satisfactionParticipationRate;

    /**
     * “满意”评价占比 。在客户参评的满意度评价中，评价是“满意”的占比。当api托管了会话分配，满意度原生功能失效。若数据项不返回，代表没有客户参评的满意度评价，此项无法计算
     */
    @SerializedName("satisfied_rate")
    private Float satisfiedRate;

    /**
     * “一般”评价占比 。在客户参评的满意度评价中，评价是“一般”的占比。当api托管了会话分配，满意度原生功能失效。若数据项不返回，代表没有客户参评的满意度评价，此项无法计算
     */
    @SerializedName("middling_rate")
    private Float middlingRate;

    /**
     * “不满意”评价占比。在客户参评的满意度评价中，评价是“不满意”的占比。当api托管了会话分配，满意度原生功能失效。若数据项不返回，代表没有客户参评的满意度评价，此项无法计算
     */
    @SerializedName("dissatisfied_rate")
    private Float dissatisfiedRate;

    /**
     * 升级服务客户数。通过「升级服务」功能成功添加专员或加入客户群的客户数，若同一个客户添加多个专员或客户群，只计算一个客户。在2022年3月10日以后才会有对应统计数据
     */
    @SerializedName("upgrade_service_customer_cnt")
    private Integer upgradeServiceCustomerCnt;

    /**
     * 专员服务邀请数。接待人员通过「升级服务-专员服务」向客户发送服务专员名片的次数。在2022年3月10日以后才会有对应统计数据
     */
    @SerializedName("upgrade_service_member_invite_cnt")
    private Integer upgradeServiceMemberInviteCnt;

    /**
     * 添加专员的客户数 。客户成功添加专员为好友的数量，若同一个客户添加多个专员，则计算多个客户数。在2022年3月10日以后才会有对应统计数据
     */
    @SerializedName("upgrade_service_member_customer_cnt")
    private Integer upgradeServiceMemberCustomerCnt;

    /**
     * 客户群服务邀请数。接待人员通过「升级服务-客户群服务」向客户发送客户群二维码的次数。在2022年3月10日以后才会有对应统计数据
     */
    @SerializedName("upgrade_service_groupchat_invite_cnt")
    private Integer upgradeServiceGroupchatInviteCnt;

    /**
     * 加入客户群的客户数。客户成功加入客户群的数量，若同一个客户加多个客户群，则计算多个客户数。在2022年3月10日以后才会有对应统计数据
     */
    @SerializedName("upgrade_service_groupchat_customer_cnt")
    private Integer upgradeServiceGroupchatCustomerCnt;

    /**
     * 被拒收消息的客户数。被接待人员设置了“不再接收消息”的客户数
     */
    @SerializedName("msg_rejected_customer_cnt")
    private Integer msgRejectedCustomerCnt;
  }
  /**
   * From json wx cp kf get servicer statistic resp.
   *
   * @param json the json
   * @return the wx cp kf get servicer statistic resp
   */
  public static WxCpKfGetServicerStatisticResp fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpKfGetServicerStatisticResp.class);
  }
}
