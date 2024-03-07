package me.chanjar.weixin.cp.bean.oa.doc;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.io.Serializable;

/**
 * 重命名空间请求.
 *
 * @author Hugo
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class WxCpDocRenameRequest implements Serializable {
  private static final long serialVersionUID = -4960339393895754138L;

  /**
   * 文档docid（docid、formid只能填其中一个）
   */
  @SerializedName("docid")
  private String docId;

  /**
   * 收集表id（docid、formid只能填其中一个）
   */
  @SerializedName("formid")
  private String formId;

  /**
   * 重命名后的文档名 （注意：文档名最多填255个字符, 英文算1个, 汉字算2个, 超过255个字符会被截断）
   */
  @SerializedName("new_name")
  private String newName;

  /**
   * From json wx cp space rename request.
   *
   * @param json the json
   * @return the wx cp space rename request
   */
  public static WxCpDocRenameRequest fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpDocRenameRequest.class);
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
