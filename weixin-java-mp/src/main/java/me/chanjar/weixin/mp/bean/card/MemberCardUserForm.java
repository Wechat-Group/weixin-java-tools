package me.chanjar.weixin.mp.bean.card;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import me.chanjar.weixin.mp.bean.card.enums.CardWechatFieldType;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 用户表单对象
 *
 * @author yuanqixun
 * @date 2018-08-30
 */
@Data
public class MemberCardUserForm implements Serializable {

  /**
   * 当前结构（required_form或者optional_form ）内的字段是否允许用户激活后再次修改，
   * 商户设置为true 时，需要接收相应事件通知处理修改事件
   */
  @SerializedName("can_modify")
  private boolean canModify;

  /**
   * 富文本类型字段列表
   */
  @SerializedName("rich_field_list")
  List<MemberCardUserFormRichField> richFieldList;

  /**
   * 文本选项类型列表
   */
  @SerializedName("custom_field_list")
  private List<String> customFieldList;


  /**
   * 微信格式化的选项类型
   */
  @SerializedName("common_field_id_list")
  private List<String> wechatFieldIdList;

  /**
   * 添加富文本类型字段
   *
   * @param fieldType
   */
  public void addRichField(MemberCardUserFormRichField field) {
    if (field == null)
      return;
    if (richFieldList == null)
      richFieldList = new ArrayList<>();
    richFieldList.add(field);
  }

  /**
   * 添加微信选项类型字段
   *
   * @param fieldType
   */
  public void addWechatField(CardWechatFieldType fieldType) {
    if (fieldType == null)
      return;
    if (wechatFieldIdList == null)
      wechatFieldIdList = new ArrayList<>();
    wechatFieldIdList.add(fieldType.name());
  }

  /**
   * 添加文本类型字段
   *
   * @param fieldType
   */
  public void addCustomField(String field) {
    if (StringUtils.isBlank(field))
      return;
    if (customFieldList == null)
      customFieldList = new ArrayList<>();
    customFieldList.add(field);
  }

  public String toString() {
    return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
  }

}
