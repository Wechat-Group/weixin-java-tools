package me.chanjar.weixin.cp.bean.oa.doc;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import me.chanjar.weixin.cp.bean.WxCpBaseResp;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.io.Serializable;

/**
 * 新建空间信息.
 *
 * @author Wang_Wong
 */
@Data
public class WxCpDocCreateData extends WxCpBaseResp implements Serializable {
  private static final long serialVersionUID = -5028321625242879581L;

  /**
   * 新建文档的访问链接
   */
  @SerializedName("url")
  private String url;

  /**
   * 新建文档的docid
   */
  @SerializedName("docid")
  private String docId;

  /**
   * From json wx cp space create data.
   *
   * @param json the json
   * @return the wx cp space create data
   */
  public static WxCpDocCreateData fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpDocCreateData.class);
  }

  public String toJson() {
    return WxCpGsonBuilder.create().toJson(this);
  }

}
