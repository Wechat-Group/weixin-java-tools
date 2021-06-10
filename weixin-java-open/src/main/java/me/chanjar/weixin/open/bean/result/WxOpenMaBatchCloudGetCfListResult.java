package me.chanjar.weixin.open.bean.result;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = true)
public class WxOpenMaBatchCloudGetCfListResult extends WxOpenResult {

  private static final long serialVersionUID = 3476780438753913998L;

  @SerializedName("total_count")
  private Long totalCount;

  @Data
  public static class Function implements Serializable {

    private static final long serialVersionUID = -2218973076554654332L;

    /**
     * 更新时间
     */
    @SerializedName("mod_time")
    private String modTime;

    /**
     * 提那家时间
     */
    @SerializedName("add_time")
    private String addTime;

    /**
     * node环境
     */
    @SerializedName("runtime")
    private String runtime;

    /**
     * 名称
     */
    @SerializedName("name")
    private String name;

    /**
     * 状态 Active
     */
    @SerializedName("status")
    private String status;
  }
}
