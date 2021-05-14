package me.chanjar.weixin.mp.bean.guide;

import com.google.gson.JsonElement;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import me.chanjar.weixin.common.bean.ToJson;
import me.chanjar.weixin.common.util.json.WxGsonBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * 群发任务信息
 *
 * @author <a href="https://www.sacoc.cn">广州跨界-宋心成</a>
 * @date 2021/5/13/013
 */

@Data
public class WxMpGuideMassedInfo implements ToJson, Serializable {
  private static final long serialVersionUID = -6120573244255111822L;

  /**
   * 任务id
   */
  @SerializedName("task_id")
  private String taskId;

  /**
   * 顾问openid
   */
  @SerializedName("guide_openid")
  private String openid;

  /**
   * 任务创建时间
   */
  @SerializedName("create_time")
  private Long createTime;

  /**
   * 任务最后修改时间
   */
  @SerializedName("update_time")
  private Long updateTime;

  /**
   * 任务下发时间
   */
  @SerializedName("push_time")
  private Long pushTime;

  /**
   * 任务完成时间
   */
  @SerializedName("finish_time")
  private Long finishTime;

  /**
   * 任务名称
   */
  @SerializedName("task_name")
  private String taskName;

  /**
   * 任务备注
   */
  @SerializedName("task_remark")
  private String taskRemark;

  /**
   * 任务状态（1.任务未执行 2.已执行 3.执行完成 4.任务取消）
   */
  @SerializedName("task_status")
  private int taskStatus;

  /**
   * 素材
   */
  @SerializedName("material")
  private List<WxMpGuideMaterialInfo> material;

  /**
   * 客户列表
   */
  @SerializedName("buyer_info")
  private List<WxMpGuideMassedBuyerInfo> buyerInfos;

  @Override
  public String toJson() {
    return WxGsonBuilder.create().toJson(this);
  }

  public static <T> WxMpGuideMassedInfo fromJson(T json) {
    if (json instanceof String) {
      return WxGsonBuilder.create().fromJson((String) json, WxMpGuideMassedInfo.class);
    } else if (json instanceof JsonElement) {
      return WxGsonBuilder.create().fromJson((JsonElement) json, WxMpGuideMassedInfo.class);
    }
    return null;
  }
}
