package me.chanjar.weixin.cp.bean.oa.selfagent;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * 审批申请当前状态信息.
 *
 * @author Wang_Wong
 */
@Data
public class WxCpOpenApprovalData implements Serializable {
  private static final long serialVersionUID = -5028321625140879591L;

  @SerializedName("ThirdNo")
  private String thirdNo;

  @SerializedName("OpenTemplateId")
  private String openTemplateId;

  @SerializedName("OpenSpName")
  private String openSpName;

  @SerializedName("OpenSpstatus")
  private Integer openSpstatus;

  @SerializedName("ApplyTime")
  private Long applyTime;

  @SerializedName("ApplyUsername")
  private String applyUserName;

  @SerializedName("ApplyUserParty")
  private String applyUserParty;

  @SerializedName("ApplyUserImage")
  private String applyUserImage;

  @SerializedName("ApplyUserId")
  private String applyUserId;

  @SerializedName("ApprovalNodes")
  private ApprovalNodes approvalNodes;

  @SerializedName("NotifyNodes")
  private NotifyNodes notifyNodes;

  @SerializedName("ApproverStep")
  private Integer approverStep;

  /**
   * The type Approval nodes.
   */
  @Getter
  @Setter
  public static class ApprovalNodes implements Serializable {
    private static final long serialVersionUID = -5696099236344075582L;

    @SerializedName("ApprovalNode")
    private List<ApprovalNode> approvalNode;

  }

  /**
   * The type Approval node.
   */
  @Getter
  @Setter
  public static class ApprovalNode implements Serializable {
    private static final long serialVersionUID = -5696099236344075582L;

    @SerializedName("NodeStatus")
    private Integer nodeStatus;

    @SerializedName("NodeAttr")
    private Integer nodeAttr;

    @SerializedName("NodeType")
    private Integer nodeType;

    @SerializedName("Items")
    private Items items;

  }

  /**
   * The type Notify nodes.
   */
  @Getter
  @Setter
  public static class NotifyNodes implements Serializable {
    private static final long serialVersionUID = -5696099236344075582L;

    @SerializedName("NotifyNode")
    private List<NotifyNode> notifyNode;

  }

  /**
   * The type Notify node.
   */
  @Getter
  @Setter
  public static class NotifyNode implements Serializable {
    private static final long serialVersionUID = -5696099236344075582L;

    @SerializedName("ItemName")
    private String itemName;

    @SerializedName("ItemParty")
    private String itemParty;

    @SerializedName("ItemImage")
    private String itemImage;

    @SerializedName("ItemUserId")
    private String itemUserId;

  }

  /**
   * The type Items.
   */
  @Getter
  @Setter
  public static class Items implements Serializable {
    private static final long serialVersionUID = -5696099236344075582L;

    @SerializedName("Item")
    private List<Item> item;

  }

  /**
   * The type Item.
   */
  @Getter
  @Setter
  public static class Item implements Serializable {
    private static final long serialVersionUID = -5696099236344075582L;

    @SerializedName("ItemName")
    private String itemName;

    @SerializedName("ItemParty")
    private String itemParty;

    @SerializedName("ItemImage")
    private String itemImage;

    @SerializedName("ItemUserId")
    private String itemUserId;

    @SerializedName("ItemSpeech")
    private String itemSpeech;

    @SerializedName("ItemStatus")
    private Integer itemStatus;

    @SerializedName("ItemOpTime")
    private Long itemOpTime;

  }

  /**
   * From json wx cp open approval data.
   *
   * @param json the json
   * @return the wx cp open approval data
   */
  public static WxCpOpenApprovalData fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpOpenApprovalData.class);
  }

  /**
   * To json string.
   *
   * @return the string
   */
  public String toJson() {
    return WxCpGsonBuilder.create().toJson(this);
  }

}
