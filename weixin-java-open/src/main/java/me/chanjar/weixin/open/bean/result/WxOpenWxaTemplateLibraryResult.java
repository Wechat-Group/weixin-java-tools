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
public class WxOpenWxaTemplateLibraryResult implements Serializable {

  private static final long serialVersionUID = 8935656251784866825L;

  private String id;
  private String title;
  @SerializedName("keyword_list")
  private List<TemplateKeyword> keywordList;

  @Data
  public class TemplateKeyword implements Serializable {

    private static final long serialVersionUID = -2577913316179481967L;

    @SerializedName("keyword_id")
    private String keywordId;// 关键词id，添加模板时需要
    private String name;// 关键词内容
    private String example;// 关键词内容对应的示例

    @Override
    public String toString() {
      return WxMpGsonBuilder.INSTANCE.create().toJson(this);
    }

  }

  public static WxOpenWxaTemplateLibraryResult fromJson(String json) {
    return WxOpenGsonBuilder.create().fromJson(json, WxOpenWxaTemplateLibraryResult.class);
  }

  @Override
  public String toString() {
    return WxMpGsonBuilder.INSTANCE.create().toJson(this);
  }

}
