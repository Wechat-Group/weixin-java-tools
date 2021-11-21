package me.chanjar.weixin.cp.bean.message;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import lombok.Data;
import me.chanjar.weixin.common.api.WxConsts.KefuMsgType;
import me.chanjar.weixin.cp.bean.article.MpnewsArticle;
import me.chanjar.weixin.cp.bean.article.NewArticle;
import me.chanjar.weixin.cp.bean.messagebuilder.*;
import me.chanjar.weixin.cp.bean.taskcard.TaskCardButton;
import me.chanjar.weixin.cp.bean.templatecard.*;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static me.chanjar.weixin.common.api.WxConsts.KefuMsgType.*;

/**
 * 消息.
 *
 * @author Daniel Qian
 */
@Data
public class WxCpMessage implements Serializable {
  private static final long serialVersionUID = -2082278303476631708L;

  private String toUser;
  private String toParty;
  private String toTag;
  private Integer agentId;
  private String msgType;
  private String content;
  private String mediaId;
  private String thumbMediaId;
  private String title;
  private String description;
  private String musicUrl;
  private String hqMusicUrl;
  private String safe;
  private String url;
  private String btnTxt;
  private List<NewArticle> articles = new ArrayList<>();
  private List<MpnewsArticle> mpnewsArticles = new ArrayList<>();
  private String appId;
  private String page;
  private Boolean emphasisFirstItem;
  private Map<String, String> contentItems;

  /**
   * enable_id_trans
   * 表示是否开启id转译，0表示否，1表示是，默认0
   */
  private Boolean enableIdTrans = false;
  /**
   * enable_duplicate_check
   * 表示是否开启重复消息检查，0表示否，1表示是，默认0
   */
  private Boolean enableDuplicateCheck = false;
  /**
   * duplicate_check_interval
   * 表示是否重复消息检查的时间间隔，默认1800s，最大不超过4小时
   */
  private Integer duplicateCheckInterval;

  /**
   * 任务卡片特有的属性.
   */
  private String taskId;
  private List<TaskCardButton> taskButtons = new ArrayList<>();

  /**
   * 模板型卡片特有属性
   */
  /**
   * 模板卡片类型，文本通知型卡片填写 “text_notice”,
   * 图文展示型卡片此处填写 “news_notice”,
   * 按钮交互型卡片填写”button_interaction”,
   * 投票选择型卡片填写”vote_interaction”,
   * 多项选择型卡片填写 “multiple_interaction”
   */
  private String card_type;

  /**
   * 卡片来源样式信息，不需要来源样式可不填写
   * 来源图片的url
   */
  private String source_icon_url;
  /**
   * 卡片来源样式信息，不需要来源样式可不填写
   * 来源图片的描述，建议不超过20个字
   */
  private String source_desc;

  /**
   * 一级标题，建议不超过36个字
   */
  private String main_title_title;
  /**
   * 标题辅助信息，建议不超过44个字
   */
  private String main_title_desc;

  /**
   * 图文展示型的卡片必须有图片字段。
   * 图片的url.
   */
  private String card_image_url;

  /**
   * 图片的宽高比，宽高比要小于2.25，大于1.3，不填该参数默认1.3
   */
  private Float card_image_aspect_ratio;
  /**
   * 关键数据样式
   * 关键数据样式的数据内容，建议不超过14个字
   */
  private String emphasis_content_title;
  /**
   * 关键数据样式的数据描述内容，建议不超过22个字
   */
  private String emphasis_content_desc;

  /**
   * 二级普通文本，建议不超过160个字
   */
  private String sub_title_text;

  /**
   * 卡片二级垂直内容，该字段可为空数组，但有数据的话需确认对应字段是否必填，列表长度不超过4
   */
  private List<VerticalContent> vertical_contents;

  /**
   * 二级标题+文本列表，该字段可为空数组，但有数据的话需确认对应字段是否必填，列表长度不超过6
   */
  private List<HorizontalContent> horizontal_contents;

  /**
   * 跳转指引样式的列表，该字段可为空数组，但有数据的话需确认对应字段是否必填，列表长度不超过3
   */
  private List<TemplateCardJump> jumps;

