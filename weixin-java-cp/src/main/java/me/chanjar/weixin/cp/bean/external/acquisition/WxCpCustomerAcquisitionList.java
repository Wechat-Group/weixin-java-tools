package me.chanjar.weixin.cp.bean.external.acquisition;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import me.chanjar.weixin.cp.bean.WxCpBaseResp;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * 获客链接列表
 *
 * @author alien_zyl
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class WxCpCustomerAcquisitionList extends WxCpBaseResp implements Serializable {

  private static final long serialVersionUID = -4168552242409627573L;

  /**
   * link_id列表
   */
  @SerializedName("link_id_list")
  private List<String> linkIdList;

  /**
   * 分页游标，在下次请求时填写以获取之后分页的记录
   */
  @SerializedName("next_cursor")
  private String nextCursor;

  public static WxCpCustomerAcquisitionList fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpCustomerAcquisitionList.class);
  }

}
