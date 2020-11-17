package me.chanjar.weixin.cp.bean.oa.applydata;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * @author element
 */
@Data
@Accessors(chain = true)
public class ApplyDataContent implements Serializable {
  private static final long serialVersionUID = 8456821731930526935L;
  /**
   * 控件类型：Text-文本；Textarea-多行文本；Number-数字；Money-金额；Date-日期/日期+时间；
   * Selector-单选/多选；；Contact-成员/部门；Tips-说明文字；File-附件；Table-明细；
   */
  private String control;

  /**
   * 控件id：控件的唯一id，可通过“获取审批模板详情”接口获取
   */
  private String id;

  @SerializedName("title")
  private List<ContentTitle> titles;

  /**
   * 控件值 ，需在此为申请人在各个控件中填写内容不同控件有不同的赋值参数，具体说明详见附录。模板配置的控件属性为必填时，对应value值需要有值。
   */
  private ContentValue value;
}
