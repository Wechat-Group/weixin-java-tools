package cn.binarywang.wx.miniapp.bean.cloud;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 获取文件下载链接结果.
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 * @date 2020-01-27
 */
@Data
public class WxCloudBatchDownloadFileResult implements Serializable {
  private static final long serialVersionUID = 646423661372964402L;

  @SerializedName("file_list")
  private List<FileDownloadInfo> fileList;

  @Data
  public static class FileDownloadInfo implements Serializable {
    private static final long serialVersionUID = 5812969045277862211L;

    /**
     * fileid	string	文件ID
     */
    @SerializedName("fileid")
    private String fileId;

    /**
     * download_url	string	下载链接
     */
    @SerializedName("download_url")
    private String downloadUrl;

    /**
     * status	number	状态码
     */
    @SerializedName("status")
    private Integer status;

    /**
     * errmsg	string	该文件错误信息
     */
    @SerializedName("errmsg")
    private String errMsg;
  }
}
