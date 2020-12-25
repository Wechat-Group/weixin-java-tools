package me.chanjar.weixin.qidian.bean.card;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import me.chanjar.weixin.qidian.util.json.WxMpGsonBuilder;
//子对象列表

/**
 * 使用门槛
 * @author yuanqixun
 * date:2018-08-25 00:35
 */
@Data
public class UseCondition implements Serializable {

  /**
   * 指定可用的商品类目,仅用于代金券类型 ，填入后将在券面拼写适用于xxx
   */
  @SerializedName("accept_category")
  private String acceptCategory;

  /**
   * 指定不可用的商品类目,仅用于代金券类型 ，填入后将在券面拼写不适用于xxxx
   */
  @SerializedName("reject_category")
  private String rejectCategory;

  /**
   * 满减门槛字段,可用于兑换券和代金券 ，填入后将在全面拼写消费满xx元可用
   */
  @SerializedName("least_cost")
  private Integer leastCost;

  /**
   * 购买xx可用类型门槛,仅用于兑换 ，填入后自动拼写购买xxx可用
   */
  @SerializedName("object_use_for")
  private String objectUseFor;

  /**
   * 不可以与其他类型共享门槛,填写false时系统将在使用须知里 拼写“不可与其他优惠共享”， 填写true时系统将在使用须知里 拼写“可与其他优惠共享”， 默认为true
   */
  @SerializedName("can_use_with_other_discount")
  private boolean canUseWithOtherDiscount;

  @Override
  public String toString() {
    return WxMpGsonBuilder.create().toJson(this);
  }
}
