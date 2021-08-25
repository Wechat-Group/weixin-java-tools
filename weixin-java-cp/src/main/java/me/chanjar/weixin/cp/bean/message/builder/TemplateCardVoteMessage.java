package me.chanjar.weixin.cp.bean.message.builder;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

/**
 * The type Template card vote message.
 */
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

  /**
   * Instantiates a new Template card vote message.
   *
   * @param cardType     the card type
   * @param mainTitle    the main title
   * @param source       the source
   * @param taskId       the task id
   * @param submitButton the submit button
   * @param checkbox     the checkbox
   */
  public TemplateCardVoteMessage(String cardType, MainTitle mainTitle, Source source, String taskId, SubmitButton submitButton, Checkbox checkbox) {
    super(cardType, mainTitle, source);
    this.taskId = taskId;
    this.submitButton = submitButton;
    this.checkbox = checkbox;
  }
}
