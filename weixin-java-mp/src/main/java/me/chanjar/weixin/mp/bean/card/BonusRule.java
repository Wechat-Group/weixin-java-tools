package me.chanjar.weixin.mp.bean.card;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import me.chanjar.weixin.mp.util.json.WxMpGsonBuilder;

/**
 * 积分规则.
 *
 * @author yuanqixun
 * date:2018-08-25 00:33
 */
@Data
public class BonusRule implements Serializable {
  private static final long serialVersionUID = -8698218402074475078L;

  /**
   * 消费金额,以分为单位.
   */
  @SerializedName("cost_money_unit")
  private Integer costMoneyUnit;

  /**
   * 对应增加的积分.
   */
  @SerializedName("increase_bonus")
  private Integer increaseBonus;

  /**
   * 用户单次可获取的积分上限.
   */
  @SerializedName("max_increase_bonus")
  private Integer maxIncreaseBonus;

  /**
   * 初始设置积分.
   */
  @SerializedName("init_increase_bonus")
  private Integer initIncreaseBonus;

  /**
   * 每使用积分.
   */
  @SerializedName("cost_bonus_unit")
  private Integer costBonusUnit;

  /**
   * 抵扣xx元,这里以分为单位）.
   */
  @SerializedName("reduce_money")
  private Integer reduceMoney;

  /**
   * 抵扣条件,满xx元（这里以分为单位）可用.
   */
  @SerializedName("least_money_to_use_bonus")
  private Integer leastMoneyToUseBonus;

  /**
   * 抵扣条件,单笔最多使用xx积分.
   */
  @SerializedName("max_reduce_bonus")
  private Integer maxReduceBonus;

  @Override
  public String toString() {
    return WxMpGsonBuilder.create().toJson(this);
  }
}
