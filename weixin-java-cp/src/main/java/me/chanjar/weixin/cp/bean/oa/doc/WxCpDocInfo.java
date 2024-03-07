package me.chanjar.weixin.cp.bean.oa.doc;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import me.chanjar.weixin.cp.bean.WxCpBaseResp;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.io.Serializable;

/**
 * 获取空间信息.
 *
 * @author Wang_Wong
 */
@Data
public class WxCpDocInfo extends WxCpBaseResp implements Serializable {
  private static final long serialVersionUID = -5028321623142879581L;

  @SerializedName("doc_base_info")
  private DocInfo docBaseInfo;

  /**
   * The type Space info.
   */
  @Getter
  @Setter
  public static class DocInfo implements Serializable {
    private static final long serialVersionUID = -4860239393895754598L;

    /**
     * 文档docid
     */
    @SerializedName("docid")
    private String docId;

    /**
     * 文档名字
     */
    @SerializedName("doc_name")
    private String docName;

    /**
     * 文档创建时间
     */
    @SerializedName("create_time")
    private Long createTime;

    /**
     * 文档最后修改时间
     */
    @SerializedName("modify_time")
    private Long modifyTime;

    /**
     * 3: 文档 4: 表格
     */
    @SerializedName("doc_type")
    private Integer docType;

    /**
     * From json space info.
     *
     * @param json the json
     * @return the space info
     */
    public static DocInfo fromJson(String json) {
      return WxCpGsonBuilder.create().fromJson(json, DocInfo.class);
    }

    /**
     * To json string.
     *
     * @return the string
     */
    public String toJson() {
      return WxCpGsonBuilder.create().toJson(this);
    }

  }


  /**
   * From json wx cp space info.
   *
   * @param json the json
   * @return the wx cp space info
   */
  public static WxCpDocInfo fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpDocInfo.class);
  }

  public String toJson() {
    return WxCpGsonBuilder.create().toJson(this);
  }

}
