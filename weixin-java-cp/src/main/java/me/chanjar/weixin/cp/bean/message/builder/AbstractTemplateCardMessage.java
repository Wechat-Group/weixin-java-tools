package me.chanjar.weixin.cp.bean.message.builder;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * The type Abstract template card message.
 *
 * @author caiqy
 */
@Data
public abstract class AbstractTemplateCardMessage implements Serializable {

  private static final long serialVersionUID = 5150218006511631535L;
  @SerializedName("card_type")
  private String cardType;

  @SerializedName("main_title")
  private MainTitle mainTitle;

  @SerializedName("source")
  private Source source;

  /**
   * Instantiates a new Abstract template card message.
   *
   * @param cardType  the card type
   * @param mainTitle the main title
   * @param source    the source
   */
  public AbstractTemplateCardMessage(String cardType, MainTitle mainTitle, Source source) {
    this.cardType = cardType;
    this.mainTitle = mainTitle;
    this.source = source;
  }

  /**
   * The type Main title.
   */
  @Data
  @AllArgsConstructor
  @NoArgsConstructor
  public static class MainTitle implements Serializable {

    private static final long serialVersionUID = -8179756248886337955L;
    /**
     * 一级标题，建议不超过40个字
     */
    @SerializedName("title")
    private String title;

    /**
     * 标题辅助信息，建议不超过40个字
     */
    @SerializedName("desc")
    private String desc;
  }

  /**
   * The type Source.
   */
  @Data
  @AllArgsConstructor
  @NoArgsConstructor
  public static class Source implements Serializable {

    private static final long serialVersionUID = -4816586086979786139L;
    /**
     * 来源图片的url
     */
    @SerializedName("icon_url")
    private String iconUrl;

    /**
     * 来源图片的描述，建议不超过20个字
     */
    @SerializedName("desc")
    private String desc;
  }

  /**
   * 二级标题+文本列表，该字段可为空数组，但有数据的话需确认对应字段是否必填，列表长度不超过6
   */
  @Data
  public static class HorizontalContentListItem implements Serializable {

    /**
     * The constant TYPE_URL.
     */
    public static final Integer TYPE_URL = 1;

    /**
     * The constant TYPE_FILE.
     */
    public static final Integer TYPE_FILE = 2;
    private static final long serialVersionUID = 1894089643965580296L;

    /**
     * 二级标题，建议不超过5个字
     */
    @SerializedName("keyname")
    private String keyName;

    /**
     * 链接类型，0或不填代表不是链接，1 代表跳转url，2 代表下载附件
     */
    @SerializedName("type")
    private int type;

    /**
     * 二级文本，如果horizontal_content_list.type是2，该字段代表文件名称（要包含文件类型），建议不超过40个字
     */
    @SerializedName("value")
    private String value;

    /**
     * 链接跳转的url，horizontal_content_list.type是1时必填
     */
    @SerializedName("url")
    private String url;

    /**
     * 附件的media_id，horizontal_content_list.type是2时必填
     */
    @SerializedName("media_id")
    private String mediaId;


    /**
     * 二级标题+文本列表，该字段可为空数组，但有数据的话需确认对应字段是否必填，列表长度不超过6
     *
     * @param keyName 二级标题，建议不超过5个字
     * @param type    链接类型，0或不填代表不是链接，1 代表跳转url，2 代表下载附件
     * @param value   二级文本，如果horizontal_content_list.type是2，该字段代表文件名称（要包含文件类型），建议不超过30个字
     * @param url     链接跳转的url，horizontal_content_list.type是1时必填
     * @param mediaId 附件的media_id，horizontal_content_list.type是2时必填
     */
    public HorizontalContentListItem(String keyName, int type, String value, String url, String mediaId) {
      this.keyName = keyName;
      this.type = type;
      this.value = value;
      this.url = url;
      this.mediaId = mediaId;
    }

    /**
     * Url horizontal content list item.
     *
     * @param keyName 链接类型，0或不填代表不是链接，1 代表跳转url，2 代表下载附件
     * @param value   二级文本，如果horizontal_content_list.type是2，该字段代表文件名称（要包含文件类型），建议不超过30个字
     * @param url     链接跳转的url，horizontal_content_list.type是1时必填
     * @return the horizontal content list item
     */
    public static HorizontalContentListItem url(String keyName, String value, String url) {
      return new HorizontalContentListItem(keyName, TYPE_URL, value, url, null);
    }

    /**
     * File horizontal content list item.
     *
     * @param keyName 链接类型，0或不填代表不是链接，1 代表跳转url，2 代表下载附件
     * @param value   二级文本，如果horizontal_content_list.type是2，该字段代表文件名称（要包含文件类型），建议不超过30个字
     * @param mediaId 附件的media_id，horizontal_content_list.type是2时必填
     * @return the horizontal content list item
     */
    public static HorizontalContentListItem file(String keyName, String value, String mediaId) {
      return new HorizontalContentListItem(keyName, TYPE_FILE, value, null, mediaId);
    }
  }


