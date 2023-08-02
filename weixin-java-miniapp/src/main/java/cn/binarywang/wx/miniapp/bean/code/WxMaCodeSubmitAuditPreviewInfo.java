package cn.binarywang.wx.miniapp.bean.code;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * @author binarywang
 */
@Data
@Accessors(chain = true)
public class WxMaCodeSubmitAuditPreviewInfo implements Serializable {
  private static final long serialVersionUID = -3391652096859063951L;

  /**
   * video_id_list
   * String Array
   * 否
   * 录屏mediaid列表，可以通过提审素材上传接口获得
   */
  @SerializedName("video_id_list")
  private List<String> videoIdList;

  /**
   * pic_id_list
   * String Array
   * 否
   * 截屏mediaid列表，可以通过提审素材上传接口获得
   */
  @SerializedName("pic_id_list")
  private List<String> picIdList;
}
