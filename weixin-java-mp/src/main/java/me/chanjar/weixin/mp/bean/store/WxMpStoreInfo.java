package me.chanjar.weixin.mp.bean.store;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import me.chanjar.weixin.common.util.ToStringUtils;

import java.io.Serializable;

@Data
public class WxMpStoreInfo implements Serializable{
  private static final long serialVersionUID = 7300598931768355461L;

  @SerializedName("base_info")
  private WxMpStoreBaseInfo baseInfo;

  @Override
  public String toString() {
    return ToStringUtils.toSimpleString(this);
  }
}