  /**
   * 跳转指引样式的列表，该字段可为空数组，但有数据的话需确认对应字段是否必填，列表长度不超过3
   */
  @Data
  public static class JumpListItem implements Serializable {

    /**
     * The constant TYPE_URL.
     */
    public static final Integer TYPE_URL = 1;

    /**
     * The constant TYPE_MINI_PROGRAM.
     */
    public static final Integer TYPE_MINI_PROGRAM = 2;
    private static final long serialVersionUID = 67644176867283592L;

    /**
     * 跳转链接类型，0或不填代表不是链接，1 代表跳转url，2 代表跳转小程序
     */
    @SerializedName("type")
    private int type;

    /**
     * 跳转链接样式的文案内容，建议不超过20个字
     */
    @SerializedName("title")
    private String title;

    /**
     * 跳转链接的url，jump_list.type是1时必填
     */
    @SerializedName("url")
    private String url;

    /**
     * 跳转链接的小程序的appid，jump_list.type是2时必填
     */
    @SerializedName("appid")
    private String appId;

    /**
     * 跳转链接的小程序的pagepath，jump_list.type是2时选填
     */
    @SerializedName("pagepath")
    private String pagePath;


    /**
     * Url jump list item.
     *
     * @param title 跳转链接样式的文案内容，建议不超过18个字
     * @param url   跳转链接的url，jump_list.type是1时必填
     * @return the jump list item
     */
    public static JumpListItem url(String title, String url) {
      return new JumpListItem(TYPE_URL, title, url, null, null);
    }

    /**
     * Mini program jump list item.
     *
     * @param title    the title
     * @param appId    跳转链接的小程序的appid，必须是与当前应用关联的小程序，jump_list.type是2时必填
     * @param pagePath 跳转链接的小程序的pagepath，jump_list.type是2时选填
     * @return the jump list item
     */
    public static JumpListItem miniProgram(String title, String appId, String pagePath) {
      return new JumpListItem(TYPE_MINI_PROGRAM, title, null, appId, pagePath);
    }

    /**
     * Instantiates a new Jump list item.
     *
     * @param type     跳转链接样式的文案内容，建议不超过18个字
     * @param title    跳转链接样式的文案内容，建议不超过18个字
     * @param url      跳转链接的url，jump_list.type是1时必填
     * @param appId    跳转链接的小程序的appid，必须是与当前应用关联的小程序，jump_list.type是2时必填
     * @param pagePath 跳转链接的小程序的pagepath，jump_list.type是2时选填
     */
    public JumpListItem(int type, String title, String url, String appId, String pagePath) {
      this.type = type;
      this.title = title;
      this.url = url;
      this.appId = appId;
      this.pagePath = pagePath;
    }
  }


  /**
   * The type Card action.
   */
  @Data
  public static class CardAction implements Serializable {

    /**
     * The constant TYPE_URL.
     */
    public static final Integer TYPE_URL = 1;

    /**
     * The constant TYPE_MINI_PROGRAM.
     */
    public static final Integer TYPE_MINI_PROGRAM = 2;
    private static final long serialVersionUID = 778337980453008719L;

    /**
     * 跳转事件类型，0或不填代表不是链接，1 代表跳转url，2 代表打开小程序
     */
    @SerializedName("type")
    private int type;

    /**
     * 跳转事件的url，card_action.type是1时必填
     */
    @SerializedName("url")
    private String url;

    /**
     * 跳转事件的小程序的appid，card_action.type是2时必填
     */
    @SerializedName("appid")
    private String appId;

    /**
     * 跳转事件的小程序的appid，card_action.type是2时必填
     */
    @SerializedName("pagepath")
    private String pagePath;

    /**
     * Url card action.
     *
     * @param url 跳转事件的url，card_action.type是1时必填
     * @return the card action
     */
    public static CardAction url(String url) {
      return new CardAction(TYPE_URL, url, null, null);
    }

    /**
     * Mini program card action.
     *
     * @param appId    跳转事件的小程序的appid，card_action.type是2时必填
     * @param pagePath 跳转事件的小程序的appid，card_action.type是2时必填
     * @return the card action
     */
    public static CardAction miniProgram(String appId, String pagePath) {
      return new CardAction(TYPE_MINI_PROGRAM, null, appId, pagePath);
    }

    /**
     * Instantiates a new Card action.
     *
     * @param type     跳转事件类型，0或不填代表不是链接，1 代表跳转url，2 代表打开小程序
     * @param url      跳转事件的url，card_action.type是1时必填
     * @param appId    跳转事件的小程序的appid，card_action.type是2时必填
     * @param pagePath 跳转事件的小程序的appid，card_action.type是2时必填
     */
    public CardAction(int type, String url, String appId, String pagePath) {
      this.type = type;
      this.url = url;
      this.appId = appId;
      this.pagePath = pagePath;
    }
  }

