package me.chanjar.weixin.mp.bean.datacube;

import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;
import lombok.Data;
import lombok.EqualsAndHashCode;
import me.chanjar.weixin.mp.util.json.WxMpGsonBuilder;

import java.util.List;

/**
 * 消息分析数据接口返回结果对象.
 * Created by Binary Wang on 2016/8/29.
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class WxDataCubeMsgResult extends WxDataCubeBaseResult {
  private static final long serialVersionUID = 6932121822150573659L;

  /**
   * ref_hour.
   * 数据的小时，包括从000到2300，分别代表的是[000,100)到[2300,2400)，即每日的第1小时和最后1小时
   */
  @SerializedName("ref_hour")
  private Integer refHour;

  /**
   * msg_type.
   * 消息类型，代表含义如下：1代表文字 2代表图片 3代表语音 4代表视频 6代表第三方应用消息（链接消息）
   */
  @SerializedName("msg_type")
  private Integer msgType;

  /**
   * msg_user.
   * 上行发送了（向公众号发送了）消息的用户数
   */
  @SerializedName("msg_user")
  private Integer msgUser;

  /**
   * msg_count.
   * 上行发送了消息的消息总数
   */
  @SerializedName("msg_count")
  private Integer msgCount;

  /**
   * count_interval.
   * 当日发送消息量分布的区间，0代表 “0”，1代表“1-5”，2代表“6-10”，3代表“10次以上”
   */
  @SerializedName("count_interval")
  private Integer countInterval;

  /**
   * int_page_read_count
   * 图文页的阅读次数
   */
  @SerializedName("int_page_read_count")
  private Integer intPageReadCount;

  /**
   * ori_page_read_user.
   * 原文页（点击图文页“阅读原文”进入的页面）的阅读人数，无原文页时此处数据为0
   */
  @SerializedName("ori_page_read_user")
  private Integer oriPageReadUser;

  public static List<WxDataCubeMsgResult> fromJson(String json) {
    return WxMpGsonBuilder.INSTANCE.create().fromJson(
      JSON_PARSER.parse(json).getAsJsonObject().get("list"),
      new TypeToken<List<WxDataCubeMsgResult>>() {
      }.getType());
  }

}
