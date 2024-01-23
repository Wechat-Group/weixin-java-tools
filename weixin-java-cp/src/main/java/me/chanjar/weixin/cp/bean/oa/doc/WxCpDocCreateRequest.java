package me.chanjar.weixin.cp.bean.oa.doc;

import com.google.gson.annotations.SerializedName;
import lombok.*;
import lombok.experimental.Accessors;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * 新建文档请求.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class WxCpDocCreateRequest implements Serializable {
  private static final long serialVersionUID = -4960239393895454138L;

  /**
   * 空间spaceid。若指定spaceid，则fatherid也要同时指定
   */
  @SerializedName("spaceid")
  private String spaceId;

  /**
   * 父目录fileid, 在根目录时为空间spaceid
   */
  @SerializedName("fatherid")
  private String fatherId;

  /**
   * 文档类型, 3:文档 4:表格
   */
  @SerializedName("doc_type")
  private Integer docType;

  /**
   * 文档名字（注意：文件名最多填255个字符, 超过255个字符会被截断）
   */
  @SerializedName("doc_name")
  private String docName;

  /**
   * 文档管理员userid
   */
  @SerializedName("admin_users")
  private List<String> adminUsers;

  /**
   * From json wx cp space create request.
   *
   * @param json the json
   * @return the wx cp space create request
   */
  public static WxCpDocCreateRequest fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpDocCreateRequest.class);
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
