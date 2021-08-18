package me.chanjar.weixin.cp.bean.message.builder;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TemplateCardButtonMessage extends AbstractTemplateCardMessage {

  private static final long serialVersionUID = 2897541858868286162L;

  @SerializedName("task_id")
  private String taskId;

  @SerializedName("sub_title_text")
  private String subTitleText;

  @SerializedName("button_list")
  private List<ButtonItem> buttonList;

  public TemplateCardButtonMessage(String cardType, MainTitle mainTitle, Source source, String taskId, String subTitleText, List<ButtonItem> buttonList) {
    super(cardType, mainTitle, source);
    this.taskId = taskId;
    this.subTitleText = subTitleText;
    this.buttonList = buttonList;
  }
}
