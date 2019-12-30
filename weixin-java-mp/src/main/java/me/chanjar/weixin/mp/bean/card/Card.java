package me.chanjar.weixin.mp.bean.card;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import me.chanjar.weixin.mp.util.json.WxMpGsonBuilder;

import java.io.Serializable;

/**
 * .
 * @author leeis
 * @date 2018/12/29
 */
@Data
public class Card implements Serializable {
  private static final long serialVersionUID = -3697110761983756780L;

  /**
   * 基本信息.
   */
  @SerializedName("base_info")
  private BaseInfo baseInfo;

  /**
   * 创建优惠券特有的高级字段.
   */
  @SerializedName("advanced_info")
  private AdvancedInfo advancedInfo;

}
