package me.chanjar.weixin.cp.bean.kf;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.cp.bean.WxCpBaseResp;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.util.List;

/**
 * 获取「客户数据统计」企业汇总数据
 *
 * @author zhongjun
 * @date 2022/4/25
 **/
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Data
public class WxCpKfGetCorpStatisticResp extends WxCpBaseResp {
  private static final long serialVersionUID = -89898989898989898L;

  /**
   * 统计数据列表
   */
  @SerializedName("statistic_list")
  private List<StatisticList> statisticList;

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
    private Statistic statistic;
  }

  @NoArgsConstructor
  @Data
  public static class Statistic {

    /**
     * 咨询会话数。客户发过消息并分配给接待人员或智能助手的客服会话数，转接不会产生新的会话
     */
    @SerializedName("session_cnt")
    private Integer sessionCnt;

    /**
     * 咨询客户数。在会话中发送过消息的客户数量，若客户多次咨询只计算一个客户
     */
    @SerializedName("user_cnt")
    private Integer customerCnt;

    /**
     * 咨询消息总数。客户在会话中发送的消息的数量
     */
    @SerializedName("customer_msg_cnt")
    private Integer customerMsgCnt;

    /**
     * 升级服务客户数。通过「升级服务」功能成功添加专员或加入客户群的客户数，若同一个客户添加多个专员或客户群，只计算一个客户。在2022年3月10日以后才会有对应统计数据
     */
    @SerializedName("upgrade_service_customer_cnt")
    private Integer upgradeServiceCustomerCnt;

    /**
     * 智能回复会话数。客户发过消息并分配给智能助手的咨询会话数。通过API发消息或者开启智能回复功能会将客户分配给智能助手
     */
    @SerializedName("ai_transfer_rate")
    private Integer aiSessionReplyCnt;

    /**
     * 转人工率。一个自然日内，客户给智能助手发消息的会话中，转人工的会话的占比。
     */
    @SerializedName("ai_transfer_rate")
    private Integer aiTransferRate;

    /**
     * 知识命中率。一个自然日内，客户给智能助手发送的消息中，命中知识库的占比。只有在开启了智能回复原生功能并配置了知识库的情况下，才会产生该项统计数据。当api托管了会话分配，智能回复原生功能失效。若不返回，代表没有向配置知识库的智能接待助手发送消息，该项无法计算
     */
    @SerializedName("ai_knowledge_hit_rate")
    private Integer aiKnowledgeHitRate;
  }

  public static WxCpKfGetCorpStatisticResp fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpKfGetCorpStatisticResp.class);
  }
}
