package me.chanjar.weixin.cp.bean.linkedcorp;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 获取互联企业成员详细信息
 *
 * @author libo
 */
@Data
public class WxCpLinkedCorpUser implements Serializable {
  private static final long serialVersionUID = -5197865724556226531L;
  @SerializedName("userid")
  private String userId;
  @SerializedName("name")
  private String name;
  @SerializedName("department")
  private String[] department;
  @SerializedName("mobile")
  private String mobile;
  @SerializedName("email")
  private String email;
  @SerializedName("position")
  private String position;
  @SerializedName("corpid")
  private String corpId;
  private final List<Attr> extAttrs = new ArrayList<>();

  /**
   * The type Attr.
   */
  @Data
  @Accessors(chain = true)
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  public static class Attr implements Serializable {
    private static final long serialVersionUID = -5696099236344075582L;

    /**
     * 属性类型: 0-文本 1-网页
     */
    private Integer type;
    private String name;
    private String textValue;
    private String webUrl;
    private String webTitle;
  }

}
