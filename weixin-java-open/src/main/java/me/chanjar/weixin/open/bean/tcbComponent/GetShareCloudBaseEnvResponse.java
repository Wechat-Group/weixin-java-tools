package me.chanjar.weixin.open.bean.tcbComponent;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import me.chanjar.weixin.open.bean.result.WxOpenResult;

import java.io.Serializable;
import java.util.List;

@Data
public class GetShareCloudBaseEnvResponse extends WxOpenResult implements Serializable {
  private static final long serialVersionUID = -6267755285547585403L;

  @SerializedName("relation_data")
  private List<RelationDataDTO> relationData;

  @SerializedName("err_list")
  private List<ErrListDTO> errList;

  @Data
  public static class RelationDataDTO implements Serializable {
    @SerializedName("appid")
    private String appid;
    @SerializedName("env_list")
    private List<String> envList;
  }

  @Data
  public static class ErrListDTO implements Serializable {
    @SerializedName("appid")
    private String appid;
    @SerializedName("errmsg")
    private String errmsg;
  }
}
