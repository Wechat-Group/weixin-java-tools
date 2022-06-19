package me.chanjar.weixin.cp.bean.oa.wedrive;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import me.chanjar.weixin.cp.bean.WxCpBaseResp;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * 获取邀请链接.
 *
 * @author Wang_Wong
 */
@Data
public class WxCpFileList extends WxCpBaseResp implements Serializable {
  private static final long serialVersionUID = -5028321625142879581L;

  @SerializedName("has_more")
  private Boolean hasMore;

  @SerializedName("next_start")
  private Integer nextStart;

  @SerializedName("file_list")
  private FileList fileList;

  @Getter
  @Setter
  public static class FileList implements Serializable {
    private static final long serialVersionUID = -4960239393895754598L;

    @SerializedName("item")
    private List<Item> item;

    public static FileList fromJson(String json) {
      return WxCpGsonBuilder.create().fromJson(json, FileList.class);
    }

    public String toJson() {
      return WxCpGsonBuilder.create().toJson(this);
    }

  }

  @Getter
  @Setter
  public static class Item implements Serializable {
    private static final long serialVersionUID = -4960239393895754598L;

    @SerializedName("fileid")
    private String fileId;

    @SerializedName("file_name")
    private String fileName;

    @SerializedName("spaceid")
    private String spaceId;

    @SerializedName("fatherid")
    private String fatherId;

    @SerializedName("file_size")
    private Long fileSize;

    @SerializedName("ctime")
    private Long cTime;

    @SerializedName("mtime")
    private Long mTime;

    @SerializedName("file_type")
    private Integer fileType;

    @SerializedName("file_status")
    private Integer fileStatus;

    @SerializedName("create_userid")
    private String createUserId;

    @SerializedName("update_userid")
    private String updateUserId;

    @SerializedName("sha")
    private String sha;

    @SerializedName("url")
    private String url;

    @SerializedName("md5")
    private String md5;

    public static Item fromJson(String json) {
      return WxCpGsonBuilder.create().fromJson(json, Item.class);
    }

    public String toJson() {
      return WxCpGsonBuilder.create().toJson(this);
    }

  }

  public static WxCpFileList fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpFileList.class);
  }

  public String toJson() {
    return WxCpGsonBuilder.create().toJson(this);
  }

}
