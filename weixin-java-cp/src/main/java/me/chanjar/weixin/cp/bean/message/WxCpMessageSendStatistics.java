package me.chanjar.weixin.cp.bean.message;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.util.List;

/**
 * 应用消息发送统计信息.
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 * @date 2020-09-13
 */
@Data
public class WxCpMessageSendStatistics {
  public static WxCpMessageSendStatistics fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpMessageSendStatistics.class);
  }

  private List<StatisticItem> statistics;

  @Data
  public static class StatisticItem {
    /**
     * 应用名
     */
    @SerializedName("app_name")
    private String appName;

    /**
     * 应用id
     */
    @SerializedName("agentid")
    private Integer agentId;

    /**
     * 发消息成功人次
     */
    @SerializedName("count")
    private Integer count;
  }
}
