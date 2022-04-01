package me.chanjar.weixin.cp.bean.external.moment;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import me.chanjar.weixin.cp.bean.external.msg.*;

import java.util.List;

/**
 * @author Borisg
 */
@Data
public class MomentInfo {
  @SerializedName("moment_id")
  private String momentId;
  @SerializedName("creator")
  private String creator;
  @SerializedName("create_time")
  private String createTime;
  @SerializedName("create_type")
  private Integer createType;
  @SerializedName("visible_type")
  private Integer visibleType;
  private Text text;
  private List<Image> image;
  private Video video;
  private Link link;
  private Location location;
}
