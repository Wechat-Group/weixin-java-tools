package me.chanjar.weixin.cp.bean.message.builder;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * The type Template card text message.
 */
@Getter
@Setter
public class TemplateCardTextMessage extends AbstractTemplateCardMessage {

  @SerializedName("horizontal_content_list")
  private List<HorizontalContentListItem> horizontalContentList;

  @SerializedName("card_action")
  private CardAction cardAction;

  @SerializedName("sub_title_text")
  private String subTitleText;

  @SerializedName("emphasis_content")
  private EmphasisContent emphasisContent;

  @SerializedName("jump_list")
  private List<JumpListItem> jumpList;

  /**
   * Instantiates a new Template card text message.
   *
   * @param cardType              the card type
   * @param mainTitle             the main title
   * @param source                the source
   * @param horizontalContentList the horizontal content list
   * @param cardAction            the card action
   * @param subTitleText          the sub title text
   * @param emphasisContent       the emphasis content
   * @param jumpList              the jump list
   */
  public TemplateCardTextMessage(String cardType, MainTitle mainTitle, Source source, List<HorizontalContentListItem> horizontalContentList, CardAction cardAction, String subTitleText, EmphasisContent emphasisContent, List<JumpListItem> jumpList) {
    super(cardType, mainTitle, source);
    this.horizontalContentList = horizontalContentList;
    this.cardAction = cardAction;
    this.subTitleText = subTitleText;
    this.emphasisContent = emphasisContent;
    this.jumpList = jumpList;
  }
}
