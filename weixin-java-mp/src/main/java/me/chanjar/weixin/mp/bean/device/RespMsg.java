package me.chanjar.weixin.mp.bean.device;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 * @author keungtung.
 * @date 10/12/2016
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class RespMsg extends AbstractDeviceBean {
  private static final long serialVersionUID = -4241272701707684136L;

  @SerializedName("ret_code")
  private Integer retCode;
  @SerializedName("error_info")
  private String errorInfo;
}
