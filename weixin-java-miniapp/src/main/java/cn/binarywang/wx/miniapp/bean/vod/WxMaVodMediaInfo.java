package cn.binarywang.wx.miniapp.bean.vod;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * 小程序帐号的可选类目，其中 address / tag / title 是提交审核会用到的
 * <p>
 * media_id 	number 	媒资文件id。
 * create_time 	number 	上传时间，时间戳。
 * expire_time 	number 	过期时间，时间戳。
 * drama_id 	number 	所属剧目id。
 * file_size 	string 	媒资文件大小，单位：字节。
 * duration 	number 	播放时长，单位：秒。
 * name 	string 	媒资文件名。
 * description 	string 	描述。
 * cover_url 	string 	封面图临时链接。
 * original_url 	string 	原始视频临时链接。
 * mp4_url 	string 	mp4格式临时链接 。
 * hls_url 	string 	hls格式临时链接。
 * audit_detail 	MediaAuditDetail 	审核信息。
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class WxMaVodMediaInfo implements Serializable {
  private static final long serialVersionUID = -7663757440028175135L;
  private String name;
  private String description;
  @SerializedName("media_id")
  private Integer mediaId;
  @SerializedName("drama_id")
  private Integer dramaId;
  @SerializedName("create_time")
  private Long createTime;
  @SerializedName("file_size")
  private String fileSize;
  @SerializedName("duration")
  private Long duration;
  @SerializedName("expire_time")
  private Long expireTime;
  @SerializedName("cover_url")
  private String coverUrl;
  @SerializedName("original_url")
  private String originalUrl;
  @SerializedName("mp4_url")
  private String mp4Url;
  @SerializedName("hls_url")
  private String hlsUrl;
  @SerializedName("audit_detail")
  private MediaAuditDetail auditDetail;

  @Data
  public static class MediaAuditDetail {

    @SerializedName("status")
    private Integer status;
    @SerializedName("create_time")
    private Long createTime;
    @SerializedName("audit_time")
    private Long auditTime;
    @SerializedName("reason")
    private String reason;
    @SerializedName("evidence_material_id_list")
    private List<String> evidenceMaterialIdList;
  }
}
