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
public class WxOpenWxaCodeTemplateDraftListResult implements Serializable {

  private static final long serialVersionUID = -2790107002917829516L;

  @SerializedName("draft_list")
  private List<CodeTemplateDraft> draftList;


  @Data
  public class CodeTemplateDraft implements Serializable {

    private static final long serialVersionUID = -2991180835094736324L;

    @SerializedName("create_time")
    private String createTime;// 开发者上传草稿时间
    @SerializedName("user_version")
    private String userVersion;// 模版版本号，开发者自定义字段
    @SerializedName("user_desc")
    private String userDesc;// 模版描述 开发者自定义字段
    @SerializedName("draft_id")
    private String draftId;// 草稿id

    @Override
    public String toString() {
      return WxMpGsonBuilder.INSTANCE.create().toJson(this);
    }

  }

  public static WxOpenWxaCodeTemplateDraftListResult fromJson(String json) {
    return WxOpenGsonBuilder.create().fromJson(json, WxOpenWxaCodeTemplateDraftListResult.class);
  }

  @Override
  public String toString() {
    return WxMpGsonBuilder.INSTANCE.create().toJson(this);
  }

}