  /**
   * 整体卡片的点击跳转事件，text_notice必填本字段
   * 跳转事件类型，1 代表跳转url，2 代表打开小程序。text_notice卡片模版中该字段取值范围为[1,2]
   */
  private Integer  card_action_type;
  /**
   * 跳转事件的url，card_action.type是1时必填
   */
  private String card_action_url;

  /**
   * 跳转事件的小程序的appid，必须是与当前应用关联的小程序，card_action.type是2时必填
   */
  private String card_action_appid;

  /**
   * 跳转事件的小程序的pagepath，card_action.type是2时选填
   */
  private String card_action_pagepath;

  /**
   * 按钮交互型卡片需指定。
   * 按钮列表，该字段可为空数组，但有数据的话需确认对应字段是否必填，列表长度不超过6
   */
  private List<TemplateCardButton> buttons;

  /**
   * 投票选择型卡片需要指定
   * 选择题key值，用户提交选项后，会产生回调事件，回调事件会带上该key值表示该题，最长支持1024字节
   */
  private String checkbox_question_key;

  /**
   * 选择题模式，单选：0，多选：1，不填默认0
   */
  private Integer checkbox_mode;

  /**
   * 选项list，选项个数不超过 20 个，最少1个
   */
  private List<CheckboxOption> options;

  /**
   * 提交按钮样式
   * 按钮文案，建议不超过10个字，不填默认为提交
   */
  private String submit_button_text;
  /**
   * 提交按钮的key，会产生回调事件将本参数作为EventKey返回，最长支持1024字节
   */
  private String submit_button_key;
  /**
   * 下拉式的选择器列表，multiple_interaction类型的卡片该字段不可为空，一个消息最多支持 3 个选择器
   */
  private List<MultipleSelect> selects;

  /**
   * 获得文本消息builder.
   */
  public static TextBuilder TEXT() {
    return new TextBuilder();
  }

  /**
   * 获得文本卡片消息builder.
   */
  public static TextCardBuilder TEXTCARD() {
    return new TextCardBuilder();
  }

  /**
   * 获得图片消息builder.
   */
  public static ImageBuilder IMAGE() {
    return new ImageBuilder();
  }

  /**
   * 获得语音消息builder.
   */
  public static VoiceBuilder VOICE() {
    return new VoiceBuilder();
  }

  /**
   * 获得视频消息builder.
   */
  public static VideoBuilder VIDEO() {
    return new VideoBuilder();
  }

  /**
   * 获得图文消息builder.
   */
  public static NewsBuilder NEWS() {
    return new NewsBuilder();
  }

  /**
   * 获得mpnews图文消息builder.
   */
  public static MpnewsBuilder MPNEWS() {
    return new MpnewsBuilder();
  }

  /**
   * 获得markdown消息builder.
   */
  public static MarkdownMsgBuilder MARKDOWN() {
    return new MarkdownMsgBuilder();
  }

  /**
   * 获得文件消息builder.
   */
  public static FileBuilder FILE() {
    return new FileBuilder();
  }

  /**
   * 获得任务卡片消息builder.
   */
  public static TaskCardBuilder TASKCARD() {
    return new TaskCardBuilder();
  }

  /**
   * 获得任务卡片消息builder.
   */
  public static TemplateCardBuilder TEMPLATECARD() {
    return new TemplateCardBuilder();
  }

  /**
   * 获得小程序通知消息builder.
   */
  public static MiniProgramNoticeMsgBuilder newMiniProgramNoticeBuilder() {
    return new MiniProgramNoticeMsgBuilder();
  }

  /**
   * <pre>
   * 请使用.
   * {@link KefuMsgType#TEXT}
   * {@link KefuMsgType#IMAGE}
   * {@link KefuMsgType#VOICE}
   * {@link KefuMsgType#MUSIC}
   * {@link KefuMsgType#VIDEO}
   * {@link KefuMsgType#NEWS}
   * {@link KefuMsgType#MPNEWS}
   * {@link KefuMsgType#MARKDOWN}
   * {@link KefuMsgType#TASKCARD}
   * {@link KefuMsgType#MINIPROGRAM_NOTICE}
   * {@link KefuMsgType#TEMPLATE_CARD}
   * </pre>
   *
   * @param msgType 消息类型
   */
  public void setMsgType(String msgType) {
    this.msgType = msgType;
  }

