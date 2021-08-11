package me.chanjar.weixin.open.bean.result;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import me.chanjar.weixin.open.util.json.WxOpenGsonBuilder;

import java.io.Serializable;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class WxOpenMaBatchCloudBatchUpdateTriggerResult extends WxOpenResult{

  private static final long serialVersionUID = -6698933735017091837L;

  @SerializedName("err_list")
  private List<Error> errList;

  @Data
  public static class Error implements Serializable {

    private static final long serialVersionUID = -774278088317598378L;

    @SerializedName("env")
    private String env;

    @SerializedName("errmsg")
    private String errMsg;
  }


  @Override
  public String toString() {
    return WxOpenGsonBuilder.create().toJson(this);
  }
}
