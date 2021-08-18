package me.chanjar.weixin.cp.bean.message.builder;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author caiqy
 */
@Getter
@Setter
public class TemplateCardImageMessage extends AbstractTemplateCardMessage {

  private static final long serialVersionUID = -8716310791929956591L;

  @SerializedName("horizontal_content_list")
  private List<HorizontalContentListItem> horizontalContentList;

  @SerializedName("vertical_content_list")
  private List<VerticalContentListItem> verticalContentList;

  @SerializedName("card_action")
  private CardAction cardAction;

  @SerializedName("card_image")
  private CardImage cardImage;

  @SerializedName("jump_list")
  private List<JumpListItem> jumpList;

  public TemplateCardImageMessage(String cardType, MainTitle mainTitle, Source source, List<HorizontalContentListItem> horizontalContentList, List<VerticalContentListItem> verticalContentList, CardAction cardAction, CardImage cardImage, List<JumpListItem> jumpList) {
    super(cardType, mainTitle, source);
    this.horizontalContentList = horizontalContentList;
    this.verticalContentList = verticalContentList;
    this.cardAction = cardAction;
    this.cardImage = cardImage;
    this.jumpList = jumpList;
  }
}