  public String toJson() {
    JsonObject messageJson = new JsonObject();
    if (this.getAgentId() != null) {
      messageJson.addProperty("agentid", this.getAgentId());
    }

    if (StringUtils.isNotBlank(this.getToUser())) {
      messageJson.addProperty("touser", this.getToUser());
    }

    messageJson.addProperty("msgtype", this.getMsgType());

    if (StringUtils.isNotBlank(this.getToParty())) {
      messageJson.addProperty("toparty", this.getToParty());
    }

    if (StringUtils.isNotBlank(this.getToTag())) {
      messageJson.addProperty("totag", this.getToTag());
    }

    if (this.getEnableIdTrans()) {
      messageJson.addProperty("enable_id_trans", 1);
    }

    if (this.getEnableDuplicateCheck()) {
      messageJson.addProperty("enable_duplicate_check", 1);
    }

    if (this.getDuplicateCheckInterval() != null) {
      messageJson.addProperty("duplicate_check_interval", this.getDuplicateCheckInterval());
    }

    this.handleMsgType(messageJson);

    if (StringUtils.isNotBlank(this.getSafe())) {
      messageJson.addProperty("safe", this.getSafe());
    }

    return messageJson.toString();
  }

  private void handleMsgType(JsonObject messageJson) {
    switch (this.getMsgType()) {
      case TEXT: {
        JsonObject text = new JsonObject();
        text.addProperty("content", this.getContent());
        messageJson.add("text", text);
        break;
      }
      case MARKDOWN: {
        JsonObject text = new JsonObject();
        text.addProperty("content", this.getContent());
        messageJson.add("markdown", text);
        break;
      }
      case TEXTCARD: {
        JsonObject text = new JsonObject();
        text.addProperty("title", this.getTitle());
        text.addProperty("description", this.getDescription());
        text.addProperty("url", this.getUrl());
        text.addProperty("btntxt", this.getBtnTxt());
        messageJson.add("textcard", text);
        break;
      }
      case IMAGE: {
        JsonObject image = new JsonObject();
        image.addProperty("media_id", this.getMediaId());
        messageJson.add("image", image);
        break;
      }
      case FILE: {
        JsonObject image = new JsonObject();
        image.addProperty("media_id", this.getMediaId());
        messageJson.add("file", image);
        break;
      }
      case VOICE: {
        JsonObject voice = new JsonObject();
        voice.addProperty("media_id", this.getMediaId());
        messageJson.add("voice", voice);
        break;
      }
      case VIDEO: {
        JsonObject video = new JsonObject();
        video.addProperty("media_id", this.getMediaId());
        video.addProperty("thumb_media_id", this.getThumbMediaId());
        video.addProperty("title", this.getTitle());
        video.addProperty("description", this.getDescription());
        messageJson.add("video", video);
        break;
      }
      case NEWS: {
        JsonObject newsJsonObject = new JsonObject();
        JsonArray articleJsonArray = new JsonArray();
        for (NewArticle article : this.getArticles()) {
          JsonObject articleJson = new JsonObject();
          articleJson.addProperty("title", article.getTitle());
          articleJson.addProperty("description", article.getDescription());
          articleJson.addProperty("url", article.getUrl());
          articleJson.addProperty("picurl", article.getPicUrl());
          articleJsonArray.add(articleJson);
        }
        newsJsonObject.add("articles", articleJsonArray);
        messageJson.add("news", newsJsonObject);
        break;
      }
      case MPNEWS: {
        JsonObject newsJsonObject = new JsonObject();
        if (this.getMediaId() != null) {
          newsJsonObject.addProperty("media_id", this.getMediaId());
        } else {
          JsonArray articleJsonArray = new JsonArray();
          for (MpnewsArticle article : this.getMpnewsArticles()) {
            article2Json(articleJsonArray, article);
          }

          newsJsonObject.add("articles", articleJsonArray);
        }
        messageJson.add("mpnews", newsJsonObject);
        break;
      }
      case TASKCARD: {
        JsonObject text = new JsonObject();
        text.addProperty("title", this.getTitle());
        text.addProperty("description", this.getDescription());

        if (StringUtils.isNotBlank(this.getUrl())) {
          text.addProperty("url", this.getUrl());
        }

        text.addProperty("task_id", this.getTaskId());

        JsonArray buttonJsonArray = new JsonArray();
        for (TaskCardButton button : this.getTaskButtons()) {
          btn2Json(buttonJsonArray, button);
        }
        text.add("btn", buttonJsonArray);

        messageJson.add("taskcard", text);
        break;
      }
      case MINIPROGRAM_NOTICE: {
        JsonObject notice = new JsonObject();
        notice.addProperty("appid", this.getAppId());
        notice.addProperty("page", this.getPage());
        notice.addProperty("description", this.getDescription());
        notice.addProperty("title", this.getTitle());
        notice.addProperty("emphasis_first_item", this.getEmphasisFirstItem());
        JsonArray content = new JsonArray();
        for (Map.Entry<String, String> item : this.getContentItems().entrySet()) {
          JsonObject articleJson = new JsonObject();
          articleJson.addProperty("key", item.getKey());
          articleJson.addProperty("value", item.getValue());
          content.add(articleJson);
        }
        notice.add("content_item", content);

        messageJson.add("miniprogram_notice", notice);
        break;
      }
      case TEMPLATE_CARD: {
        JsonObject template = new JsonObject();
        template.addProperty("card_type", this.getCard_type());

        if (StringUtils.isNotBlank(this.getSource_icon_url()) || StringUtils.isNotBlank(this.getSource_desc())) {
          JsonObject source = new JsonObject();
          if (StringUtils.isNotBlank(this.getSource_icon_url())) {
            source.addProperty("icon_url", this.getSource_icon_url());
          }
          if (StringUtils.isNotBlank(this.getSource_desc())) {
            source.addProperty("desc", this.getSource_desc());
          }
          template.add("source", source);
        }

        if (StringUtils.isNotBlank(this.getMain_title_title()) || StringUtils.isNotBlank(this.getMain_title_desc())) {
          JsonObject main_title = new JsonObject();
          if (StringUtils.isNotBlank(this.getMain_title_title())) {
            main_title.addProperty("title", this.getMain_title_title());
          }
          if (StringUtils.isNotBlank(this.getMain_title_desc())) {
            main_title.addProperty("desc", this.getMain_title_desc());
          }
          template.add("main_title", main_title);
        }

        if (StringUtils.isNotBlank(this.getEmphasis_content_title()) || StringUtils.isNotBlank(this.getEmphasis_content_desc())) {
          JsonObject emphasis_content = new JsonObject();
          if (StringUtils.isNotBlank(this.getEmphasis_content_title())) {
            emphasis_content.addProperty("title", this.getEmphasis_content_title());
          }
          if (StringUtils.isNotBlank(this.getEmphasis_content_desc())) {
            emphasis_content.addProperty("desc", this.getEmphasis_content_desc());
          }
          template.add("emphasis_content", emphasis_content);
        }


        if (StringUtils.isNotBlank(this.getSub_title_text())) {
          template.addProperty("sub_title_text", this.getSub_title_text());
        }

        if (StringUtils.isNotBlank(this.getTaskId())) {
          template.addProperty("task_id", this.getTaskId());
        }

        List<VerticalContent> verticalContents = this.getVertical_contents();
        if(null != verticalContents && verticalContents.size() > 0) {
          JsonArray vContentJsonArray = new JsonArray();
          for (VerticalContent vContent : this.getVertical_contents()) {
            JsonObject tempObject = vContent.toJson();
            vContentJsonArray.add(tempObject);
          }
          template.add("vertical_content_list", vContentJsonArray);
        }

        List<HorizontalContent> horizontalContents = this.getHorizontal_contents();
        if(null != horizontalContents && horizontalContents.size() > 0) {
          JsonArray hContentJsonArray = new JsonArray();
          for (HorizontalContent hContent : this.getHorizontal_contents()) {
            JsonObject tempObject = hContent.toJson();
            hContentJsonArray.add(tempObject);
          }
          template.add("horizontal_content_list", hContentJsonArray);
        }

        List<TemplateCardJump> jumps = this.getJumps();
        if(null != jumps && jumps.size() > 0) {
          JsonArray jumpJsonArray = new JsonArray();
          for (TemplateCardJump jump : this.getJumps()) {
            JsonObject tempObject = jump.toJson();
            jumpJsonArray.add(tempObject);
          }
          template.add("jump_list", jumpJsonArray);
        }

        if (null != this.getCard_action_type()) {
          JsonObject cardAction = new JsonObject();
          cardAction.addProperty("type", this.getCard_action_type());
          if (StringUtils.isNotBlank(this.getCard_action_url())) {
            cardAction.addProperty("url", this.getCard_action_url());
          }
          if (StringUtils.isNotBlank(this.getCard_action_appid())) {
            cardAction.addProperty("appid", this.getCard_action_appid());
          }
          if (StringUtils.isNotBlank(this.getCard_action_pagepath())) {
            cardAction.addProperty("pagepath", this.getCard_action_pagepath());
          }
          template.add("card_action", cardAction);
        }

        List<TemplateCardButton> buttons = this.getButtons();
        if(null != buttons && buttons.size() > 0) {
          JsonArray btnJsonArray = new JsonArray();
          for (TemplateCardButton btn : this.getButtons()) {
            JsonObject tempObject = btn.toJson();
            btnJsonArray.add(tempObject);
          }
          template.add("button_list", btnJsonArray);
        }

        // checkbox
        if (StringUtils.isNotBlank(this.getCheckbox_question_key())) {
          JsonObject checkBox = new JsonObject();
          checkBox.addProperty("question_key", this.getCheckbox_question_key());
          if (null != this.getCheckbox_mode()) {
            checkBox.addProperty("mode", this.getCheckbox_mode());
          }
          JsonArray optionArray = new JsonArray();
          for (CheckboxOption option : this.getOptions()) {
            JsonObject tempObject = option.toJson();
            optionArray.add(tempObject);
          }
          checkBox.add("option_list", optionArray);

          template.add("checkbox", checkBox);
        }

        // submit_button
        if (StringUtils.isNotBlank(this.getSubmit_button_text()) || StringUtils.isNotBlank(this.getSubmit_button_key())) {
          JsonObject submit_button = new JsonObject();
          if (StringUtils.isNotBlank(this.getSubmit_button_text())) {
            submit_button.addProperty("text", this.getSubmit_button_text());
          }
          if (StringUtils.isNotBlank(this.getSubmit_button_key())) {
            submit_button.addProperty("key", this.getSubmit_button_key());
          }
          template.add("submit_button", submit_button);
        }

        // select_list
        List<MultipleSelect> selects = this.getSelects();
        if(null != selects && selects.size() > 0) {
          JsonArray selectJsonArray = new JsonArray();
          for (MultipleSelect select : this.getSelects()) {
            JsonObject tempObject = select.toJson();
            selectJsonArray.add(tempObject);
          }
          template.add("select_list", selectJsonArray);
        }

        messageJson.add("template_card", template);
        break;
      }
      default: {
        // do nothing
      }
    }
  }

