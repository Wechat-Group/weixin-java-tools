package me.chanjar.weixin.mp.bean.card.membercard;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import me.chanjar.weixin.mp.bean.card.enums.CardRichFieldType;
import me.chanjar.weixin.mp.util.json.WxMpGsonBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * 富文本字段.
 *
 * @author yuanqixun
 * @date 2018-08-30
 */
@Data
public class MemberCardUserFormRichField {

  /**
   * 富文本类型
   */
  @SerializedName("type")
  private CardRichFieldType type;

  @SerializedName("name")
  private String name;

  @SerializedName("values")
  private List<String> valueList;

  public void add(String value) {
    if (valueList == null) {
      valueList = new ArrayList<>();
    }
    valueList.add(value);
  }

  @Override
  public String toString() {
    return WxMpGsonBuilder.create().toJson(this);
  }
}
