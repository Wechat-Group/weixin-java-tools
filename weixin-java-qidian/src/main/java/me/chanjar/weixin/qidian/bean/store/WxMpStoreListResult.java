package me.chanjar.weixin.qidian.bean.store;

import java.io.Serializable;
import java.util.List;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import me.chanjar.weixin.qidian.util.json.WxMpGsonBuilder;

/**
 * <pre>
 * 门店列表结果类
 * Created by Binary Wang on 2016-09-27.
 * </pre>
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
@Data
public class WxMpStoreListResult implements Serializable {
  private static final long serialVersionUID = 5388907559949538663L;

  /**
   * 错误码，0为正常.
   */
  @SerializedName("errcode")
  private Integer errCode;
  /**
   * 错误信息.
   */
  @SerializedName("errmsg")
  private String errMsg;
  /**
   * 门店信息列表.
   */
  @SerializedName("business_list")
  private List<WxMpStoreInfo> businessList;
  /**
   * 门店信息总数.
   */
  @SerializedName("total_count")
  private Integer totalCount;

  public static WxMpStoreListResult fromJson(String json) {
    return WxMpGsonBuilder.create().fromJson(json, WxMpStoreListResult.class);
  }

  @Override
  public String toString() {
    return WxMpGsonBuilder.create().toJson(this);
  }

}
