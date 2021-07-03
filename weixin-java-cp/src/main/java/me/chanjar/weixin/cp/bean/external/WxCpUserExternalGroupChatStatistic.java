package me.chanjar.weixin.cp.bean.external;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;
import me.chanjar.weixin.cp.bean.WxCpBaseResp;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * 联系客户群统计数据
 *
 * @author yqx
 * @date 2020/3/16
 */
@Getter
@Setter
public class WxCpUserExternalGroupChatStatistic extends WxCpBaseResp {
  private static final long serialVersionUID = -3548998672207956622L;

  @SerializedName("total")
  private int total;

  @SerializedName("next_offset")
  private int nextOffset;

  @SerializedName("items")
  private List<StatisticItem> itemList;

  @Getter
  @Setter
  public static class StatisticItem implements Serializable {
    private static final long serialVersionUID = -7272935708787587856L;

    @SerializedName("owner")
    private String owner;

    @SerializedName("data")
    private ItemData itemData;
  }

  @Getter
  @Setter
  public static class ItemData implements Serializable {
    private static final long serialVersionUID = 354382008606856587L;

    /**
     * 新增客户群数量
     */
    @SerializedName("new_chat_cnt")
    private int newChatCnt;

    /**
     * 截至当天客户群总数量
     */
    @SerializedName("chat_total")
    private int chatTotal;

    /**
     * 截至当天有发过消息的客户群数量
     */
    @SerializedName("chat_has_msg")
    private int chatHasMsg;

    /**
     * 客户群新增群人数。
     */
    @SerializedName("new_member_cnt")
    private int newMemberCnt;

    /**
     * 截至当天客户群总人数
     */
    @SerializedName("member_total")
    private int memberTotal;

    /**
     * 截至当天有发过消息的群成员数
     */
    @SerializedName("member_has_msg")
    private int memberHasMsg;

    /**
     * 截至当天客户群消息总数
     */
    @SerializedName("msg_total")
    private int msgTotal;
  }

  public static WxCpUserExternalGroupChatStatistic fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpUserExternalGroupChatStatistic.class);
  }
}
