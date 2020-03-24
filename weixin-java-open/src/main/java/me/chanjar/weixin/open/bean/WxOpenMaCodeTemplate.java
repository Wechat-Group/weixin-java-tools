package me.chanjar.weixin.open.bean;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;

/**
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
   * 开发者上传草稿时间 / 被添加为模版的时间
   */
  @SerializedName(value = "createTime", alternate = "create_time")
  private Long createTime;
}
