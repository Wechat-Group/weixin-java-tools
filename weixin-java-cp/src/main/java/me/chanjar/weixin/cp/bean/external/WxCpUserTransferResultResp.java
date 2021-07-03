package me.chanjar.weixin.cp.bean.external;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;
import me.chanjar.weixin.cp.bean.WxCpBaseResp;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.io.Serializable;

/**
 * 在职成员的客户转接情况
 * @author pg
 * @date 2021年6月21日
 */
@Getter
@Setter
public class WxCpUserTransferResultResp extends WxCpBaseResp {
  private static final long serialVersionUID = 6897979567174991786L;
  @SerializedName("next_cursor")
  private String nextCursor;

  public static WxCpUserTransferResultResp fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpUserTransferResultResp.class);
  }

  public String toJson() {
    return WxCpGsonBuilder.create().toJson(this);
  }

  /**
   * 客户转接结果实体
   */
  @Getter
  @Setter
  public static class TransferResult implements Serializable {
    private static final long serialVersionUID = 2847784363733118393L;

    /**
     * 客户的external_userid
     */
    @SerializedName("external_userid")
    private String externalUserid;
    /**
     * 接替状态， 1-接替完毕 2-等待接替 3-客户拒绝 4-接替成员客户达到上限 5-无接替记录
     */
    private STATUS status;
    /**
     * 接替客户的时间，如果是等待接替状态，则为未来的自动接替时间
     */
    @SerializedName("takeover_time")
    private Long takeOverTime;

    public static WxCpUserTransferResultResp.TransferResult fromJson(String json) {
      return WxCpGsonBuilder.create().fromJson(json, WxCpUserTransferResultResp.TransferResult.class);
    }

    public String toJson() {
      return WxCpGsonBuilder.create().toJson(this);
    }
  }

  public enum STATUS {

    /**
     * 接替完毕
     */
    @SerializedName("1")
    COMPLETE,

    /**
     * 等待接替
     */
    @SerializedName("2")
    WAITING,
    /**
     * 客户拒绝
     */
    @SerializedName("3")
    REFUSED,
    /**
     * 接替成员客户达到上限
     */
    @SerializedName("4")
    LIMIT,
    /**
     * 无接替记录
     */
    @SerializedName("5")
    NORECORD
  }
}
