package me.chanjar.weixin.cp.bean.oa;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import me.chanjar.weixin.cp.bean.WxCpBaseResp;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * 获取审批数据（旧）.
 *
 * @author Wang_Wong
 */
@Data
public class WxCpGetApprovalData extends WxCpBaseResp implements Serializable {
  private static final long serialVersionUID = 7387181805254287159L;

  @SerializedName("count")
  private Integer count;

  @SerializedName("total")
  private Integer total;

  @SerializedName("next_spnum")
  private Long nextSpNum;

  @SerializedName("data")
  private List<ApprovalData> data;

  /**
   * The type Approval data.
   */
  @Getter
  @Setter
  public static class ApprovalData implements Serializable {
    private static final long serialVersionUID = -5696099236344075582L;

    @SerializedName("spname")
    private String spName;

    @SerializedName("apply_name")
    private String applyName;

    @SerializedName("apply_org")
    private String applyOrg;

    @SerializedName("approval_name")
    private List<String> approvalName;

    @SerializedName("notify_name")
    private List<String> notifyName;

    @SerializedName("mediaids")
    private List<String> mediaIds;

    @SerializedName("sp_status")
    private Integer spStatus;

    @SerializedName("sp_num")
    private Long spNum;

    @SerializedName("apply_time")
    private Long applyTime;

    @SerializedName("apply_user_id")
    private String applyUserId;

    @SerializedName("expense")
    private Expense expense;

    @SerializedName("comm")
    private Comm comm;

  }

  /**
   * The type Expense.
   */
  @Getter
  @Setter
  public static class Expense implements Serializable {
    private static final long serialVersionUID = -5696099236344075582L;

    @SerializedName("expense_type")
    private Integer expenseType;

    @SerializedName("reason")
    private String reason;

    @SerializedName("item")
    private List<Item> item;

  }

  /**
   * The type Comm.
   */
  @Getter
  @Setter
  public static class Comm implements Serializable {
    private static final long serialVersionUID = -5696099236344075582L;

    @SerializedName("apply_data")
    private String applyData;

  }

  /**
   * The type Item.
   */
  @Getter
  @Setter
  public static class Item implements Serializable {
    private static final long serialVersionUID = -5696099236344075582L;

    @SerializedName("expenseitem_type")
    private Integer expenseItemType;

    @SerializedName("time")
    private Long time;

    @SerializedName("sums")
    private Integer sums;

    @SerializedName("reason")
    private String reason;

  }

  /**
   * From json wx cp get approval data.
   *
   * @param json the json
   * @return the wx cp get approval data
   */
  public static WxCpGetApprovalData fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpGetApprovalData.class);
  }

  public String toJson() {
    return WxCpGsonBuilder.create().toJson(this);
  }

}
