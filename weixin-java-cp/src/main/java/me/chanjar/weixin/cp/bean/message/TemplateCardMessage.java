package me.chanjar.weixin.cp.bean.message;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TemplateCardMessage implements Serializable {
  private static final long serialVersionUID = 8833792280163704239L;

  @JsonProperty("userids")
  private List<String> userids;
  @JsonProperty("partyids")
  private List<Integer> partyids;
  @JsonProperty("tagids")
  private List<Integer> tagids;
  @JsonProperty("atall")
  private Integer atall;
  @JsonProperty("agentid")
  private Integer agentid;
  @JsonProperty("response_code")
  private String responseCode;
  @JsonProperty("enable_id_trans")
  private Integer enableIdTrans;
  @JsonProperty("template_card")
  private TemplateCardDTO templateCard;

  @NoArgsConstructor
  @Data
  public static class TemplateCardDTO {
    @JsonProperty("card_type")
    private String cardType;
    @JsonProperty("source")
    private SourceDTO source;
    @JsonProperty("main_title")
    private MainTitleDTO mainTitle;
    @JsonProperty("select_list")
    private List<SelectListDTO> selectList;
    @JsonProperty("submit_button")
    private SubmitButtonDTO submitButton;
    @JsonProperty("replace_text")
    private String replaceText;

    @JsonProperty("checkbox")
    private CheckboxDTO checkbox;


    @JsonProperty("action_menu")
    private ActionMenuDTO actionMenu;
    @JsonProperty("quote_area")
    private QuoteAreaDTO quoteArea;
    @JsonProperty("sub_title_text")
    private String subTitleText;
    @JsonProperty("horizontal_content_list")
    private List<HorizontalContentListDTO> horizontalContentList;
    @JsonProperty("card_action")
    private CardActionDTO cardAction;
    @JsonProperty("button_selection")
    private ButtonSelectionDTO buttonSelection;
    @JsonProperty("button_list")
    private List<ButtonListDTO> buttonList;

    @JsonProperty("image_text_area")
    private ImageTextAreaDTO imageTextArea;
    @JsonProperty("card_image")
    private CardImageDTO cardImage;
    @JsonProperty("vertical_content_list")
    private List<MainTitleDTO> verticalContentList;
    @JsonProperty("jump_list")
    private List<JumpListDTO> jumpList;


    @NoArgsConstructor
    @Data
    public static class SourceDTO {
      @JsonProperty("icon_url")
      private String iconUrl;
      @JsonProperty("desc")
      private String desc;
      @JsonProperty("desc_color")
      private Integer descColor;
    }

    @NoArgsConstructor
    @Data
    public static class ActionMenuDTO {
      @JsonProperty("desc")
      private String desc;
      @JsonProperty("action_list")
      private List<SubmitButtonDTO> actionList;
    }

    @NoArgsConstructor
    @Data
    public static class QuoteAreaDTO {
      @JsonProperty("type")
      private Integer type;
      @JsonProperty("url")
      private String url;
      @JsonProperty("title")
      private String title;
      @JsonProperty("quote_text")
      private String quoteText;
    }

    @NoArgsConstructor
    @Data
    public static class CardActionDTO {
      @JsonProperty("type")
      private Integer type;
      @JsonProperty("url")
      private String url;
      @JsonProperty("appid")
      private String appid;
      @JsonProperty("pagepath")
      private String pagepath;
    }

    @NoArgsConstructor
    @Data
    public static class ButtonSelectionDTO {
      @JsonProperty("question_key")
      private String questionKey;
      @JsonProperty("title")
      private String title;
      @JsonProperty("option_list")
      private List<SelectListDTO.OptionListDTO> optionList;
      @JsonProperty("selected_id")
      private String selectedId;
    }

    @NoArgsConstructor
    @Data
    public static class HorizontalContentListDTO {
      @JsonProperty("keyname")
      private String keyname;
      @JsonProperty("value")
      private String value;
      @JsonProperty("type")
      private Integer type;
      @JsonProperty("url")
      private String url;
      @JsonProperty("media_id")
      private String mediaId;
      @JsonProperty("userid")
      private String userid;
    }

    @NoArgsConstructor
    @Data
    public static class ButtonListDTO {
      @JsonProperty("text")
      private String text;
      @JsonProperty("style")
      private Integer style;
      @JsonProperty("key")
      private String key;
    }


    @NoArgsConstructor
    @Data
    public static class CheckboxDTO {
      @JsonProperty("question_key")
      private String questionKey;
      @JsonProperty("option_list")
      private List<OptionListDTO> optionList;
      @JsonProperty("disable")
      private Boolean disable;
      @JsonProperty("mode")
      private Integer mode;

      @NoArgsConstructor
      @Data
      public static class OptionListDTO {
        @JsonProperty("id")
        private String id;
        @JsonProperty("text")
        private String text;
        @JsonProperty("is_checked")
        private Boolean isChecked;
      }

    }

    @NoArgsConstructor
    @Data
    public static class MainTitleDTO {
      @JsonProperty("title")
      private String title;
      @JsonProperty("desc")
      private String desc;
    }

    @NoArgsConstructor
    @Data
    public static class SubmitButtonDTO {
      @JsonProperty("text")
      private String text;
      @JsonProperty("key")
      private String key;
    }

    @NoArgsConstructor
    @Data
    public static class SelectListDTO {
      @JsonProperty("question_key")
      private String questionKey;
      @JsonProperty("title")
      private String title;
      @JsonProperty("selected_id")
      private String selectedId;
      @JsonProperty("disable")
      private Boolean disable;
      @JsonProperty("option_list")
      private List<OptionListDTO> optionList;

      @NoArgsConstructor
      @Data
      public static class OptionListDTO {
        @JsonProperty("id")
        private String id;
        @JsonProperty("text")
        private String text;
      }
    }

    @NoArgsConstructor
    @Data
    public static class ImageTextAreaDTO {
      @JsonProperty("type")
      private Integer type;
      @JsonProperty("url")
      private String url;
      @JsonProperty("title")
      private String title;
      @JsonProperty("desc")
      private String desc;
      @JsonProperty("image_url")
      private String imageUrl;
    }

    @NoArgsConstructor
    @Data
    public static class CardImageDTO {
      @JsonProperty("url")
      private String url;
      @JsonProperty("aspect_ratio")
      private Double aspectRatio;
    }

    @NoArgsConstructor
    @Data
    public static class JumpListDTO {
      @JsonProperty("type")
      private Integer type;
      @JsonProperty("title")
      private String title;
      @JsonProperty("url")
      private String url;
      @JsonProperty("appid")
      private String appid;
      @JsonProperty("pagepath")
      private String pagepath;
    }


  }

}
