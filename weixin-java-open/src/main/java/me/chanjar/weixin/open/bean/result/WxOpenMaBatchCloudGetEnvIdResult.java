package me.chanjar.weixin.open.bean.result;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import me.chanjar.weixin.open.util.json.WxOpenGsonBuilder;

import java.util.List;

/**
 * 微信第三方平台-批量代云开发-批量查询小程序绑定的环境id
 *
 * @author caiqy
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class WxOpenMaBatchCloudGetEnvIdResult extends WxOpenResult {

  private static final long serialVersionUID = -1664697245879090505L;

  @SerializedName("relation_data")
  private List<RelationData> relationData;

  public static class RelationData {

    /**
     * app id
     */
    @SerializedName("appid")
    private String appId;

    /**
     * 环境ID
     */
    @SerializedName("env_list")
    private List<String> envList;
  }


  @Override
  public String toString() {
    return WxOpenGsonBuilder.create().toJson(this);
  }
}
