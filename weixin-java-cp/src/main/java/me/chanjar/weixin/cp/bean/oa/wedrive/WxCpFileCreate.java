package me.chanjar.weixin.cp.bean.oa.wedrive;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import me.chanjar.weixin.cp.bean.WxCpBaseResp;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.io.Serializable;

/**
 * 新建文件/微文档 返回信息.
 *
 * @author Wang_Wong
 */
@Data
public class WxCpFileCreate extends WxCpBaseResp implements Serializable {
  private static final long serialVersionUID = -5028321625142879581L;

  @SerializedName("fileid")
  private String fileId;

  @SerializedName("url")
  private String url;

  /**
   * From json wx cp file create.
   *
   * @param json the json
   * @return the wx cp file create
   */
  public static WxCpFileCreate fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpFileCreate.class);
  }

  public String toJson() {
    return WxCpGsonBuilder.create().toJson(this);
  }

}
