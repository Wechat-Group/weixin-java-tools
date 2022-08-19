package me.chanjar.weixin.open.bean.tcbComponent;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import me.chanjar.weixin.open.bean.result.WxOpenResult;

import java.io.Serializable;
import java.util.List;

@Data
public class GetTcbEnvListResponse extends WxOpenResult implements Serializable {
  private static final long serialVersionUID = -5896318347861752798L;

  @SerializedName("info_list")
  private List<InfoListDTO> infoList;

  @Data
  public static class InfoListDTO implements Serializable {
    @SerializedName("env")
    private String env;
    @SerializedName("alias")
    private String alias;
    @SerializedName("create_time")
    private String createTime;
    @SerializedName("update_time")
    private String updateTime;
    @SerializedName("status")
    private String status;
    @SerializedName("package_id")
    private String packageId;
    @SerializedName("package_name")
    private String packageName;
    @SerializedName("dbinstance_id")
    private String dbinstanceId;
    @SerializedName("bucket_id")
    private String bucketId;
  }
}
