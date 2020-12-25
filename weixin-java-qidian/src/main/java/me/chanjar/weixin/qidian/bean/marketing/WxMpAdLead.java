package me.chanjar.weixin.qidian.bean.marketing;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author <a href="https://github.com/007gzs">007</a>
 */
@Data
public class WxMpAdLead implements Serializable {
  private static final long serialVersionUID = -8889087268596440407L;
  /**
   * 点击ID
   */
  @SerializedName("click_id")
  private String click_id;
  /**
   * 广告组ID
   */
  @SerializedName("adgroup_id")
  private Long adgroup_id;
  /**
   * 广告组名称
   */
  @SerializedName("adgroup_name")
  private String adgroup_name;
  /**
   * 推广计划ID
   */
  @SerializedName("campaign_id")
  private Long campaign_id;
  /**
   * 推广计划名称
   */
  @SerializedName("campaign_name")
  private String campaign_name;
  /**
   * 代理ID
   */
  @SerializedName("agency_id")
  private String agency_id;
  /**
   * 代理名称
   */
  @SerializedName("agency_name")
  private String agency_name;
  /**
   * 销售线索信息
   */
  @SerializedName("leads_info")
  private List<WxMpAdLeadInfo> leads_info;
}
