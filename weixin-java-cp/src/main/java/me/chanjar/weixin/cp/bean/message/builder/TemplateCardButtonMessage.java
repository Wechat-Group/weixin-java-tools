package me.chanjar.weixin.cp.bean.message.builder;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * The type Template card button message.
 */
@Getter
@Setter
public class TemplateCardButtonMessage extends AbstractTemplateCardMessage {

  private static final long serialVersionUID = 2897541858868286162L;

  /**
   * 任务id，同一个应用任务id不能重复，只能由数字、字母和“_-@”组成，最长128字节
   */
  @SerializedName("task_id")
  private String taskId;

  /**
   * 二级普通文本，建议不超过160个字
   */
  @SerializedName("sub_title_text")
  private String subTitleText;

  @SerializedName("button_list")
  private List<ButtonItem> buttonList;

  /**
   * Instantiates a new Template card button message.
   *
   * @param cardType     the card type
   * @param mainTitle    the main title
   * @param source       the source
   * @param taskId       the task id
   * @param subTitleText the sub title text
   * @param buttonList   the button list
   */
  public TemplateCardButtonMessage(String cardType, MainTitle mainTitle, Source source, String taskId, String subTitleText, List<ButtonItem> buttonList) {
    super(cardType, mainTitle, source);
    this.taskId = taskId;
    this.subTitleText = subTitleText;
    this.buttonList = buttonList;
  }
}
