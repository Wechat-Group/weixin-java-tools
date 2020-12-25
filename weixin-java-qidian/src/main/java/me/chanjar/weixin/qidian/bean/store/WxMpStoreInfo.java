package me.chanjar.weixin.qidian.bean.store;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import me.chanjar.weixin.qidian.util.json.WxMpGsonBuilder;

/**
 * .
 *
 * @author BinaryWang
 */
@Data
public class WxMpStoreInfo implements Serializable {
  private static final long serialVersionUID = 7300598931768355461L;

  @SerializedName("base_info")
  private WxMpStoreBaseInfo baseInfo;

  @Override
  public String toString() {
    return WxMpGsonBuilder.create().toJson(this);
  }
}