  /**
   * 关键数据样式
   */
  @Data
  @AllArgsConstructor
  @NoArgsConstructor
  public static class EmphasisContent implements Serializable {

    private static final long serialVersionUID = -5831980708667484140L;
    /**
     * 关键数据样式的数据内容，建议不超过14个字
     */
    @SerializedName("title")
    private String title;

    /**
     * 关键数据样式的数据描述内容，建议不超过20个字
     */
    @SerializedName("desc")
    private String desc;
  }

  /**
   * 卡片二级垂直内容，该字段可为空数组，但有数据的话需确认对应字段是否必填，列表长度不超过4
   */
  @Data
  @AllArgsConstructor
  @NoArgsConstructor
  public static class VerticalContentListItem implements Serializable {

    private static final long serialVersionUID = -4939941518852196118L;
    /**
     * 卡片二级标题，建议不超过40个字
     */
    @SerializedName("title")
    private String title;

    /**
     * 二级普通文本，建议不超过40个字
     */
    @SerializedName("desc")
    private String desc;

  }

  /**
   * The type Card image.
   */
  @Data
  public static class CardImage implements Serializable {

    private static final long serialVersionUID = 6574529585871597781L;
    @SerializedName("aspect_ratio")
    private double aspectRatio;

    @SerializedName("url")
    private String url;

    /**
     * Instantiates a new Card image.
     *
     * @param aspectRatio the aspect ratio
     * @param url         the url
     */
    public CardImage(double aspectRatio, String url) {
      this.aspectRatio = aspectRatio;
      this.url = url;
    }
  }

  /**
   * The type Button item.
   */
  @Data
  public static class ButtonItem implements Serializable {

    private static final long serialVersionUID = 8161062534806288537L;
    /**
     * 按钮文案，建议不超过10个字
     */
    @SerializedName("text")
    private String text;

    /**
     * 按钮样式，目前可填1~4，不填或错填默认1
     */
    @SerializedName("style")
    private int style;

    /**
     * 按钮key值，用户点击后，会产生回调事件将本参数作为EventKey返回，最长支持1024字节，不可重复
     */
    @SerializedName("key")
    private String key;
  }

  /**
   * The type Submit button.
   */
  @Data
  public static class SubmitButton implements Serializable {

    private static final long serialVersionUID = 1176037356041385057L;
    @SerializedName("text")
    private String text;

    @SerializedName("key")
    private String key;

    /**
     * Instantiates a new Submit button.
     *
     * @param text the text
     * @param key  the key
     */
    public SubmitButton(String text, String key) {
      this.text = text;
      this.key = key;
    }
  }

  /**
   * 选项list，选项个数不超过 20 个，最少1个
   */
  @Data
  public static class OptionItem implements Serializable {

    private static final long serialVersionUID = -3531269002320912570L;
    @SerializedName("is_checked")
    private boolean checked;

    /**
     * 选项id，用户提交选项后，会产生回调事件，回调事件会带上该id值表示该选项，最长支持128字节，不可重复
     */
    @SerializedName("id")
    private String id;

    /**
     * 选项文案描述，建议不超过17个字
     */
    @SerializedName("text")
    private String text;

    /**
     * Instantiates a new Option item.
     *
     * @param checked 该选项是否要默认选中
     * @param id      选项id，用户提交选项后，会产生回调事件，回调事件会带上该id值表示该选项，最长支持128字节，不可重复
     * @param text    选项文案描述，建议不超过17个字
     */
    public OptionItem(boolean checked, String id, String text) {
      this.checked = checked;
      this.id = id;
      this.text = text;
    }
  }

  /**
   * The type Checkbox.
   */
  @Data
  public static class Checkbox implements Serializable {

    /**
     * The constant SINGLE_CHECK.
     */
    public static final int SINGLE_CHECK = 0;

    /**
     * The constant MULTI_CHECK.
     */
    public static final int MULTI_CHECK = 1;
    private static final long serialVersionUID = 3581367955009729023L;

    @SerializedName("mode")
    private int mode;

    @SerializedName("question_key")
    private String questionKey;

    /**
     * 选项list，选项个数不超过 20 个，最少1个
     */
    @SerializedName("option_list")
    private List<OptionItem> optionList;

    /**
     * Instantiates a new Checkbox.
     *
     * @param mode        the mode
     * @param questionKey the question key
     * @param optionList  the option list
     */
    public Checkbox(int mode, String questionKey, List<OptionItem> optionList) {
      this.mode = mode;
      this.questionKey = questionKey;
      this.optionList = optionList;
    }
  }
}
