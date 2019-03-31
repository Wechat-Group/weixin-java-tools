package me.chanjar.weixin.mp.bean.marketing;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author <a href="https://github.com/007gzs">007</a>
 */
@Data
public class WxMpAdLeadInfo implements Serializable {
  private static final long serialVersionUID = -6462312242780350479L;
  @SerializedName("key")
  private String key;
  @SerializedName("value")
  private String value;
}
