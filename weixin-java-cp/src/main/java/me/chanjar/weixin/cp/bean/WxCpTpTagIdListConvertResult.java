package me.chanjar.weixin.cp.bean;


import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.util.List;

@Getter
@Setter
public class WxCpTpTagIdListConvertResult extends WxCpBaseResp {

  private static final long serialVersionUID = -6153589164415497369L;


  /**
   * 客户标签转换结果
   */
  @SerializedName("items")
  private List<Item> items;

  /**
   * 无法转换的客户标签ID列表
   */
  @SerializedName("invalid_external_tagid_list")
  private List<String> invalidExternalTagIdList;


  @Getter
  @Setter
  public static class Item {

    /**
     * 企业主体下的客户标签ID
     */
    @SerializedName("external_tagid")
    private String externalTagId;

    /**
     * 服务商主体下的客户标签ID，如果传入的external_tagid已经是服务商主体下的ID，则open_external_tagid与external_tagid相同。
     */
    @SerializedName("open_external_tagid")
    private String openExternalTagId;
  }

  public static WxCpTpTagIdListConvertResult fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpTpTagIdListConvertResult.class);
  }
}
