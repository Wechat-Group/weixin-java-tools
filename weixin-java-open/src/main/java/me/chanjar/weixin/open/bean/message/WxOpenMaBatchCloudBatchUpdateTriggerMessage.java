package me.chanjar.weixin.open.bean.message;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import me.chanjar.weixin.open.bean.WxCloudFunctionTrigger;

import java.io.Serializable;
import java.util.List;

@Data
public class WxOpenMaBatchCloudBatchUpdateTriggerMessage implements Serializable {

  private static final long serialVersionUID = -9192840679420300765L;

  @SerializedName("envs")
  private List<String> envs;

  /**
   * fuck tencent,sb
   */
  @SerializedName("funcname")
  private String functionName;

  @SerializedName("triggers")
  private List<WxCloudFunctionTrigger> triggers;


}
