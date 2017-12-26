/**
 *
 */
package me.chanjar.weixin.open.bean.result;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import me.chanjar.weixin.mp.util.json.WxMpGsonBuilder;
import me.chanjar.weixin.open.util.json.WxOpenGsonBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * @author lfj
 */
@Data
public class WxOpenWxaCodeTemplateListResult implements Serializable {

  private static final long serialVersionUID = -5160627287680416141L;

  @SerializedName("template_list")
  private List<CodeTemplate> templateList;

  @Data
  public class CodeTemplate implements Serializable {

    private static final long serialVersionUID = 1210088237618467799L;

    @SerializedName("create_time")
    private String createTime;// 开发者上传草稿时间
    @SerializedName("user_version")
    private String userVersion;// 模版版本号，开发者自定义字段
    @SerializedName("user_desc")
    private String userDesc;// 模版描述 开发者自定义字段
    @SerializedName("template_id")
    private String templateId;// 模版id

    @Override
    public String toString() {
      return WxMpGsonBuilder.INSTANCE.create().toJson(this);
    }

  }

  public static WxOpenWxaCodeTemplateListResult fromJson(String json) {
    return WxOpenGsonBuilder.create().fromJson(json, WxOpenWxaCodeTemplateListResult.class);
  }

  @Override
  public String toString() {
    return WxMpGsonBuilder.INSTANCE.create().toJson(this);
  }

}
