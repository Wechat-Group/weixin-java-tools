package me.chanjar.weixin.cp.bean.external.acquisition;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import me.chanjar.weixin.cp.bean.WxCpBaseResp;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * 获客链接详情
 *
 * @author alien_zyl
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class WxCpCustomerAcquisitionInfo extends WxCpBaseResp implements Serializable {

  private static final long serialVersionUID = -425354507473041229L;
  /**
   * link_id列表
   */
  @SerializedName("link")
  private Link link;

  /**
   * 分页游标，在下次请求时填写以获取之后分页的记录
   */
  @SerializedName("range")
  private Range range;

  /**
   * 是否无需验证，默认为true
   */
  @SerializedName("skip_verify")
  private Boolean skipVerify;

  public static WxCpCustomerAcquisitionInfo fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpCustomerAcquisitionInfo.class);
  }

  @Data
  @EqualsAndHashCode(callSuper = true)
  public static class Link extends WxCpBaseResp implements Serializable {
    private static final long serialVersionUID = 6750537220943228300L;

    /**
     * 获客链接的id
     */
    @SerializedName("link_id")
    private String linkId;

    /**
     * 获客链接的名称
     */
    @SerializedName("link_name")
    private String linkName;

    /**
     * 获客链接实际的url
     */
    @SerializedName("url")
    private String url;

    /**
     * 创建时间
     */
    @SerializedName("create_time")
    private Long createTime;

    public static Link fromJson(String json) {
      return WxCpGsonBuilder.create().fromJson(json, Link.class);
    }
  }

  @Data
  public static class Range implements Serializable {
    private static final long serialVersionUID = -6343768645371744643L;

    /**
     * 此获客链接关联的userid列表，最多可关联100个
     */
    @SerializedName("user_list")
    private List<String> userList;

    /**
     * 此获客链接关联的部门id列表，部门覆盖总人数最多100个
     */
    @SerializedName("department_list")
    private List<String> departmentList;

    public static Range fromJson(String json) {
      return WxCpGsonBuilder.create().fromJson(json, Range.class);
    }

    public String toJson() {
      return WxCpGsonBuilder.create().toJson(this);
    }
  }

}