  private void btn2Json(JsonArray buttonJsonArray, TaskCardButton button) {
    JsonObject buttonJson = new JsonObject();
    buttonJson.addProperty("key", button.getKey());
    buttonJson.addProperty("name", button.getName());

    if (StringUtils.isNotBlank(button.getReplaceName())) {
      buttonJson.addProperty("replace_name", button.getReplaceName());
    }

    if (StringUtils.isNotBlank(button.getColor())) {
      buttonJson.addProperty("color", button.getColor());
    }

    if (button.getBold() != null) {
      buttonJson.addProperty("is_bold", button.getBold());
    }

    buttonJsonArray.add(buttonJson);
  }

  private void article2Json(JsonArray articleJsonArray, MpnewsArticle article) {
    JsonObject articleJson = new JsonObject();
    articleJson.addProperty("title", article.getTitle());
    articleJson.addProperty("thumb_media_id", article.getThumbMediaId());
    articleJson.addProperty("author", article.getAuthor());
    articleJson.addProperty("content_source_url", article.getContentSourceUrl());
    articleJson.addProperty("content", article.getContent());
    articleJson.addProperty("digest", article.getDigest());
    articleJson.addProperty("show_cover_pic", article.getShowCoverPic());
    articleJsonArray.add(articleJson);
  }

}
