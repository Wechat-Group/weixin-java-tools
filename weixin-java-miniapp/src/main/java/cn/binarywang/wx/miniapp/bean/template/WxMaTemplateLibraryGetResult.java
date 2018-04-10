package cn.binarywang.wx.miniapp.bean.template;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import me.chanjar.weixin.common.util.json.WxGsonBuilder;

import java.io.Serializable;
import java.util.List;

@Data
public class WxMaTemplateLibraryGetResult implements Serializable{

  private static final long serialVersionUID = -190847592776636744L;
  private String id;
  private String title;
  @SerializedName("keyword_list")
  private List<KeywordInfo> keywordList;

  @Data
  public static class KeywordInfo{

    @SerializedName("keyword_id")
    private int keywordId;
    private String name;
    private String example;
  }

  public static WxMaTemplateLibraryGetResult fromJson(String json){
    return WxGsonBuilder.create().fromJson(json, WxMaTemplateLibraryGetResult.class);
  }
}
