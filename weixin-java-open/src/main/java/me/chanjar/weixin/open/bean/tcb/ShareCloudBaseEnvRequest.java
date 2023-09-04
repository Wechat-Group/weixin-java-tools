package me.chanjar.weixin.open.bean.tcb;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ShareCloudBaseEnvRequest implements Serializable {
  private static final long serialVersionUID = 410566969624593042L;

  @SerializedName("data")
  private List<DataDTO> data;

  /**
   * 请求环境源，填 1，表示云托管环境
   */
  @SerializedName("source_type")
  private Integer sourceType;

  @Data
  @Builder
  @AllArgsConstructor
  @NoArgsConstructor
  public static class DataDTO implements Serializable {
    private static final long serialVersionUID = -8117487215582856716L;

    @SerializedName("env")
    private String env;
    @SerializedName("appids")
    private List<String> appids;
  }

}
