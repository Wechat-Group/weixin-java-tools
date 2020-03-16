package me.chanjar.weixin.cp.bean;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.util.List;

/**
 * 联系客户群统计数据
 *
 * @author yqx
 * @date 2020/3/16
 */
@Getter
@Setter
public class WxCpUserExternalGroupChatStatistic extends WxCpBaseResp{

  @SerializedName("total")
  int total;

  @SerializedName("next_offset")
  int nextOffset;

  @SerializedName("items")
  List<StatisticItem> itemList;

  @Getter
  @Setter
  public static class StatisticItem {

    @SerializedName("owner")
    String owner;

    @SerializedName("data")
    ItemData itemData;
  }

  @Getter
  @Setter
  public static class ItemData {

    /**
     * 新增客户群数量
     */
    @SerializedName("new_chat_cnt")
    int newChatCnt;

    /**
     * 截至当天客户群总数量
     */
    @SerializedName("chat_total")
    int chatTotal;

    /**
     * 截至当天有发过消息的客户群数量
     */
    @SerializedName("chat_has_msg")
    int chatHasMsg;

    /**
     * 客户群新增群人数。
     */
    @SerializedName("new_member_cnt")
    int newMemberCnt;

    /**
     * 截至当天客户群总人数
     */
    @SerializedName("member_total")
    int memberTotal;

    /**
     * 截至当天有发过消息的群成员数
     */
    @SerializedName("member_has_msg")
    int memberHasMsg;

    /**
     * 截至当天客户群消息总数
     */
    @SerializedName("msg_total")
    int msgTotal;
  }

  public static WxCpUserExternalGroupChatStatistic fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpUserExternalGroupChatStatistic.class);
  }
}
