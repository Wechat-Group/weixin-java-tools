package cn.binarywang.wx.miniapp.bean.cloud;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 文件删除结果.
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 * @date 2020-01-27
 */
@Data
public class WxCloudBatchDeleteFileResult implements Serializable {
  private static final long serialVersionUID = -1133274298839868115L;

  @SerializedName("delete_list")
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
