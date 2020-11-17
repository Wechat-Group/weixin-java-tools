package me.chanjar.weixin.cp.bean.external;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;
import me.chanjar.weixin.common.util.json.WxGsonBuilder;
import me.chanjar.weixin.cp.bean.WxCpBaseResp;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.util.List;

/**
 *
 */
@Getter
@Setter
public class WxCpUserExternalTagGroupList extends WxCpBaseResp {

  @SerializedName("tag_group")
  private List<WxCpUserExternalTagGroupList.TagGroup> tagGroupList;

  @Getter
  @Setter
  public static class TagGroup{
    @SerializedName("group_id")
    private String groupId;

    @SerializedName("group_name")
    private String groupName;

    @SerializedName("create_time")
    private Long createTime;

    @SerializedName("order")
    private Integer order;

    @SerializedName("deleted")
    private Boolean deleted;


    @SerializedName("tag")
    private List<Tag> tag;

    @Getter
    @Setter
    public static class Tag {

      /**
       * 客户群ID
       */
      @SerializedName("id")
      private String id;

      @SerializedName("name")
      private String name;

      @SerializedName("create_time")
      private Long  createTime;

      @SerializedName("order")
      private Integer  order;

      @SerializedName("deleted")
      private Boolean  deleted;

    }
  }

  public String toJson() {
    return WxGsonBuilder.create().toJson(this);
  }

  public static WxCpUserExternalTagGroupList fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpUserExternalTagGroupList.class);
  }
}
