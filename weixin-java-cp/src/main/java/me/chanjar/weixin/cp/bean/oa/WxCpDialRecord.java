package me.chanjar.weixin.cp.bean.oa;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 公费电话拨打记录.
 *
 * @author Element
 * @date 2019-04-06 15:38
 */
@Data
public class WxCpDialRecord implements Serializable {

  private static final long serialVersionUID = 4178886812949929116L;
  @SerializedName("call_time")
  private Long callTime;

  /**
   * 总通话时长，单位为分钟
   */
  @SerializedName("total_duration")
  private Integer totalDuration;

  /**
   * 通话类型，1-单人通话 2-多人通话
   */
  @SerializedName("call_type")
  private Integer callType;

  private Caller caller;

  private List<Callee> callee;

  /**
   * 主叫信息
   */
  @Data
  public static class Caller implements Serializable {

    private static final long serialVersionUID = 4792200404338145607L;

    @SerializedName("userid")
    private String userId;

    private Integer duration;
  }

  /**
   * 被叫信息
   */
  @Data
  public static class Callee implements Serializable {

    private static final long serialVersionUID = 2390963671336179550L;

    /**
     * 被叫用户的userid，当被叫用户为企业内用户时返回
     */
    @SerializedName("userid")
    private String userId;

    /**
     * 被叫用户的号码，当被叫用户为外部用户时返回
     */
    private String phone;

    private Integer duration;
  }
}
