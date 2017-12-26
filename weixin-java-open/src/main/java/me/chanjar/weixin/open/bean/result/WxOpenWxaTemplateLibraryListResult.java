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
public class WxOpenWxaTemplateLibraryListResult implements Serializable {

  private static final long serialVersionUID = -5653848561704256702L;

  private List<TemplateLibrary> list;
  @SerializedName("total_count")
  private Integer totalCount;

  @Data
  public class TemplateLibrary implements Serializable {

    private static final long serialVersionUID = 5032529389536959648L;
    private String id;
    private String title;

    @Override
    public String toString() {
      return WxMpGsonBuilder.INSTANCE.create().toJson(this);
    }
  }


  public static WxOpenWxaTemplateLibraryListResult fromJson(String json) {
    return WxOpenGsonBuilder.create().fromJson(json, WxOpenWxaTemplateLibraryListResult.class);
  }

  @Override
  public String toString() {
    return WxMpGsonBuilder.INSTANCE.create().toJson(this);
  }

}
