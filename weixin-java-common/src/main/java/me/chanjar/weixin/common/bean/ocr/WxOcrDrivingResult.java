package me.chanjar.weixin.common.bean.ocr;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import me.chanjar.weixin.common.util.json.WxGsonBuilder;

import java.io.Serializable;

/**
 * @author Theo Nie
 */
@Data
public class WxOcrDrivingResult implements Serializable {
  private static final long serialVersionUID = -7477484374200211303L;

  /**
   * 车牌号码
   */
  @SerializedName("plate_num")
  private String plateNum;
  /**
   * 车辆类型
   */
  @SerializedName("vehicle_type")
  private String vehicleType;
  /**
   * 所有人
   */
  @SerializedName("owner")
  private String owner;
  /**
   * 住址
   */
  @SerializedName("addr")
  private String addr;
  /**
   * 使用性质
   */
  @SerializedName("use_character")
  private String useCharacter;
  /**
   * 品牌型号
   */
  @SerializedName("model")
  private String model;
  /**
   * 车辆识别代码
   */
  @SerializedName("vin")
  private String vin;
  /**
   * 发动机号码
   */
  @SerializedName("engine_num")
  private String engineNum;
  /**
   * 注册日期
   */
  @SerializedName("register_date")
  private String registerDate;
  /**
   * 发证日期
   */
  @SerializedName("issue_date")
  private String issueDate;
  /**
   * 车牌号码
   */
  @SerializedName("plate_num_b")
  private String plateNumB;
  /**
   * 号牌
   */
  @SerializedName("record")
  private String record;
  /**
   * 核定载人数
   */
  @SerializedName("passengers_num")
  private String passengersNum;
  /**
   * 总质量
   */
  @SerializedName("total_quality")
  private String totalQuality;
  /**
   * 整备质量
   */
  @SerializedName("prepare_quality")
  private String prepareQuality;
  /**
   * 外廓尺寸
   */
  @SerializedName("overall_size")
  private String overallSize;
  /**
   * 卡片正面位置（检测到卡片正面才会返回）
   */
  @SerializedName("card_position_front")
  private CardPosition cardPositionFront;
  /**
   * 卡片反面位置（检测到卡片反面才会返回）
   */
  @SerializedName("card_position_back")
  private CardPosition cardPositionBack;
  /**
   * 图片大小
   */
  @SerializedName("img_size")
  private WxOcrImgSize imgSize;

  @Data
  public static class CardPosition implements Serializable {
    private static final long serialVersionUID = 2884515165228160517L;

    @SerializedName("pos")
    private WxOcrPos pos;

    @Override
    public String toString() {
      return WxGsonBuilder.create().toJson(this);
    }
  }

  public static WxOcrDrivingResult fromJson(String json) {
    return WxGsonBuilder.create().fromJson(json, WxOcrDrivingResult.class);
  }

  @Override
  public String toString() {
    return WxGsonBuilder.create().toJson(this);
  }
}
