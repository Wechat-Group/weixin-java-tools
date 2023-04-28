package me.chanjar.weixin.open.bean;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;

/**
 * 小程序代码模板
 *
 * @author <a href="https://github.com/charmingoh">Charming</a>
 * @since 2018-04-26 17:10
 */
@Data
public class WxOpenMaCodeTemplate implements Serializable {
  private static final long serialVersionUID = -3278116984473619010L;
  /**
   * 草稿id
   */
  @SerializedName(value = "draftId", alternate = "draft_id")
  private Long draftId;
  /**
   * 模版id
   */
  @SerializedName(value = "templateId", alternate = "template_id")
  private Long templateId;
  /**
   * 模版版本号，开发者自定义字段
   */
  @SerializedName(value = "userVersion", alternate = "user_version")
  private String userVersion;
  /**
   * 模版描述 开发者自定义字段
   */
  @SerializedName(value = "userDesc", alternate = "user_desc")
  private String userDesc;

  /**
   * 模板类型 0对应普通模板，1对应标准模板
   */
  @SerializedName(value = "templateType", alternate = "template_type")
  private Integer templateType;

  /**
   * 开发者上传草稿时间 / 被添加为模版的时间
   */
  @SerializedName(value = "createTime", alternate = "create_time")
  private Long createTime;

  /**
   * 开发小程序的appid
   */
  @SerializedName(value = "sourceMiniProgramAppid", alternate = "source_miniprogram_appid")
  private String sourceMiniProgramAppid;

  /**
   * 开发小程序的名称
   */
  @SerializedName(value = "sourceMiniProgram", alternate = "source_miniprogram")
  private String sourceMiniProgram;

  /**
   * 标准模板的场景标签；普通模板不返回该值
   */
  @SerializedName(value = "auditScene", alternate = "audit_scene")
  private Integer auditScene;

  /**
   * 标准模板的审核状态；普通模板不返回该值
   */
  @SerializedName(value = "auditStatus", alternate = "audit_status")
  private Integer auditStatus;

  /**
   * 标准模板的审核驳回的原因，；普通模板不返回该值
   */
  @SerializedName(value = "reason")
  private String reason;

  /**
   * 开发者
   */
  @SerializedName(value = "developer")
  private String developer;
}
