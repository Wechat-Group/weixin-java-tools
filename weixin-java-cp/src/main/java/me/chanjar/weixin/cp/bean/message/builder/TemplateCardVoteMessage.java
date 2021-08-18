package me.chanjar.weixin.cp.bean.message.builder;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TemplateCardVoteMessage extends AbstractTemplateCardMessage {

  private static final long serialVersionUID = -5479368307619279988L;

  @SerializedName("task_id")
  private String taskId;

  @SerializedName("submit_button")
  private SubmitButton submitButton;

  @SerializedName("checkbox")
  private Checkbox checkbox;

  public TemplateCardVoteMessage(String cardType, MainTitle mainTitle, Source source, String taskId, SubmitButton submitButton, Checkbox checkbox) {
    super(cardType, mainTitle, source);
    this.taskId = taskId;
    this.submitButton = submitButton;
    this.checkbox = checkbox;
  }
}
