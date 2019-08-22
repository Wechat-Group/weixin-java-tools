package me.chanjar.weixin.open.bean.result;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import me.chanjar.weixin.open.util.json.WxOpenGsonBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * 微信开放平台小程序当前分阶段发布详情
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class WxOpenMaGrayReleasePlanResult extends WxOpenResult {

  private static final long serialVersionUID = 8417849861393170728L;

  @SerializedName("gray_release_plan")
  private GrayReleasePlanBean grayReleasePlan;


  @Data
  public static class GrayReleasePlanBean {

    /**
     * 0:初始状态 1:执行中 2:暂停中 3:执行完毕 4:被删除
     */
    @SerializedName("status")
    private Integer status;

    /**
     * 创建时间
     */
    @SerializedName("create_timestamp")
    private Long createTimestamp;


    /**
     * 灰度百分比
     */
    @SerializedName("gray_percentage")
    private Integer grayPercentage;
  }


  @Override
  public String toString() {
    return WxOpenGsonBuilder.create().toJson(this);
  }


}
