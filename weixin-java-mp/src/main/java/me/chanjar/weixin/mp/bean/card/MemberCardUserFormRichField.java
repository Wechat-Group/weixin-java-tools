package me.chanjar.weixin.mp.bean.card;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import me.chanjar.weixin.mp.bean.card.enums.CardRichFieldType;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.ArrayList;
import java.util.List;

/**
 * 富文本字段
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
    if (valueList == null)
      valueList = new ArrayList<String>();
    valueList.add(value);
  }

  public String toString() {
    return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
  }
}
