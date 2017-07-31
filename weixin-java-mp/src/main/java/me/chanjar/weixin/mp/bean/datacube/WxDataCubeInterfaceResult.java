package me.chanjar.weixin.mp.bean.datacube;

import com.google.gson.JsonParser;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;
import me.chanjar.weixin.mp.util.json.WxMpGsonBuilder;

import java.util.List;

/**
 * 接口分析数据接口返回结果对象
 *
 * @author <a href="https://github.com/binarywang">binarywang(Binary Wang)</a>
 *         Created by Binary Wang on 2016/8/30.
 */
public class WxDataCubeInterfaceResult extends WxDataCubeBaseResult {

  private static final JsonParser JSON_PARSER = new JsonParser();

  /**
   * ref_hour
   * 数据的小时，包括从000到2300，分别代表的是[000,100)到[2300,2400)，即每日的第1小时和最后1小时
   */
  @SerializedName("ref_hour")
  private Integer refHour;

  /**
   * callback_count
   * 通过服务器配置地址获得消息后，被动回复用户消息的次数
   */
  @SerializedName("callback_count")
  private Integer callbackCount;

  /**
   * fail_count
   * 上述动作的失败次数
   */
  @SerializedName("fail_count")
  private Integer failCount;

  /**
   * total_time_cost
   * 总耗时，除以callback_count即为平均耗时
   */
  @SerializedName("total_time_cost")
  private Integer totalTimeCost;

  /**
   * max_time_cost
   * 最大耗时
   */
  @SerializedName("max_time_cost")
  private Integer maxTimeCost;

  public static List<WxDataCubeInterfaceResult> fromJson(String json) {
    return WxMpGsonBuilder.INSTANCE.create().fromJson(
      JSON_PARSER.parse(json).getAsJsonObject().get("list"),
      new TypeToken<List<WxDataCubeInterfaceResult>>() {
      }.getType());
  }

  public Integer getRefHour() {
    return this.refHour;
  }

  public void setRefHour(Integer refHour) {
    this.refHour = refHour;
  }

  public Integer getCallbackCount() {
    return this.callbackCount;
  }

  public void setCallbackCount(Integer callbackCount) {
    this.callbackCount = callbackCount;
  }

  public Integer getFailCount() {
    return this.failCount;
  }

  public void setFailCount(Integer failCount) {
    this.failCount = failCount;
  }

  public Integer getTotalTimeCost() {
    return this.totalTimeCost;
  }

  public void setTotalTimeCost(Integer totalTimeCost) {
    this.totalTimeCost = totalTimeCost;
  }

  public Integer getMaxTimeCost() {
    return this.maxTimeCost;
  }

  public void setMaxTimeCost(Integer maxTimeCost) {
    this.maxTimeCost = maxTimeCost;
  }

}
