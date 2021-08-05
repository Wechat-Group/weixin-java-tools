package me.chanjar.weixin.cp.bean.external;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;
import me.chanjar.weixin.cp.bean.WxCpBaseResp;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * 联系客户统计数据
 *
 * @author yqx
 * @date 2020/3/16
 */
@Getter
@Setter
public class WxCpUserExternalUserBehaviorStatistic extends WxCpBaseResp {

  @SerializedName("behavior_data")
  private List<Behavior> behaviorList;

  @Getter
  @Setter
  public static class Behavior implements Serializable {
    private static final long serialVersionUID = -4301684507150486556L;

    /**
     * 数据日期，为当日0点的时间戳
     */
    @SerializedName("stat_time")
    private Long statTime;

    /**
     * 聊天总数， 成员有主动发送过消息的聊天数，包括单聊和群聊。
     */
    @SerializedName("chat_cnt")
    private int chatCnt;

    /**
     * 发送消息数，成员在单聊和群聊中发送的消息总数。
     */
    @SerializedName("message_cnt")
    private int messageCnt;

    /**
     * 已回复聊天占比，客户主动发起聊天后，成员在一个自然日内有回复过消息的聊天数/客户主动发起的聊天数比例，不包括群聊，仅在确有回复时返回。
     */
    @SerializedName("reply_percentage")
    private double replyPercentage;

    /**
     * 平均首次回复时长，单位为分钟，即客户主动发起聊天后，成员在一个自然日内首次回复的时长间隔为首次回复时长，所有聊天的首次回复总时长/已回复的聊天总数即为平均首次回复时长，不包括群聊，仅在确有回复时返回。
     */
    @SerializedName("avg_reply_time")
    private int avgReplyTime;

    /**
     * 删除/拉黑成员的客户数，即将成员删除或加入黑名单的客户数。
     */
    @SerializedName("negative_feedback_cnt")
    private int negativeFeedbackCnt;

    /**
     * 发起申请数，成员通过「搜索手机号」、「扫一扫」、「从微信好友中添加」、「从群聊中添加」、「添加共享、分配给我的客户」、「添加单向、双向删除好友关系的好友」、「从新的联系人推荐中添加」等渠道主动向客户发起的好友申请数量。
     */
    @SerializedName("new_apply_cnt")
    private int newApplyCnt;

    /**
     * 新增客户数，成员新添加的客户数量。
     */
    @SerializedName("new_contact_cnt")
    private int newContactCnt;
  }

  public static WxCpUserExternalUserBehaviorStatistic fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpUserExternalUserBehaviorStatistic.class);
  }
}
