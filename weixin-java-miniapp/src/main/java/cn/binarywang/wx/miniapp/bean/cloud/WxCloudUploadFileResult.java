package cn.binarywang.wx.miniapp.bean.cloud;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;

/**
 * 云开发文件上传接口响应结果.
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 * @date 2020-01-27
 */
@Data
public class WxCloudUploadFileResult implements Serializable {
  private static final long serialVersionUID = 787346474470048318L;

  /**
   * 上传url
   */
  @SerializedName("url")
  private String url;

  /**
   * token
   */
  @SerializedName("token")
  private String token;

  /**
   * authorization
   */
  @SerializedName("authorization")
  private String authorization;

  /**
   * 文件ID
   */
  @SerializedName("file_id")
  private String fileId;

  /**
   * cos文件ID
   */
  @SerializedName("cos_file_id")
  private String cosFileId;
}
