package me.chanjar.weixin.open.bean.result;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import me.chanjar.weixin.open.bean.WxCloudFunctionTrigger;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class WxOpenMaBatchCloudGetTriggersResult extends WxOpenResult {

  private static final long serialVersionUID = 3716467418670630992L;

  @SerializedName("triggers")
  private List<WxCloudFunctionTrigger> triggers;
}
