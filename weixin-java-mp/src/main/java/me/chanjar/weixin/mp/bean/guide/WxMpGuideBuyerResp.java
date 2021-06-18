package me.chanjar.weixin.mp.bean.guide;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import me.chanjar.weixin.common.bean.ToJson;
import me.chanjar.weixin.common.util.json.WxGsonBuilder;

import java.io.Serializable;

/**
 * 批量操作客户是否成功返回信息
 * @author <a href="https://www.sacoc.cn">广州跨界-宋心成</a>
 * @date 2021/5/10/010
 */

@Data
public class WxMpGuideBuyerResp implements ToJson, Serializable {
  private static final long serialVersionUID = -5628199106867822424L;

  /**
   * 错误码
   */
  @SerializedName("errcode")
  private Integer errCode;

  /**
   * 错误信息
   */
  @SerializedName("errmsg")
  private String errMsg;

  /**
   * 客户openid
   */
  @SerializedName("openid")
  private String openid;

  @Override
  public String toJson() {
    return WxGsonBuilder.create().toJson(this);
  }

  public static WxMpGuideBuyerResp fromJson(String json) {
    return WxGsonBuilder.create().fromJson(json, WxMpGuideBuyerResp.class);
  }
}
