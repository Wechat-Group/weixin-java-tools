package me.chanjar.weixin.cp.bean.messagebuilder;

import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.cp.bean.message.WxCpMessage;
import me.chanjar.weixin.cp.bean.templatecard.*;

import java.util.List;
/**
 * <pre>
 * 模板卡片消息Builder
 * 用法: WxCustomMessage m = WxCustomMessage.TEMPLATECARD().title(...)....toUser(...).build();
 * </pre>
 *
 * @author yzts</a>
 * @date 2019-05-16
 */
public class TemplateCardBuilder  extends BaseBuilder<TemplateCardBuilder>{
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
   * 任务id，同一个应用任务id不能重复，只能由数字、字母和“_-@”组成，最长128字节
   */
  private String task_id;

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


  public TemplateCardBuilder() {
    this.msgType = WxConsts.KefuMsgType.TEMPLATE_CARD;
  }

  public TemplateCardBuilder card_type(String card_type) {
    this.card_type = card_type;
    return this;
  }

  public TemplateCardBuilder source_icon_url(String source_icon_url) {
    this.source_icon_url = source_icon_url;
    return this;
  }

  public TemplateCardBuilder source_desc(String source_desc) {
    this.source_desc = source_desc;
    return this;
  }

  public TemplateCardBuilder main_title_title(String main_title_title) {
    this.main_title_title = main_title_title;
    return this;
  }

  public TemplateCardBuilder main_title_desc(String mainTitleDesc) {
    this.main_title_desc = mainTitleDesc;
    return this;
  }

  public TemplateCardBuilder emphasis_content_title(String emphasis_content_title) {
    this.emphasis_content_title = emphasis_content_title;
    return this;
  }

  public TemplateCardBuilder emphasis_content_desc(String emphasis_content_desc) {
    this.emphasis_content_desc = emphasis_content_desc;
    return this;
  }

  public TemplateCardBuilder sub_title_text(String sub_title_text) {
    this.sub_title_text = sub_title_text;
    return this;
  }

  public TemplateCardBuilder  vertical_contents(List<VerticalContent> vertical_contents) {
    this.vertical_contents = vertical_contents;
    return this;
  }

  public TemplateCardBuilder horizontal_contents(List<HorizontalContent> horizontal_contents) {
    this.horizontal_contents = horizontal_contents;
    return this;
  }

  public TemplateCardBuilder jumps(List<TemplateCardJump> jumps) {
    this.jumps = jumps;
    return this;
  }

  public TemplateCardBuilder card_action_type(Integer card_action_type) {
    this.card_action_type = card_action_type;
    return this;
  }

  public TemplateCardBuilder card_action_url(String card_action_url) {
    this.card_action_url = card_action_url;
    return this;
  }

  public TemplateCardBuilder card_action_appid(String card_action_appid) {
    this.card_action_appid = card_action_appid;
    return this;
  }

  public TemplateCardBuilder card_action_pagepath(String card_action_pagepath) {
    this.card_action_pagepath = card_action_pagepath;
    return this;
  }

  public TemplateCardBuilder task_id(String taskId) {
    this.task_id = taskId;
    return this;
  }

  public TemplateCardBuilder buttons(List<TemplateCardButton> buttons) {
    this.buttons = buttons;
    return this;
  }

  public TemplateCardBuilder checkbox_question_key(String checkbox_question_key) {
    this.checkbox_question_key = checkbox_question_key;
    return this;
  }

  public TemplateCardBuilder checkbox_mode(Integer checkbox_mode) {
    this.checkbox_mode = checkbox_mode;
    return this;
  }

  public TemplateCardBuilder options(List<CheckboxOption> options) {
    this.options = options;
    return this;
  }

  public TemplateCardBuilder submit_button_text(String submit_button_text) {
    this.submit_button_text = submit_button_text;
    return this;
  }

  public TemplateCardBuilder submit_button_key(String submit_button_key) {
    this.submit_button_key = submit_button_key;
    return this;
  }

  public TemplateCardBuilder selects(List<MultipleSelect> selects) {
    this.selects = selects;
    return this;
  }

  @Override
  public WxCpMessage build() {
    WxCpMessage m = super.build();
    m.setSafe(null);
    m.setCard_type(this.card_type);
    m.setSource_icon_url(this.source_icon_url);
    m.setSource_desc(this.source_desc);
    m.setMain_title_title(this.main_title_title);
    m.setMain_title_desc(this.main_title_desc);
    m.setCard_image_url(this.card_image_url);
    m.setCard_image_aspect_ratio(this.card_image_aspect_ratio);
    m.setEmphasis_content_title(this.emphasis_content_title);
    m.setEmphasis_content_desc(this.emphasis_content_desc);
    m.setSub_title_text(this.sub_title_text);
    m.setVertical_contents(this.vertical_contents);
    m.setHorizontal_contents(this.horizontal_contents);
    m.setJumps(this.jumps);
    m.setCard_action_type(this.card_action_type);
    m.setCard_action_appid(this.card_action_appid);
    m.setCard_action_pagepath(this.card_action_pagepath);
    m.setCard_action_url(this.card_action_url);
    m.setTaskId(this.task_id);
    m.setButtons(this.buttons);
    m.setCheckbox_mode(this.checkbox_mode);
    m.setCheckbox_question_key(this.checkbox_question_key);
    m.setOptions(this.options);
    m.setSubmit_button_text(this.submit_button_text);
    m.setSubmit_button_key(this.submit_button_key);
    m.setSelects(this.selects);
    return m;
  }
}
