package me.chanjar.weixin.open.bean.icp;

import java.io.Serializable;
import java.util.List;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.chanjar.weixin.open.bean.result.WxOpenResult;

/**
 * @author xzh
 * @Description 证件类型定义
 * @createTime 2024/08/14 10:52
 */
@Getter
@Setter
@NoArgsConstructor
public class WxOpenQueryIcpCertificateTypeResult extends WxOpenResult {

  private static final long serialVersionUID = -6492653564753189104L;

  /**
   * 证件类型列表
   */
  @SerializedName("items")
  private List<Item> items;

  @Getter
  @Setter
  @NoArgsConstructor
  public static class Item implements Serializable {

    private static final long serialVersionUID = -5353506906838811002L;

    @SerializedName("type")
    private Integer type;

    @SerializedName("subject_type")
    private Integer subjectType;

    @SerializedName("name")
    private String name;

    @SerializedName("remark")
    private String remark;
  }
}
