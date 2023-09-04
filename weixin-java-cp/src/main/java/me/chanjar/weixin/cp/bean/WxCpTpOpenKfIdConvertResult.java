package me.chanjar.weixin.cp.bean;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.util.List;

@Setter
@Getter
public class WxCpTpOpenKfIdConvertResult extends WxCpBaseResp {

  /**
   * 微信客服ID转换结果
   */
  @SerializedName("items")
  private List<Item> items;

  /**
   * 无法转换的微信客服ID列表
   */
  @SerializedName("invalid_open_kfid_list")
  private List<String> invalidOpenKfIdList;

  @Getter
  @Setter
  public static class Item {

    /***
     * 企业主体下的微信客服ID
     */
    @SerializedName("open_kfid")
    private String openKfId;

    /**
     * 服务商主体下的微信客服ID，如果传入的open_kfid已经是服务商主体下的ID，则new_open_kfid与open_kfid相同。
     */
    @SerializedName("new_open_kfid")
    private String newOpenKfId;
  }

  public static WxCpTpOpenKfIdConvertResult fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpTpOpenKfIdConvertResult.class);
  }
}
